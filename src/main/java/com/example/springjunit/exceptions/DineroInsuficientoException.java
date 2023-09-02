package com.example.springjunit.exceptions;

public class DineroInsuficientoException extends RuntimeException {

    public DineroInsuficientoException(String mensaje) {
        super(mensaje);
    }
}
