package com.fabioaraujo.ms.email.provider.gmail;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.exception.EmailBodyException;
import com.fabioaraujo.ms.email.core.exception.EmailServerException;
import com.fabioaraujo.ms.email.core.exception.EmailValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

public class EmailGatewayGmail implements EmailGateway {

    @Autowired
    private JavaMailSender emailSender;

    public EmailGatewayGmail(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(Email email) {
        try {
            System.out.println("GMAIL");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFrom());
            message.setTo(email.getToAsArray());
            message.setCc(email.getCcAsArray());
            message.setBcc(email.getBccAsArray());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            emailSender.send(message);
        } catch (MailSendException e) {
            if (Objects.requireNonNull(e.getMessage()).contains("Invalid Addresses")) {
                throw new EmailValidationException();
            } else {
                throw new EmailServerException();
            }
        } catch (MailParseException e) {
            throw new EmailBodyException();
        } catch (Exception e) {
            throw new EmailServerException();
        }
    }
}
