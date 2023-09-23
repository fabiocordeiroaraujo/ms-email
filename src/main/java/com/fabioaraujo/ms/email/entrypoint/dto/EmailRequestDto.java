package com.fabioaraujo.ms.email.entrypoint.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class EmailRequestDto {

    @NotBlank
    private String owner;

    @NotBlank
    @Email
    private String from;

    @NotNull
    @Size(min = 1)
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

}