package com.example.api.Gestao_Biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarUsuarioDto(

        @NotBlank
        @Size(min = 3, max = 15, message = "nome precisa ter pelo menos 3 caracteres")
        String nome,

        @NotBlank
        @Size(min = 6, max = 30, message = "Senha precisa ter pelo menos 6 caracteres")
        String senha

) {
}
