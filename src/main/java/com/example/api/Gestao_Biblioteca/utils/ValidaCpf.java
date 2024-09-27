package com.example.api.Gestao_Biblioteca.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidaCpf implements ConstraintValidator<CpfInvalido, String> {


    @Override
    public void initialize(CpfInvalido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                cpf.length() != 11) {
            return false;
        }

        int sm = 0;
        int peso = 10;

        // Cálculo do 1º Dígito Verificador
        for (int i = 0; i < 9; i++) {
            sm += (cpf.charAt(i) - '0') * peso;
            peso--;
        }

        int r = 11 - (sm % 11);
        char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');


        sm = 0;
        peso = 11;

        for (int i = 0; i < 10; i++) {
            sm += (cpf.charAt(i) - '0') * peso;
            peso--;
        }

        r = 11 - (sm % 11);
        char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

        return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);
    }
}
