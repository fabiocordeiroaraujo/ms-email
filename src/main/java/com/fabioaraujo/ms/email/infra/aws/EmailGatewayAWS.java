package com.fabioaraujo.ms.email.infra.aws;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailGatewayAWS implements EmailGateway {
    @Autowired
    private JavaMailSender emailSender;

    public EmailGatewayAWS(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void send(Email email) {
        System.out.println("AWS");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        emailSender.send(message);
    }
}
