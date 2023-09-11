package com.fabioaraujo.ms.email.app;

import com.fabioaraujo.ms.email.core.contract.EmailGateway;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import com.fabioaraujo.ms.email.core.usecase.GetAll;
import com.fabioaraujo.ms.email.core.usecase.GetById;
import com.fabioaraujo.ms.email.core.usecase.GetByOwner;
import com.fabioaraujo.ms.email.core.usecase.SendEmail;
import com.fabioaraujo.ms.email.infra.aws.EmailGatewayAWS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@EnableJpaRepositories("com.fabioaraujo.ms.email.data")
@EntityScan("com.fabioaraujo.ms.email.data")
@ComponentScan("com.fabioaraujo.ms.email")
public class EmailApp {
    public static void main(String[] args) {
        SpringApplication.run(EmailApp.class, args);
    }


    @Bean
    public GetAll getAll(EmailRepository emailRepository) {
        return new GetAll(emailRepository);
    }

    @Bean
    public GetById getById(EmailRepository emailRepository) {
        return new GetById(emailRepository);
    }

    @Bean
    public GetByOwner getByOwner(EmailRepository emailRepository) {
        return new GetByOwner(emailRepository);
    }

    @Bean
    public SendEmail sendEmail(EmailRepository emailRepository, EmailGateway emailGateway) {
        return new SendEmail(emailRepository, emailGateway);
    }

    @Bean
    public EmailGateway emailGateway(JavaMailSender javaMailSender) {
        return new EmailGatewayAWS(javaMailSender);
    }
}
