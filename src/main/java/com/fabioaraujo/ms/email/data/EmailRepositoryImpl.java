package com.fabioaraujo.ms.email.data;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.entity.EmailFactory;
import com.fabioaraujo.ms.email.core.contract.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class EmailRepositoryImpl implements EmailRepository {

    private final EmailDao emailDao;

    @Autowired
    EmailRepositoryImpl(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Override
    public Email save(Email email) {
        EmailModel model = new EmailModel();
        model.setOwner(email.getOwner());
        model.setAddressFrom(email.getFrom());
        model.setAddressTo(email.getTo());
        model.setSubject(email.getSubject());
        model.setBody(email.getBody());
        model.setDate(email.getDate());
        model.setStatus(email.getStatus());
        emailDao.save(model);
        return new EmailFactory()
                .withId(model.getId().toString())
                .withOwner(model.getOwner())
                .withFrom(model.getAddressFrom())
                .withTo(model.getAddressTo())
                .withSubject(model.getSubject())
                .withBody(model.getBody())
                .withDate(model.getDate())
                .withStatus(model.getStatus())
                .build();
    }

    @Override
    public List<Email> findAll() {
        List<EmailModel> modelList = emailDao.findAll();
        List<Email> emailList = new ArrayList<>();
        for (EmailModel model : modelList) {
            Email email = new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(model.getAddressTo())
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .build();

            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public Email findById(UUID emailId) {
        Optional<EmailModel> modelOptional = emailDao.findById(emailId);
        if (modelOptional.isPresent()) {
            EmailModel model = modelOptional.get();
            return new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(model.getAddressTo())
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .build();
        } else {
            throw new RuntimeException("Email não encontrado com o ID: " + emailId);
        }
    }

    @Override
    public List<Email> findByOwner(String owner) {
        List<EmailModel> modelList = emailDao.findByOwner(owner);
        List<Email> emailList = new ArrayList<>();
        for (EmailModel model : modelList) {
            Email email = new EmailFactory()
                    .withId(model.getId().toString())
                    .withOwner(model.getOwner())
                    .withFrom(model.getAddressFrom())
                    .withTo(model.getAddressTo())
                    .withSubject(model.getSubject())
                    .withBody(model.getBody())
                    .withDate(model.getDate())
                    .withStatus(model.getStatus())
                    .build();

            emailList.add(email);
        }
        return emailList;
    }
}
