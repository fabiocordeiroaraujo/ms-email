package com.fabioaraujo.ms.email.infra.gmail;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailGatewayGmail implements EmailGateway {

    @Autowired
    private JavaMailSender emailSender;

    public EmailGatewayGmail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void send(Email email) {
        System.out.println("GMAIL");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        emailSender.send(message);
    }
}
