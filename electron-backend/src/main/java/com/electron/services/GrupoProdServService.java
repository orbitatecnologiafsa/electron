package com.electron.services;

import com.electron.domain.GrupoProdServ;
import com.electron.repositories.GrupoProdServRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoProdServService {
    private final GrupoProdServRepository grupoProdServRepository;

    public GrupoProdServService(GrupoProdServRepository grupoProdServRepository) {
        this.grupoProdServRepository = grupoProdServRepository;
    }

    // Listar todos os grupos de produto/serviço
    public List<GrupoProdServ> listarTodos() {
        return grupoProdServRepository.findAll();
    }

    // Buscar grupo de produto/serviço por ID
    public GrupoProdServ buscarPorId(Long id) {
        return grupoProdServRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    // Criar novo grupo de produto/serviço
    public GrupoProdServ salvar(GrupoProdServ grupoProdServ) {
        return grupoProdServRepository.save(grupoProdServ);
    }

    // Atualizar grupo de produto/serviço existente
    public GrupoProdServ atualizar(Long id, GrupoProdServ grupoProdServAtualizado) {
        return grupoProdServRepository.findById(id)
                .map(grupoProdServ -> {
                    grupoProdServ.setNome(grupoProdServAtualizado.getNome());
                    return grupoProdServRepository.save(grupoProdServ);
                })
                .orElseThrow(() -> new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    // Excluir grupo de produto/serviço por ID
    public void excluir(Long id) {
        if (!grupoProdServRepository.existsById(id)) {
            throw new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id);
        }
        grupoProdServRepository.deleteById(id);
    }
}