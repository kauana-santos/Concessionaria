package com.concessionaria.exception;

import lombok.Getter;

@Getter
public class RegistroDuplicadoException extends RuntimeException {

    private final String campo;

    public RegistroDuplicadoException(String campo, String message) {
        super(message);
        this.campo = campo;
    }
}
