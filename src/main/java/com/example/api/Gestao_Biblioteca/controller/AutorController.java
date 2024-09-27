package com.example.api.Gestao_Biblioteca.controller;

import com.example.api.Gestao_Biblioteca.dto.AtualizarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {

    final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorModel> CriarAutor(@RequestBody @Valid CriarAutorDto autorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.CriarAutor(autorDto));
    }

    @GetMapping
    public ResponseEntity<List<AutorModel>> listarAutores() {
        return ResponseEntity.status(HttpStatus.OK).body(autorService.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorModel> listarUmAutor(@PathVariable("id") UUID id){
      return ResponseEntity.status(HttpStatus.OK).body(autorService.listarUmAutor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorModel> atualizar(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarAutorDto autorDto) {
        var autor = autorService.atualizar(id, autorDto);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AutorModel> atualizarParcialmente(@PathVariable("id") UUID id,  @RequestBody  AtualizarAutorDto autorDto) {
        var autor = autorService.atualizarParcialmente(id, autorDto);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }
}
