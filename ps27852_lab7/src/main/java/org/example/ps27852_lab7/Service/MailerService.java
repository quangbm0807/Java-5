package org.example.ps27852_lab7.Service;

import jakarta.mail.MessagingException;
import org.example.ps27852_lab7.entity.MailInfo;

public interface MailerService {
    void send(MailInfo mail) throws MessagingException;
    void send(String []to, String subject, String body) throws MessagingException;
    void queue(MailInfo mail);
    void queue(String []to, String subject, String body);

}
