package com.fabioaraujo.ms.email.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailDao extends JpaRepository<EmailModel, UUID> {
    List<EmailModel> findByOwner(String owner);
}
