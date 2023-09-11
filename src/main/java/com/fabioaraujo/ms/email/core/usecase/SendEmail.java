package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.EmailFactory;
import com.fabioaraujo.ms.email.core.entity.Status;

import java.time.LocalDateTime;

public class SendEmail {

    private final EmailRepository emailRepository;
    private final EmailGateway emailGateway;

    public SendEmail(EmailRepository emailRepository, EmailGateway emailGateway) {
        this.emailRepository = emailRepository;
        this.emailGateway = emailGateway;
    }

    public Email execute(String owner, String from, String to, String subject, String body) {
        Email email = new EmailFactory()
                .withOwner(owner)
                .withFrom(from)
                .withTo(to)
                .withSubject(subject)
                .withBody(body)
                .withDate(LocalDateTime.now())
                .build();
        try {
            emailGateway.send(email);
            email.setStatus(Status.SENT);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            email.setStatus(Status.ERROR);
        }
        return emailRepository.save(email);
    }

}
