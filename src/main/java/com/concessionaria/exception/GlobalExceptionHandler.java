package com.concessionaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> tratarValidacao(MethodArgumentNotValidException ex) {
        List<ErroCampoDto> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErroCampoDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroValidacao(HttpStatus.BAD_REQUEST.value(), erros));
    }

    // 404 - recurso que não existe.
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroSimples> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroSimples(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // 409 - chassi, placa ou CPF já cadastrados.
    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroValidacao> tratarDuplicidade(RegistroDuplicadoException ex) {
        List<ErroCampoDto> erros = List.of(new ErroCampoDto(ex.getCampo(), ex.getMessage()));

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErroValidacao(HttpStatus.CONFLICT.value(), erros));
    }
}
