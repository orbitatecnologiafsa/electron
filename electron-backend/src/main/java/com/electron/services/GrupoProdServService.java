package com.electron.services;

import com.electron.domain.GrupoProdServ;
import com.electron.repositories.GrupoProdServRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoProdServService {
    private final GrupoProdServRepository grupoProdServRepository;

    public GrupoProdServService(GrupoProdServRepository grupoProdServRepository) {
        this.grupoProdServRepository = grupoProdServRepository;
    }

    public Page<GrupoProdServ> listarTodos(Pageable pageable) {
        return grupoProdServRepository.findAll(pageable);
    }

    public GrupoProdServ buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return grupoProdServRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grupo de Produto/Serviço não encontrado com ID: " + id));
    }

    public GrupoProdServ salvar(GrupoProdServ grupoProdServ) {
        if (grupoProdServ == null) {
            throw new IllegalArgumentException("Grupo não pode ser nulo");
        }
        if (grupoProdServ.getNome() == null || grupoProdServ.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do grupo é obrigatório");
        }
        if (grupoProdServRepository.findByNome(grupoProdServ.getNome()).isPresent()) {
            throw new AlreadyExistException("Já existe um grupo com este nome: " + grupoProdServ.getNome());
        }
        return grupoProdServRepository.save(grupoProdServ);
    }

    public GrupoProdServ atualizar(Long id, GrupoProdServ grupoProdServAtualizado) {
        if (id == null || grupoProdServAtualizado == null) {
            throw new IllegalArgumentException("ID e grupo não podem ser nulos");
        }

        GrupoProdServ existente = buscarPorId(id);
        
        if (!existente.getNome().equals(grupoProdServAtualizado.getNome()) && 
            grupoProdServRepository.findByNome(grupoProdServAtualizado.getNome()).isPresent()) {
            throw new AlreadyExistException("Já existe um grupo com este nome: " + grupoProdServAtualizado.getNome());
        }

        existente.setNome(grupoProdServAtualizado.getNome());
        return grupoProdServRepository.save(existente);
    }

    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!grupoProdServRepository.existsById(id)) {
            throw new NotFoundException("Grupo não encontrado com ID: " + id);
        }
        grupoProdServRepository.deleteById(id);
    }
}