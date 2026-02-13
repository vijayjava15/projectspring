package com.learn.websocket.email;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.learn.websocket.notification.Notification;
import com.learn.websocket.notification.NotificationHandler;
import com.learn.websocket.notification.NotificationService;
import com.learn.websocket.notification.NotificationStatus;
import com.learn.websocket.user.User;

@Service
public class EmailSendHandler implements NotificationHandler{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    NotificationService notificationService;
    

    @Value("${spring.mail.username:}")
    private String defaultFrom;

    public void sendNotification(User user, String subject, String body) {
        if (StringUtils.isBlank(user.getEmail())) {
            throw new IllegalArgumentException("to is required");
        }
        if (StringUtils.isBlank(subject)) {
            throw new IllegalArgumentException("subject is required");
        }
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("body is required");
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        if (StringUtils.isNotBlank(defaultFrom)) {
            mailMessage.setFrom(defaultFrom);
        }
        mailSender.send(mailMessage);
        System.out.println("sending email");
        saveNoticification(user, body);
    }

    @Override
    public void process(User user, String message) {
        sendNotification(user, "E-Receipt", message);
    }

    private void saveNoticification(User user, String body){

        Notification noticification = new Notification();
        noticification.setMessage(body);
        noticification.setChannel("EMAIL");
        noticification.setRecipientUserId(user.getId());
        noticification.setStatus(NotificationStatus.SENT);
        notificationService.create(noticification);

    }
}
