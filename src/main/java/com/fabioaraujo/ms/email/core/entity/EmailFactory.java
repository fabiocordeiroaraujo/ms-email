package com.fabioaraujo.ms.email.core.entity;

import java.time.LocalDateTime;
import java.util.List;

public class EmailFactory {
    private String id;
    private String owner;
    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String body;
    private LocalDateTime date;
    private Status status;
    private int retryCount;
    private LocalDateTime lastRetryDate;

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

    public EmailFactory withTo(List<String> to) {
        this.to = to;
        return this;
    }

    public EmailFactory withCc(List<String> cc) {
        this.cc = cc;
        return this;
    }

    public EmailFactory withBcc(List<String> bcc) {
        this.bcc = bcc;
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

    public EmailFactory withRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public EmailFactory withLastRetryDate(LocalDateTime lastRetryDate) {
        this.lastRetryDate = lastRetryDate;
        return this;
    }

    public Email build() {
        if (this.id != null)
            return new Email(this.id, this.owner, this.from, this.to, this.cc, this.bcc, this.subject, this.body, this.date, this.status, this.retryCount, this.lastRetryDate);
        else
            return new Email(this.owner, this.from, this.to, this.cc, this.bcc, this.subject, this.body, this.date);
    }
}
