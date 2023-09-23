package com.fabioaraujo.ms.email.rabbitmq;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.usecase.SendEmail;
import com.fabioaraujo.ms.email.entrypoint.dto.EmailRequestDto;
import com.fabioaraujo.ms.email.entrypoint.dto.EmailResponseDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
    @Autowired
    private final SendEmail sendEmail;

    public RabbitMqConsumer(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRequestDto request) {
        Email email = sendEmail.execute(request.getOwner(), request.getFrom(), request.getTo(), request.getCc(), request.getBcc(), request.getSubject(), request.getBody());
        System.out.println("Email Status: " + EmailResponseDto.from(email).getStatus());
    }
}
