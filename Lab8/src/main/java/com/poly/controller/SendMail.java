package com.poly.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import com.poly.service.MailerService;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import com.poly.model.*;
@Controller
public class SendMail {
	@Autowired
	HttpServletRequest req;

	@Autowired
	JavaMailSender sender;

	@Autowired
	MailerService mailer;

	@Autowired
	ServletContext context;

	@GetMapping("/mail/send")
	public void index(Model model) {
		System.out.println("SendMailController.Index() -> " + req.getRequestURI());
		MailModel mail = new MailModel();
		model.addAttribute("mailModel", mail);
	}

	@PostMapping("/mail/send")
	public String send(Model model, @ModelAttribute("mailModel") MailModel mail,
			@RequestParam("attachment") MultipartFile[] attach) {
		MimeMessage message = sender.createMimeMessage();
		try {
			List<File> fileAttach = new ArrayList<>();
			String pathUpload = context.getRealPath("/uploads");
			File dirs = new File(pathUpload);
			if(!dirs.exists())
				dirs.mkdirs();
			for(MultipartFile at: attach) {
				String namefileOrgin = at.getOriginalFilename();
				int index = namefileOrgin.lastIndexOf(".");
				String rename = namefileOrgin.substring(0, index - 1) + "_" +(new Date().getTime() + "_"
						+ (long) Math.floor(Math.random() * (1000-100+1) + 100) + "." + namefileOrgin.substring(index+1));
				File upload = new File(pathUpload + "/"+rename);
				at.transferTo(upload);
				fileAttach.add(upload);
			}
			mail.setFiles(fileAttach);
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getBody(), true);
			helper.setReplyTo(mail.getFrom());
			for(String email: mail.getCc()) {
				helper.addCc(email);
			}
			for(String email: mail.getBcc()) {
				helper.addBcc(email);
			}
			for(File file: mail.getFiles()) {
				helper.addAttachment(file.getName(), file);
			}
		} catch (MessagingException | IllegalStateException | IOException ex) {
			System.out.println(ex);
			model.addAttribute("message", "Gui mail that bai");
		}
		sender.send(message);
		model.addAttribute("message", "Gui mail thanh cong");
		return "mail/send";
	}

	@GetMapping("/mail/send_with_queue")
	public String send_with_queue(Model model) {
		MailModel mail = new MailModel();
		model.addAttribute("mailModel", mail);
		return "/mail/send_with_queue";
	}

	@PostMapping (" mail/send with _queue")
	public String send_with_queue (Model m, @ModelAttribute ("mailModel")MailModel mail,
	@RequestParam("attachment") MultipartFile[] attach) {
		try {
			List<File> fileAttach = new ArrayList<> () ;
			String pathUpload = context.getRealPath ("/uploads") ;
			File dirs = new File (pathUpload);
			if (!dirs.exists ())
				dirs.mkdirs () ;
			for (MultipartFile at : attach) {
				String namefileOrgin = at.getOriginalFilename() ;
				int index = namefileOrgin.lastIndexOf(".");
				String rename = namefileOrgin.substring(0, index - 1) + "_" +(new Date().getTime() + "_"
						+ (long) Math.floor(Math.random() * (1000-100+1) + 100) + "." + namefileOrgin.substring(index+1));
				File upload = new File(pathUpload + "/" + rename);
				at.transferTo(upload);
				fileAttach.add(upload);
			}
				mail.setFiles(fileAttach);
				mailer.push(mail);
		}catch (MessagingException | IllegalStateException | IOException ex) {
			ex.printStackTrace();
		}
		return"/mail/send_with _queue";
	}

	@ResponseBody
	@RequestMapping ("/mailer/send")
		public String sendler () {
		try { 
			mailer.push ("dangthpc04349@fpt.edu.vn", "Subject 1 ", "Content 1");
			mailer.push ("lebichvicm2020@gmail.com", "Subject 2","Content 3");
			mailer.push ("dannkpc04351@fpt.edu.vn", "Subject 3", "Content 3");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Mail cua ban da duoc dua vdo hang doi. se duoc he thông tu dong gui nhau môt khoan thoi gian!";
	
	}

}
