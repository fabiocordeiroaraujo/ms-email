package com.fabioaraujo.ms.email.core.entity;

import com.fabioaraujo.ms.email.core.vo.EmailAddress;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email {
    private UUID id;
    private String owner;
    private EmailAddress from;
    private EmailAddress to;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;

    public Email(String id, String owner, String from, String to, String subject, String body, LocalDateTime date, Status status){
        this.id = UUID.fromString(id);
        this.owner = owner;
        this.from = new EmailAddress(from);
        this.to = new EmailAddress(to);
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.status = status;
    }

    public Email(String owner, String from, String to, String subject, String body, LocalDateTime date){
        this.owner = owner;
        this.from = new EmailAddress(from);
        this.to = new EmailAddress(to);
        this.subject = subject;
        this.body = body;
        this.date = date;
    }

    public String getFrom(){
        return from.getAddress();
    }
    public void setFrom(String address){
        from = new EmailAddress(address);
    }

    public String getTo(){
        return to.getAddress();
    }
    public void setTo(String address){
        to = new EmailAddress(address);
    }
}
