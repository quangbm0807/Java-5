package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.MailerService;
import org.example.ps27852_lab7.entity.MailInfo;
import org.example.ps27852_lab7.helper.MailerHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mailer")
@RequiredArgsConstructor
public class MailerController {
    private final MailerService mailerService;

    @GetMapping("")
    public String mailViewer(Model model, HttpServletRequest request) {
        model.addAttribute("content", "mail.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại{
        return "index";
    }


    @PostMapping("/send")
    public String send(Model model,
                       @RequestParam String txtTo,
                       @RequestParam String txtCC,
                       @RequestParam String txtBCC,
                       @RequestParam String txtSubject,
                       @RequestParam String txtContent,
                       @RequestParam("files") MultipartFile[] multipartFiles,
                       @RequestParam(value = "sendDate", required = false) String sendDateStr, // Nhận giá trị thời gian gửi
                       HttpServletRequest request) throws IOException {

        MailerHelper helper = new MailerHelper();

        String[] toEmails = helper.parseStringEmailToArray(txtTo);
        String[] emailCC = helper.parseStringEmailToArray(txtCC);
        String[] emailBCC = helper.parseStringEmailToArray(txtBCC);

        MailInfo mail = new MailInfo();
        mail.setTo(toEmails);
        mail.setCc(emailCC);
        mail.setBcc(emailBCC);
        mail.setSubject(txtSubject);
        mail.setBody(txtContent);
        mail.setMultipartFiles(Arrays.asList(multipartFiles));

        // Chuyển đổi sendDateStr thành LocalDateTime nếu có
        if (sendDateStr != null && !sendDateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime sendDate = LocalDateTime.parse(sendDateStr, formatter);
            mail.setSendDate(sendDate); // Thiết lập thời gian gửi email
        }

        try {
            if (mail.getSendDate() == null) {
                mailerService.send(mail); // Gửi ngay nếu không có thời gian gửi
            } else {
                mailerService.queue(mail); // Thêm vào hàng đợi nếu có thời gian gửi
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/mailer";
    }

}
