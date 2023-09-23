package com.fabioaraujo.ms.email.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailJpa extends JpaRepository<EmailModel, UUID> {
    List<EmailModel> findByOwner(String owner);
    @Query("SELECT e FROM EmailModel e " +
            "WHERE e.status IN ('SERVER_ERROR', 'EMAIL_BODY_ERROR', 'GENERAL_ERROR') " +
            "AND e.retryCount < :maxRetryCount")
    List<EmailModel> findEmailsForRetry(@Param("maxRetryCount") int maxRetryCount);

}
