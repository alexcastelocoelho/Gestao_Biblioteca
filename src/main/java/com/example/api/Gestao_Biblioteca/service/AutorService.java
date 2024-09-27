package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.exception.AutorNaoLocalizado;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.repository.AutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public AutorModel CriarAutor(CriarAutorDto autorDto) {
        AutorModel autorModel = new AutorModel();

        BeanUtils.copyProperties(autorDto, autorModel);
        autorRepository.save(autorModel);
        return autorModel;

    }

    public List<AutorModel> listarAutores() {
        return autorRepository.findAll();
    }

    public AutorModel listarUmAutor(UUID id) {
        var autor = autorRepository.findById(id);
        if (autor.isEmpty()) {
            throw new AutorNaoLocalizado("Autor não localizado");
        }
        return autor.get();

    }
}
