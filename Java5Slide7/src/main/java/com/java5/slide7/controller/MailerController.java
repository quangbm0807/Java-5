package com.java5.slide7.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java5.slide7.service.MailerService;
import com.java5.slide7.helper.MailerHelper;
import com.java5.slide7.model.MailInfo;

@Controller
@RequestMapping("mailer")
public class MailerController {
	@Autowired
	MailerService mailer;

	@GetMapping("")
	public String mailViewer() {
		return "mail/index";
	}

	@ResponseBody
	@PostMapping("/send")
	public String send(Model model, @RequestParam String txtTo, @RequestParam String txtCC, @RequestParam String txtBCC,
			@RequestParam String txtSubject, @RequestParam String txtContent,
			@RequestParam("files") MultipartFile[] multipartFiles) throws IOException {

		MailerHelper helper = new MailerHelper();
		List<File> files = new ArrayList<>();

		String[] toEmails = helper.parseStringEmailToArray(txtTo);
		String[] emailCC = helper.parseStringEmailToArray(txtCC);
		String[] emailBCC = helper.parseStringEmailToArray(txtBCC);

		MailInfo mail = new MailInfo();
		mail.setTo(toEmails);
		mail.setCc(emailCC);
		mail.setBcc(emailBCC);
		mail.setSubject(txtSubject);
		mail.setBody(txtContent);

		// Convert từng file MultipartFile thành File và thêm vào danh sách
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				File file = helper.convertMultipartFileToFile(multipartFile);
				files.add(file);
			}
		}

		mail.setFiles(files);

		// Gửi mail vào queue hoặc gửi trực tiếp
		mailer.queue(mail);
		return "<h1>Mail của bạn đã được gửi đi</h1>";
	}

}
