package com.example.api.Gestao_Biblioteca.dto;

import com.example.api.Gestao_Biblioteca.utils.enums.Genero;
import jakarta.validation.constraints.*;

public class CriarLivroDto {

    @NotBlank
    @Size(min = 3, max = 15, message = "titulo precisa ter pelo menos 3 caracteres")
    private String titulo;

    @NotNull(message = "Por favor, informe o ano de publicação do livro")
    private Integer anoPublicacao;

    @NotNull(message = "Informe o gênero do livro, por gentileza")
    private Genero genero;

    @NotBlank
    @Size(min = 15, max = 200, message = "Faça um breve resumo sobre o livro")
    private String resumo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}