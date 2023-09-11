package com.fabioaraujo.ms.email.entrypoint.dto;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EmailResponseDto {

    private UUID id;
    private String owner;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;

    public static EmailResponseDto from(Email email){
        EmailResponseDto response = new EmailResponseDto();
        response.id = email.getId();
        response.owner = email.getOwner();
        response.from = email.getFrom();
        response.to = email.getTo();
        response.subject = email.getSubject();
        response.body = email.getBody();
        response.date = email.getDate();
        response.status = email.getStatus();
        return response;
    }
}
