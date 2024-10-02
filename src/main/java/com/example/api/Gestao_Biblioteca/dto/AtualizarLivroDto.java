package com.example.api.Gestao_Biblioteca.dto;

import jakarta.validation.constraints.*;

public record AtualizarLivroDto(

        @NotBlank
        @Size(min = 15, max = 200, message = "Faça um breve resumo sobre o livro")
        String resumo

) {
}
