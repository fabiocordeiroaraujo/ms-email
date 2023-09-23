package com.fabioaraujo.ms.email.entrypoint.dto;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public class EmailResponseDto {

    private UUID id;
    private String owner;
    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;

    public static EmailResponseDto from(Email email){
        EmailResponseDto response = new EmailResponseDto();
        response.id = email.getId();
        response.owner = email.getOwner();
        response.from = email.getFrom();
        response.to = Arrays.stream(email.getToAsArray()).toList();
        response.cc = Arrays.stream(email.getCcAsArray()).toList();
        response.bcc = Arrays.stream(email.getBccAsArray()).toList();
        response.subject = email.getSubject();
        response.body = email.getBody();
        response.date = email.getDate();
        response.status = email.getStatus();
        return response;
    }
}
