package com.fabioaraujo.ms.email.data;

import com.fabioaraujo.ms.email.core.entity.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "emails")
public class EmailModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String owner;
    private String addressFrom;
    private String addressTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    private LocalDateTime date;
    private Status status;

    public EmailModel() {
        // Default constructor
    }
}