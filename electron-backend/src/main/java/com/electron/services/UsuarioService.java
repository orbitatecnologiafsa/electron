package com.electron.services;

import com.electron.domain.Usuario;
import com.electron.repositories.UsuarioRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario listarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + id));
        return usuario;
    }

    public void criar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if(usuarioRepository.findById(id).isPresent()){
            usuarioRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível deletar o usuário de id: " + id);

    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + id));
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        return usuarioRepository.save(usuarioExistente);
    }
}
