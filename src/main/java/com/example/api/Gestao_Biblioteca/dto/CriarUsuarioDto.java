package com.example.api.Gestao_Biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CriarUsuarioDto {

    @NotBlank
    @Size(min = 3, max = 15, message = "nome precisa ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank
    @Email(message = "Informe um endereço de email valido")
    private String email;

    @NotBlank
    @Size(min = 6, max = 30, message = "Senha precisa ter pelo menos 6 caracteres")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
