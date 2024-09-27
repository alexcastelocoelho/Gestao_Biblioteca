package com.example.api.Gestao_Biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AutorNaoLocalizado.class)
    public ResponseEntity<String> autorNaoLocalizado(AutorNaoLocalizado exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        if (exception.getMessage() != null && exception.getMessage().contains("UUID string too large")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O id fornecido é muito grande, verifique o formato inserido");
        }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de argumento inválido.");
    }

}
