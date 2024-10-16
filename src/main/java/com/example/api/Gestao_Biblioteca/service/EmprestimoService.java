package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.AtualizarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.AtualizarEmprestimoDto;
import com.example.api.Gestao_Biblioteca.dto.CriarAutorDto;
import com.example.api.Gestao_Biblioteca.dto.CriarEmprestimoDto;
import com.example.api.Gestao_Biblioteca.exception.BadRequest;
import com.example.api.Gestao_Biblioteca.exception.RecursoNaoLocalizado;
import com.example.api.Gestao_Biblioteca.model.AutorModel;
import com.example.api.Gestao_Biblioteca.model.EmprestimoModel;
import com.example.api.Gestao_Biblioteca.repository.AutorRepository;
import com.example.api.Gestao_Biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {

    final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public EmprestimoModel CriarEmprestimo(CriarEmprestimoDto emprestimoDto) {
        EmprestimoModel emprestimoModel = new EmprestimoModel();

        BeanUtils.copyProperties(emprestimoDto, emprestimoModel);
        emprestimoRepository.save(emprestimoModel);
        return emprestimoModel;

    }

    public List<EmprestimoModel> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public EmprestimoModel listarUmEmprestimo(UUID id) {
        var emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isEmpty()) {
            throw new RecursoNaoLocalizado("Empresimo não localizado");
        }
        return emprestimo.get();
    }



    public EmprestimoModel atualizar(UUID id, AtualizarEmprestimoDto emprestimoDto) {
        var emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()){
            EmprestimoModel emprestimoModel = emprestimo.get();
            BeanUtils.copyProperties(emprestimoDto, emprestimoModel);
            emprestimoRepository.save(emprestimoModel);
            return emprestimoModel;
        } else
            throw new RecursoNaoLocalizado("emprestimo não localizado para atualização, verifique");
    }

    public EmprestimoModel atualizarParcialmente(UUID id, AtualizarEmprestimoDto emprestimoDto) {
        var emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            var emprestimoModel = emprestimo.get();
            if (emprestimoDto.dataDevolucao() != null) {
                emprestimoModel.setDataEmprestimo(emprestimoDto.dataDevolucao());
            }

            if (emprestimoDto.status() != null) {
                emprestimoModel.setStatus(emprestimoDto.status());
            }
            return emprestimoRepository.save(emprestimoModel);
        } else {
            throw new RecursoNaoLocalizado("Emprestimo não localizado para atualização parcial, verifique novamente");
        }
    }


    public void deletarEmprestimo(UUID id) {
        var emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isEmpty()) {
            throw new RecursoNaoLocalizado("Emprestimo não localizado");
        }
        emprestimoRepository.delete(emprestimo.get());
    }

}
