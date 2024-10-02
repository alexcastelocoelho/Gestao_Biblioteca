package com.example.api.Gestao_Biblioteca.controller;

import com.example.api.Gestao_Biblioteca.dto.AtualizarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.AtualizarLivroDto;
import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.CriarLivroDto;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.service.AutorService;
import com.example.api.Gestao_Biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
public class LivroController {

    final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroModel> CriarLivro(@RequestBody @Valid CriarLivroDto livroDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.CriarLivro(livroDto));
    }

    @GetMapping
    public ResponseEntity<List<LivroModel>> listarLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.listarLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroModel> listarUmLivro(@PathVariable("id") UUID id){
      return ResponseEntity.status(HttpStatus.OK).body(livroService.listarUmLivro(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroModel> atualizar(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarLivroDto livroDto) {
        var livro = livroService.atualizar(id, livroDto);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivroModel> atualizarParcialmente(@PathVariable("id") UUID id,  @RequestBody  AtualizarLivroDto livroDto) {
        var livro = livroService.atualizarParcialmente(id, livroDto);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable("id") UUID id) {
        livroService.deletarLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso ");
    }
}
