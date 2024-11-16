package org.example.ps27852_lab7.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
    String from = "vothanhtung795@gmail.com";
    String []to;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    private List<MultipartFile> multipartFiles; // Thêm thuộc tính này
    private LocalDateTime sendDate; // Thêm trường này để lưu ngày giờ gửi


    public MailInfo(String []to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
