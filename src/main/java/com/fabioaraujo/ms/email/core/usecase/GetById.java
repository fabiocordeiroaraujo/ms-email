package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;

import java.util.UUID;

public class GetById {

    private final EmailRepository emailRepository;

    public GetById(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Email execute(String id) {
        UUID emailId = UUID.fromString(id);
        return emailRepository.findById(emailId);
    }
}
