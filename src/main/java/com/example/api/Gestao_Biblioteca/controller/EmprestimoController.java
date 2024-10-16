package com.example.api.Gestao_Biblioteca.controller;

import com.example.api.Gestao_Biblioteca.dto.AtualizarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.AtualizarEmprestimoDto;
import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.CriarEmprestimoDto;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.model.EmprestimoModel;
import com.example.api.Gestao_Biblioteca.service.AutorService;
import com.example.api.Gestao_Biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoModel> CriarEmprestimo(@RequestBody @Valid CriarEmprestimoDto emprestimoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.CriarEmprestimo(emprestimoDto));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoModel>> listarEmprestimos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.listarEmprestimos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoModel> listarUmEmprestimo(@PathVariable("id") UUID id){
      return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.listarUmEmprestimo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoModel> atualizar(@PathVariable("id") UUID id, @RequestBody @Valid AtualizarEmprestimoDto emprestimoDto) {
        var emprestimo = emprestimoService.atualizar(id, emprestimoDto);
        return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmprestimoModel> atualizarParcialmente(@PathVariable("id") UUID id,  @RequestBody  AtualizarEmprestimoDto emprestimoDto) {
        var emprestimo = emprestimoService.atualizarParcialmente(id, emprestimoDto);
        return ResponseEntity.status(HttpStatus.OK).body(emprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEmprestimo(@PathVariable("id") UUID id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Emprestimo deletado com sucesso ");
    }

}
