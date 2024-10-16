package com.example.api.Gestao_Biblioteca.repository;

import com.example.api.Gestao_Biblioteca.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    boolean existsByEmail(String email);
}
