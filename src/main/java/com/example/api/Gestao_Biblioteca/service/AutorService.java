package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.repository.AutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
