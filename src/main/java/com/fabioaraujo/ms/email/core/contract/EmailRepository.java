package com.fabioaraujo.ms.email.core.contract;

import com.fabioaraujo.ms.email.core.entity.Email;

import java.util.List;
import java.util.UUID;

public interface EmailRepository {
    Email save(Email email);
    List<Email> findAll();
    Email findById(UUID emailId);
    List<Email> findByOwner(String owner);
}
