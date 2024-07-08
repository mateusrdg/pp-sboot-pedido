package com.pratopronto.aplicacao.adaptatores.controllers.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}

