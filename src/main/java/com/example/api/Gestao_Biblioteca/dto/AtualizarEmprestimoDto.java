package com.example.api.Gestao_Biblioteca.dto;

import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.model.UsuarioModel;
import com.example.api.Gestao_Biblioteca.utils.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarEmprestimoDto(

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataDevolucao,

        @NotNull(message = "informe o status do emprestimo")
        Status status


) {
}
