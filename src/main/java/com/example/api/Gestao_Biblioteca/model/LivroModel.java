package com.example.api.Gestao_Biblioteca.model;

import com.example.api.Gestao_Biblioteca.utils.enums.Genero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_livro")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(nullable = false)
    private String resumo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    private Set<EmprestimoModel> emprestimos = new HashSet<>();

    public LivroModel() {
    }

    public LivroModel(UUID id, String titulo, Integer anoPublicacao, Genero genero, String resumo) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.resumo = resumo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public AutorModel getAutor() {
        return autor;
    }

    public void setAutor(AutorModel autor) {
        this.autor = autor;
    }

    public Set<EmprestimoModel> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(Set<EmprestimoModel> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
