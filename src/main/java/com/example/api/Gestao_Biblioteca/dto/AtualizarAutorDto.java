package com.example.api.Gestao_Biblioteca.dto;

import com.example.api.Gestao_Biblioteca.utils.CpfInvalido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

public record AtualizarAutorDto(


    @NotBlank
    @Size(min = 3, max = 15, message = "nome precisa ter pelo menos 3 caracteres")
    String nome,

    @NotNull
    @Min(value = 18, message = "idade não pode ser menor que 18")
    @Max(value = 95)
    Integer idade,

    @NotBlank
    @Size(min = 15, message = "Biografia precisa ter pelo menos 15 caracteres")
    String biografia

) {
}
