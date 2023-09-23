package com.fabioaraujo.ms.email.schedule;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.usecase.GetForRetry;
import com.fabioaraujo.ms.email.core.usecase.RetryEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailRetryService {

    @Autowired
    private final GetForRetry getForRetry;

    @Autowired
    private final RetryEmail retryEmail;

    @Value("${schedule.retry.max}")
    private int maxRetryCount;

    public EmailRetryService(GetForRetry getForRetry, RetryEmail retryEmail) {
        this.getForRetry = getForRetry;
        this.retryEmail = retryEmail;
    }
    @Scheduled(fixedRateString = "${scheduled.fixedRate}")
    public void retryFailedEmails() {
        List<Email> failedEmails = getForRetry.execute(this.maxRetryCount);
        for (Email email : failedEmails) {
            retryEmail.execute(email.getId(), email.getOwner(), email.getFrom(), email.getTo(), email.getCc(), email.getBcc(), email.getSubject(), email.getBody(), email.getRetryCount());
        }
    }
}
