package com.fabioaraujo.ms.email.core.usecase;

import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.entity.Email;

import java.util.List;

public class GetAll {

    private final EmailRepository emailRepository;

    public GetAll(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> execute() {
        return emailRepository.findAll();
    }
}
