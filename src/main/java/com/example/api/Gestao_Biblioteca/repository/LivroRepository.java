package com.example.api.Gestao_Biblioteca.repository;

import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, UUID> {
}
