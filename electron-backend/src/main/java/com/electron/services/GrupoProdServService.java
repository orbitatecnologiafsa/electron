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

    public List<GrupoProdServ> listarTodos() {
        return grupoProdServRepository.findAll();
    }

    public GrupoProdServ buscarPorId(Long id) {
        return grupoProdServRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    public GrupoProdServ salvar(GrupoProdServ grupoProdServ) {
        return grupoProdServRepository.save(grupoProdServ);
    }

    public GrupoProdServ atualizar(Long id, GrupoProdServ grupoProdServAtualizado) {
        return grupoProdServRepository.findById(id)
                .map(grupoProdServ -> {
                    grupoProdServ.setNome(grupoProdServAtualizado.getNome());
                    return grupoProdServRepository.save(grupoProdServ);
                })
                .orElseThrow(() -> new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id));
    }

    public void excluir(Long id) {
        if (!grupoProdServRepository.existsById(id)) {
            throw new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id);
        }
        grupoProdServRepository.deleteById(id);
    }
}