package com.fabioaraujo.ms.email.entrypoint.controller;

import com.fabioaraujo.ms.email.core.entity.Email;
import com.fabioaraujo.ms.email.core.usecase.GetAll;
import com.fabioaraujo.ms.email.core.usecase.GetById;
import com.fabioaraujo.ms.email.core.usecase.GetByOwner;
import com.fabioaraujo.ms.email.core.usecase.SendEmail;
import com.fabioaraujo.ms.email.entrypoint.dto.EmailRequestDto;
import com.fabioaraujo.ms.email.entrypoint.dto.EmailResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ms")
public class EmailRestController {

    @Autowired
    private final SendEmail sendEmail;
    @Autowired
    private final GetById getById;
    @Autowired
    private final GetAll getAll;
    @Autowired
    private final GetByOwner getByOwner;

    public EmailRestController(SendEmail sendEmail,
                               GetById getById,
                               GetAll getAll,
                               GetByOwner getByOwner) {
        this.sendEmail = sendEmail;
        this.getById = getById;
        this.getAll = getAll;
        this.getByOwner = getByOwner;
    }

    @PostMapping("/sending-email")
    public ResponseEntity<EmailResponseDto> sendingEmail(@RequestBody @Valid EmailRequestDto request) {
        Email email = sendEmail.execute(request.getOwner(), request.getFrom(), request.getTo(), request.getCc(), request.getBcc(), request.getSubject(), request.getBody());
        return ResponseEntity.ok(EmailResponseDto.from(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailResponseDto> getById(@PathVariable String id) {
        Email email = getById.execute(id);
        return ResponseEntity.ok(EmailResponseDto.from(email));
    }

    @GetMapping("/owner/{owner}")
    public ResponseEntity<List<EmailResponseDto>> getByOwner(@PathVariable String owner) {
        List<Email> emails = getByOwner.execute(owner);
        List<EmailResponseDto> responseList = emails.stream()
                .map(EmailResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDto>> get() {
        List<Email> emails = getAll.execute();
        List<EmailResponseDto> responseList = emails.stream()
                .map(EmailResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }
}
