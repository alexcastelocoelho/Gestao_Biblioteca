package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.AtualizarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.exception.CpfInvalidoException;
import com.example.api.Gestao_Biblioteca.exception.RecursoNaoLocalizado;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.repository.AutorRepository;
import org.springframework.beans.BeanUtils;
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
        var cpfexiste = autorRepository.findByCpf(autorDto.getCpf());
        if (cpfexiste.isPresent()) {
            throw new CpfInvalidoException("cpf já registrado na base de dados");
        }

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
            throw new RecursoNaoLocalizado("Autor não localizado");
        }
        return autor.get();

    }

    public AutorModel atualizar(UUID id, AtualizarAutorDto autorDto) {
        var autor =autorRepository.findById(id);
        if (autor.isPresent()){
            AutorModel autorModel = autor.get();
            BeanUtils.copyProperties(autorDto, autorModel);
            autorRepository.save(autorModel);
            return autorModel;
        } else
            throw new RecursoNaoLocalizado("Autor não localizado para atualização, verifiquei");
    }

    public AutorModel atualizarParcialmente(UUID id, AtualizarAutorDto autorDto) {
        var autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            var autorModel = autor.get();
            if (autorDto.nome() != null) {
                autorModel.setNome(autorDto.nome());
            }

            if (autorDto.idade() != null) {
                autorModel.setIdade(autorDto.idade());
            }

            if (autorDto.biografia() != null) {
                autorModel.setBiografia(autorDto.biografia());
            }

            return autorRepository.save(autorModel);
        } else {
            throw new RecursoNaoLocalizado("Autor não localizado para atualização parcial, verifique novamente");
        }
    }


    public void deletarAutor(UUID id) {
        var autor = autorRepository.findById(id);
        if (autor.isEmpty()) {
            throw new RecursoNaoLocalizado("Autor não localizado");
        }
        autorRepository.delete(autor.get());
    }
}
