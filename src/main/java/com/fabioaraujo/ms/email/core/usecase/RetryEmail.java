package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.EmailFactory;
import com.fabioaraujo.ms.email.core.entity.Status;
import com.fabioaraujo.ms.email.core.exception.EmailBodyException;
import com.fabioaraujo.ms.email.core.exception.EmailServerException;
import com.fabioaraujo.ms.email.core.exception.EmailValidationException;
import com.fabioaraujo.ms.email.core.vo.EmailAddress;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RetryEmail {

    private final EmailRepository emailRepository;
    private final EmailGateway emailGateway;

    public RetryEmail(EmailRepository emailRepository, EmailGateway emailGateway) {
        this.emailRepository = emailRepository;
        this.emailGateway = emailGateway;
    }

    public void execute(UUID id, String owner, String from, List<EmailAddress> to, List<EmailAddress> cc, List<EmailAddress> bcc, String subject, String body, int retryCount) {
        Email email = new EmailFactory()
                .withId(id.toString())
                .withOwner(owner)
                .withFrom(from)
                .withTo(EmailAddress.convertToAddressList(to))
                .withCc(EmailAddress.convertToAddressList(cc))
                .withBcc(EmailAddress.convertToAddressList(bcc))
                .withSubject(subject)
                .withBody(body)
                .withDate(LocalDateTime.now())
                .withRetryCount(++retryCount)
                .withLastRetryDate(LocalDateTime.now())
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
        emailRepository.update(email);
    }
}
