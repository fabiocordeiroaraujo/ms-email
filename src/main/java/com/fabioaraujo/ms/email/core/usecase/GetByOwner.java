package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;

import java.util.List;

public class GetByOwner {

    private final EmailRepository emailRepository;

    public GetByOwner(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> execute(String owner) {
        return emailRepository.findByOwner(owner);
    }
}
