package com.example.api.Gestao_Biblioteca.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidaCpf.class)
public @interface CpfInvalido {
    String message() default "Cpf invalido, por favor, verifique novamente";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
