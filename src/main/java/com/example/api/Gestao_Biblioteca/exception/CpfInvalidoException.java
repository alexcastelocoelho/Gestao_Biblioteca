package com.example.api.Gestao_Biblioteca.exception;

public class CpfInvalidoException extends RuntimeException{
    public CpfInvalidoException(String message) {
        super(message);
    }
}
