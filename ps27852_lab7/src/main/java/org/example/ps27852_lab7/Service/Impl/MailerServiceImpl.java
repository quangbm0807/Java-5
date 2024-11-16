package org.example.ps27852_lab7.Service.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.MailerService;
import org.example.ps27852_lab7.entity.MailInfo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailerServiceImpl implements MailerService {

    List<MailInfo> listEmails = new ArrayList<>();
    private final JavaMailSender sender;

    @Override
    public void send(MailInfo mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        // Sử dụng Helper để thiết lập các thông tin cần thiết cho message
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());

        String[] cc = mail.getCc();
        //Kiểm tra mảng cc có tồn tại hay không
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }

        String[] bcc = mail.getBcc();
        //Kiểm tra mảng bcc có tồn tại hay không
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }
        // Đính kèm file từ MultipartFile
        for (MultipartFile multipartFile : mail.getMultipartFiles()) {
            if (!multipartFile.isEmpty()) {
                helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
            }
        }
        // Gửi message đến SMTP server
        sender.send(message);
    }

    @Override
    public void send(String []to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body));
    }

    @Override
    public void queue(MailInfo mail) {
        listEmails.add(mail);
    }

    @Override
    public void queue(String []to, String subject, String body) {
        queue(new MailInfo(to, subject, body));
    }


    @Scheduled(fixedDelay = 6000)
    public void run() {
        LocalDateTime now = LocalDateTime.now();

        List<MailInfo> toSend = new ArrayList<>();

        // Lọc các email cần gửi
        for (MailInfo mail : listEmails) {
            if (mail.getSendDate() != null && mail.getSendDate().isBefore(now)) {
                toSend.add(mail);
            }
        }

        // Gửi các email theo lịch trình
        for (MailInfo mail : toSend) {
            try {
                this.send(mail);
                listEmails.remove(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
