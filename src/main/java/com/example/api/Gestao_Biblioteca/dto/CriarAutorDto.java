package com.example.api.Gestao_Biblioteca.dto;

import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.utils.CpfInvalido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CriarAutorDto {

    @NotBlank
    @Size(min = 3, max = 15, message = "nome precisa ter pelo menos 3 caracteres")
    String nome;

    @NotNull
    @Min(value = 18, message = "idade não pode ser menor que 18")
    @Max(value = 95)
    Integer idade;

    @NotBlank
    @CpfInvalido
    String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento;

    @NotBlank
    @Size(min = 15, message = "Biografia precisa ter pelo menos 15 caracteres")
    String biografia;

    @NotNull
    private Set<LivroModel> livros = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Set<LivroModel> getLivros() {
        return livros;
    }

    public void setLivros(Set<LivroModel> livros) {
        this.livros = livros;
    }
}
