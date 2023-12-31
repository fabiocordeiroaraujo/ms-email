package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.EmailFactory;
import com.fabioaraujo.ms.email.core.entity.Status;
import com.fabioaraujo.ms.email.core.exception.EmailBodyException;
import com.fabioaraujo.ms.email.core.exception.EmailServerException;
import com.fabioaraujo.ms.email.core.exception.EmailValidationException;

import java.time.LocalDateTime;
import java.util.List;

public class SendEmail {

    private final EmailRepository emailRepository;
    private final EmailGateway emailGateway;

    public SendEmail(EmailRepository emailRepository, EmailGateway emailGateway) {
        this.emailRepository = emailRepository;
        this.emailGateway = emailGateway;
    }

    public Email execute(String owner, String from, List<String> to, List<String> cc, List<String> bcc, String subject, String body) {
        Email email = new EmailFactory()
                .withOwner(owner)
                .withFrom(from)
                .withTo(to)
                .withCc(cc)
                .withBcc(bcc)
                .withSubject(subject)
                .withBody(body)
                .withDate(LocalDateTime.now())
                .build();
        try {
            emailGateway.send(email);
            email.setStatus(Status.SENT);
        } catch (EmailValidationException e) {
            System.out.println(Status.EMAIL_VALIDATION_ERROR);
            email.setStatus(Status.EMAIL_VALIDATION_ERROR);
        } catch (EmailBodyException e) {
            System.out.println(Status.EMAIL_BODY_ERROR);
            email.setStatus(Status.EMAIL_BODY_ERROR);
        } catch (EmailServerException e) {
            System.out.println(Status.SERVER_ERROR);
            email.setStatus(Status.SERVER_ERROR);
        } catch (RuntimeException e) {
            System.out.println(Status.GENERAL_ERROR);
            email.setStatus(Status.GENERAL_ERROR);
        }
        return emailRepository.save(email);
    }
}
