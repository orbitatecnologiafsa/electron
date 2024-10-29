package com.electron.services;

import com.electron.domain.SubgrupoProdServ;
import com.electron.repositories.SubgrupoProdServRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubgrupoProdServService {
    private final SubgrupoProdServRepository subgrupoProdServRepository;

    public SubgrupoProdServService(SubgrupoProdServRepository subgrupoProdServRepository) {
        this.subgrupoProdServRepository = subgrupoProdServRepository;
    }

    // Listar todos os subgrupos de produto/serviço
    public List<SubgrupoProdServ> listarTodos() {
        return subgrupoProdServRepository.findAll();
    }

    // Buscar subgrupo de produto/serviço por ID
    public SubgrupoProdServ buscarPorId(Long id) {
        return subgrupoProdServRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    // Criar novo subgrupo de produto/serviço
    public SubgrupoProdServ salvar(SubgrupoProdServ subgrupoProdServ) {
        return subgrupoProdServRepository.save(subgrupoProdServ);
    }

    // Atualizar subgrupo de produto/serviço existente
    public SubgrupoProdServ atualizar(Long id, SubgrupoProdServ subgrupoProdServAtualizado) {
        return subgrupoProdServRepository.findById(id)
                .map(subgrupoProdServ -> {
                    subgrupoProdServ.setNome(subgrupoProdServAtualizado.getNome());
                    subgrupoProdServ.setGrupoProdServ(subgrupoProdServAtualizado.getGrupoProdServ());
                    return subgrupoProdServRepository.save(subgrupoProdServ);
                })
                .orElseThrow(() -> new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    // Excluir subgrupo de produto/serviço por ID
    public void excluir(Long id) {
        if (!subgrupoProdServRepository.existsById(id)) {
            throw new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id);
        }
        subgrupoProdServRepository.deleteById(id);
    }
}
