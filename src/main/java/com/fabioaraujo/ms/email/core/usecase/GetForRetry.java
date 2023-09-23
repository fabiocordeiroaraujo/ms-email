package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;

import java.util.List;

public class GetForRetry {

    private final EmailRepository emailRepository;

    public GetForRetry(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> execute(int maxRetryCount) {
        return emailRepository.findEmailsForRetry(maxRetryCount);
    }
}
