package com.electron.services;

import com.electron.domain.SubgrupoProdServ;
import com.electron.repositories.SubgrupoProdServRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubgrupoProdServService {
    private final SubgrupoProdServRepository subgrupoProdServRepository;

    public SubgrupoProdServService(SubgrupoProdServRepository subgrupoProdServRepository) {
        this.subgrupoProdServRepository = subgrupoProdServRepository;
    }

    public Page<SubgrupoProdServ> listarTodos(Pageable pageable) {
        return subgrupoProdServRepository.findAll(pageable);
    }

    public SubgrupoProdServ buscarPorId(Long id) {
        return subgrupoProdServRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    public SubgrupoProdServ salvar(SubgrupoProdServ subgrupoProdServ) {
        return subgrupoProdServRepository.save(subgrupoProdServ);
    }

    public SubgrupoProdServ atualizar(Long id, SubgrupoProdServ subgrupoProdServAtualizado) {
        return subgrupoProdServRepository.findById(id)
                .map(subgrupoProdServ -> {
                    subgrupoProdServ.setNome(subgrupoProdServAtualizado.getNome());
                    subgrupoProdServ.setGrupoProdServ(subgrupoProdServAtualizado.getGrupoProdServ());
                    return subgrupoProdServRepository.save(subgrupoProdServ);
                })
                .orElseThrow(() -> new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    public void excluir(Long id) {
        if (!subgrupoProdServRepository.existsById(id))
            throw new NotFoundException("Subgrupo de Produto/Serviço não encontrado com o ID: " + id);

        subgrupoProdServRepository.deleteById(id);
    }
}
