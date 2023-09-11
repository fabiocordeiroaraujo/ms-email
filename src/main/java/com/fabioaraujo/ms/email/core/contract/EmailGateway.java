package com.fabioaraujo.ms.email.core.contract;

import com.fabioaraujo.ms.email.core.entity.Email;

public interface EmailGateway {
    void send(Email email);
}