package com.example.api.Gestao_Biblioteca.dto;

import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.model.UsuarioModel;
import com.example.api.Gestao_Biblioteca.utils.CpfInvalido;
import com.example.api.Gestao_Biblioteca.utils.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CriarEmprestimoDto {

    @NotNull
    private LivroModel livro;

    @NotNull
    private UsuarioModel usuario;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEmprestimo;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    @NotNull(message = "informe o status do emprestimo")
    private Status status;

    public LivroModel getLivro() {
        return livro;
    }

    public void setLivro(LivroModel livro) {
        this.livro = livro;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
