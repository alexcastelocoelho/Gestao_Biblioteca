package com.example.api.Gestao_Biblioteca.repository;

import com.example.api.Gestao_Biblioteca.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, UUID> {
    Optional<AutorModel> findByCpf(String cpf);
}
