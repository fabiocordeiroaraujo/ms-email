package com.fabioaraujo.ms.email.data;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.EmailFactory;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Transactional
public class EmailRepositoryImpl implements EmailRepository {

    private final EmailJpa emailJpa;

    @Autowired
    EmailRepositoryImpl(EmailJpa emailJpa) {
        this.emailJpa = emailJpa;
    }

    @Override
    public Email save(Email email) {
        EmailModel model = new EmailModel();
        if (email.getId() != null) model.setId(email.getId());
        model.setOwner(email.getOwner());
        model.setAddressFrom(email.getFrom());
        model.setAddressTo(Arrays.toString(email.getToAsArray()));
        model.setAddressCc(Arrays.toString(email.getCcAsArray()));
        model.setAddressBcc(Arrays.toString(email.getBccAsArray()));
        model.setSubject(email.getSubject());
        model.setBody(email.getBody());
        model.setDate(email.getDate());
        model.setStatus(email.getStatus());
        emailJpa.save(model);
        return new EmailFactory()
                .withId(model.getId().toString())
                .withOwner(model.getOwner())
                .withFrom(model.getAddressFrom())
                .withTo(stringToList(model.getAddressTo()))
                .withCc(stringToList(model.getAddressCc()))
                .withBcc(stringToList(model.getAddressBcc()))
                .withSubject(model.getSubject())
                .withBody(model.getBody())
                .withDate(model.getDate())
                .withStatus(model.getStatus())
                .withRetryCount(model.getRetryCount())
                .withLastRetryDate(model.getLastRetryDate())
                .build();
    }

    @Override
    public Email update(Email email) {
        EmailModel model = new EmailModel();
        model.setId(email.getId());
        model.setOwner(email.getOwner());
        model.setAddressFrom(email.getFrom());
        model.setAddressTo(Arrays.toString(email.getToAsArray()));
        model.setAddressCc(Arrays.toString(email.getCcAsArray()));
        model.setAddressBcc(Arrays.toString(email.getBccAsArray()));
        model.setSubject(email.getSubject());
        model.setBody(email.getBody());
        model.setDate(email.getDate());
        model.setStatus(email.getStatus());
        model.setRetryCount(email.getRetryCount());
        model.setLastRetryDate(email.getLastRetryDate());
        emailJpa.save(model);
        return new EmailFactory()
                .withId(model.getId().toString())
                .withOwner(model.getOwner())
                .withFrom(model.getAddressFrom())
                .withTo(stringToList(model.getAddressTo()))
                .withCc(stringToList(model.getAddressCc()))
                .withBcc(stringToList(model.getAddressBcc()))
                .withSubject(model.getSubject())
                .withBody(model.getBody())
                .withDate(model.getDate())
                .withStatus(model.getStatus())
                .withRetryCount(model.getRetryCount())
                .withLastRetryDate(model.getLastRetryDate())
                .build();
    }

    @Override
    public List<Email> findAll() {
        List<EmailModel> modelList = emailJpa.findAll();
        List<Email> emailList = new ArrayList<>();
        for (EmailModel model : modelList) {
            Email email = new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(stringToList(model.getAddressTo()))
                    .withCc(stringToList(model.getAddressCc()))
                    .withBcc(stringToList(model.getAddressBcc()))
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .withRetryCount(model.getRetryCount())
                    .withLastRetryDate(model.getLastRetryDate())
                    .build();

            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public Email findById(UUID emailId) {
        Optional<EmailModel> modelOptional = emailJpa.findById(emailId);
        if (modelOptional.isPresent()) {
            EmailModel model = modelOptional.get();
            return new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(stringToList(model.getAddressTo()))
                    .withCc(stringToList(model.getAddressCc()))
                    .withBcc(stringToList(model.getAddressBcc()))
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .withRetryCount(model.getRetryCount())
                    .withLastRetryDate(model.getLastRetryDate())
                    .build();
        } else {
            throw new RuntimeException("Email não encontrado com o ID: " + emailId);
        }
    }

    @Override
    public List<Email> findByOwner(String owner) {
        List<EmailModel> modelList = emailJpa.findByOwner(owner);
        List<Email> emailList = new ArrayList<>();
        for (EmailModel model : modelList) {
            Email email = new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(stringToList(model.getAddressTo()))
                    .withCc(stringToList(model.getAddressCc()))
                    .withBcc(stringToList(model.getAddressBcc()))
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .withRetryCount(model.getRetryCount())
                    .withLastRetryDate(model.getLastRetryDate())
                    .build();

            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> findEmailsForRetry(int maxRetryCount) {
        List<EmailModel> modelList = emailJpa.findEmailsForRetry(maxRetryCount);
        List<Email> emailList = new ArrayList<>();
        for (EmailModel model : modelList) {
            Email email = new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(stringToList(model.getAddressTo()))
                    .withCc(stringToList(model.getAddressCc()))
                    .withBcc(stringToList(model.getAddressBcc()))
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .withRetryCount(model.getRetryCount())
                    .withLastRetryDate(model.getLastRetryDate())
                    .build();
            emailList.add(email);
        }
        return emailList;
    }

    private List<String> stringToList(String input) {
        if (input == null || input.equals("[]")) {
            return null;
        }
        String cleanInput = input.substring(1, input.length() - 1);
        String[] parts = cleanInput.split(",");
        List<String> resultList = new ArrayList<>();
        for (String part : parts) {
            String trimmedItem = part.trim();
            resultList.add(trimmedItem);
        }
        return resultList;
    }

}
