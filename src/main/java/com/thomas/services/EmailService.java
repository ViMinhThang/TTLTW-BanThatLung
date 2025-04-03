package com.thomas.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailService {
    private final String HOST_EMAIL = "huynhminhthang246@gmail.com";
    private final String HOST_PASSWORD = "zhfz gtpx bkbp cuuo";

    public void sendEmail(String toEmail, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HOST_EMAIL, HOST_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(HOST_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}