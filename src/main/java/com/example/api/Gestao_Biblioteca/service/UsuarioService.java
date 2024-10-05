package com.example.api.Gestao_Biblioteca.service;

import com.example.api.Gestao_Biblioteca.dto.AtualizarUsuarioDto;
import com.example.api.Gestao_Biblioteca.dto.CriarUsuarioDto;
import com.example.api.Gestao_Biblioteca.exception.BadRequest;
import com.example.api.Gestao_Biblioteca.exception.RecursoNaoLocalizado;
import com.example.api.Gestao_Biblioteca.model.UsuarioModel;
import com.example.api.Gestao_Biblioteca.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {


    final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel criarUsuario(CriarUsuarioDto criarUsuario) {
        UsuarioModel usuarioModel = new UsuarioModel();


        BeanUtils.copyProperties(criarUsuario, usuarioModel);
        usuarioRepository.save(usuarioModel);
        return usuarioModel;

    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel listarUmUsuario(UUID id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new RecursoNaoLocalizado("usuario não localizado");
        }
        return usuario.get();

    }

    public UsuarioModel atualizar(UUID id, AtualizarUsuarioDto usuarioDto) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            UsuarioModel usuarioModel = usuario.get();
            BeanUtils.copyProperties(usuarioDto, usuarioModel);
            usuarioRepository.save(usuarioModel);
            return usuarioModel;
        } else
            throw new RecursoNaoLocalizado("Usuario não localizado para atualização, verifique");
    }


    public UsuarioModel atualizarParcialmente(UUID id, AtualizarUsuarioDto usuarioDto) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            var usuarioModel = usuario.get();

            if (usuarioDto.nome() != null) {
                usuarioModel.setNome(usuarioDto.nome());
            }

            if (usuarioDto.senha() != null) {
                usuarioModel.setSenha(usuarioDto.senha());
            }

            return usuarioRepository.save(usuarioModel);
        } else {
            throw new RecursoNaoLocalizado("Usuario não localizado para atualização parcial, verifique novamente");
        }
    }


    public void deletarUsuario(UUID id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new RecursoNaoLocalizado("Usuario não localizado");
        }
        usuarioRepository.delete(usuario.get());
    }
}
