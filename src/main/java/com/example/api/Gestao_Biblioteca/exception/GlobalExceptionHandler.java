package com.example.api.Gestao_Biblioteca.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoLocalizado.class)
    public ResponseEntity<ApiErrorResponse> recursoNaoLocalizado(RecursoNaoLocalizado ex, HttpServletRequest request, HandlerMethod method ) {
        ApiErrorResponse resposta = new ApiErrorResponse( HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI(),method.getMethod().getName(),ZonedDateTime.now());
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> badRequest(BadRequest exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<ApiErrorResponse> cpfInvalido(CpfInvalidoException ex, HttpServletRequest request, HandlerMethod method ) {
        ApiErrorResponse resposta = new ApiErrorResponse( HttpStatus.CONFLICT,ex.getMessage(),request.getRequestURI(),method.getMethod().getName(),ZonedDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<ApiErrorResponse> emailInvalido(EmailInvalidoException ex, HttpServletRequest request, HandlerMethod method ) {
        ApiErrorResponse resposta = new ApiErrorResponse( HttpStatus.CONFLICT,ex.getMessage(),request.getRequestURI(),method.getMethod().getName(),ZonedDateTime.now());
        return new ResponseEntity<>(resposta, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        if (exception.getMessage() != null && exception.getMessage().contains("UUID string too large")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O id fornecido é muito grande, verifique o formato inserido");
        }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de argumento inválido.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String erromessage = ex.getMessage();
        String erroMessagemPadrao = "valor invalido para campo" + erromessage;

         if (ex.getCause() instanceof JsonMappingException jsonMappingException){
            JsonMappingException.Reference reference = jsonMappingException.getPath().get(0);

            if (erromessage != null && erromessage.contains("Failed to deserialize java.time.LocalDate")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor ou formato errado no campo " + reference.getFieldName());
            }
            else if (erromessage != null && erromessage.contains("Cannot deserialize value of type `java.lang.Integer")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor ou formato errado no campo " + reference.getFieldName());
            } else {
            String enumValores = pegaValoresAceitosEnum(jsonMappingException);

            String campo = reference.getFieldName();
            erroMessagemPadrao = "Valor invalido para campo: " + campo + " -- Os valores aceitos são " + enumValores;
            }
        }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroMessagemPadrao);
    }

    private String pegaValoresAceitosEnum(JsonMappingException mappingException) {
        String message = mappingException.getMessage();

        return message.substring(message.indexOf("[") + 1, message.indexOf("]"));
    }

}
