package com.fabioaraujo.ms.email.core.entity;

public enum Status {
    PROCESSING("Em processamento"),
    SENT("Enviado com sucesso"),
    EMAIL_VALIDATION_ERROR("O endereço de e-mail é inválido"),
    EMAIL_BODY_ERROR("Ocorreu um problema com o conteúdo do e-mail"),
    SERVER_ERROR("Ocorreu um problema ao conectar-se ao servidor de e-mail"),
    GENERAL_ERROR("Ocorreu um erro ao enviar o e-mail");

    private final String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}