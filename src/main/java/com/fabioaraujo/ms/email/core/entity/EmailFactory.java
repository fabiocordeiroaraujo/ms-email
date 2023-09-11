package com.fabioaraujo.ms.email.core.entity;

import java.time.LocalDateTime;

public class EmailFactory {
    private String id;
    private String owner;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;

    public EmailFactory withId(String id) {
        this.id = id;
        return this;
    }

    public EmailFactory withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public EmailFactory withFrom(String from) {
        this.from = from;
        return this;
    }

    public EmailFactory withTo(String to) {
        this.to = to;
        return this;
    }

    public EmailFactory withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailFactory withBody(String body) {
        this.body = body;
        return this;
    }

    public EmailFactory withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public EmailFactory withStatus(Status status) {
        this.status = status;
        return this;
    }

    public Email build() {
        if (this.id != null)
            return new Email(this.id, this.owner, this.from, this.to, this.subject, this.body, this.date, this.status);
        else
            return new Email(this.owner, this.from, this.to, this.subject, this.body, this.date);
    }
}
