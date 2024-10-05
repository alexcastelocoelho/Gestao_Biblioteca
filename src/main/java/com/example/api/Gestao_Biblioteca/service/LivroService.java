package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.AtualizarLivroDto;
import com.example.api.Gestao_Biblioteca.dto.CriarLivroDto;
import com.example.api.Gestao_Biblioteca.exception.RecursoNaoLocalizado;
import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroModel CriarLivro(CriarLivroDto livroDto) {
        LivroModel livroModel = new LivroModel();

        BeanUtils.copyProperties(livroDto, livroModel);
        livroRepository.save(livroModel);
        return livroModel;

    }

    public List<LivroModel> listarLivros() {
        return livroRepository.findAll();
    }

    public LivroModel listarUmLivro(UUID id) {
        var livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            throw new RecursoNaoLocalizado("Livro não localizado");
        }
        return livro.get();

    }

    public LivroModel atualizar(UUID id, AtualizarLivroDto livroDto) {
        var livro = livroRepository.findById(id);
        if (livro.isPresent()){
            LivroModel livroModel = livro.get();
            BeanUtils.copyProperties(livroDto, livroModel);
            livroRepository.save(livroModel);
            return livroModel;
        } else
            throw new RecursoNaoLocalizado("Livro não localizado para atualização, verifique");
    }

    public LivroModel atualizarParcialmente(UUID id, AtualizarLivroDto livroDto) {
        var livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            var livroModel = livro.get();
            if (livroDto.resumo() != null) {
                livroModel.setResumo(livroDto.resumo());
            }

            return livroRepository.save(livroModel);
        } else {
            throw new RecursoNaoLocalizado("Livro não localizado para atualização parcial, verifique novamente");
        }
    }


    public void deletarLivro(UUID id) {
        var livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            throw new RecursoNaoLocalizado("livro não localizado");
        }
        livroRepository.delete(livro.get());
    }
}
