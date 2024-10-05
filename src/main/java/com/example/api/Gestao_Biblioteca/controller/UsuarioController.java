package com.example.api.Gestao_Biblioteca.controller;

import com.example.api.Gestao_Biblioteca.dto.AtualizarLivroDto;
import com.example.api.Gestao_Biblioteca.dto.AtualizarUsuarioDto;
import com.example.api.Gestao_Biblioteca.dto.CriarUsuarioDto;
import com.example.api.Gestao_Biblioteca.model.LivroModel;
import com.example.api.Gestao_Biblioteca.model.UsuarioModel;
import com.example.api.Gestao_Biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody @Valid CriarUsuarioDto criarUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(criarUsuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> listarUmUsuario(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUmUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizar(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarUsuarioDto usuarioDto) {
        var usuario = usuarioService.atualizar(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizarParcialmente(@PathVariable("id") UUID id,  @RequestBody  AtualizarUsuarioDto usuarioDto) {
        var usuario = usuarioService.atualizarParcialmente(id, usuarioDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso ");
    }

}
