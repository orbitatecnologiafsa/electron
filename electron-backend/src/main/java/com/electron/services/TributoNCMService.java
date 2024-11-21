package com.electron.services;

import com.electron.domain.TributoNcm;
import com.electron.repositories.TributoNCMRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TributoNCMService {

    private final TributoNCMRepository tributoNCMRepository;

    public TributoNCMService(TributoNCMRepository tributoNCMRepository) {
        this.tributoNCMRepository = tributoNCMRepository;
    }

    public Page<TributoNcm> listarTodos(Pageable pageable) {
        return tributoNCMRepository.findAll(pageable);
    }

    public TributoNcm listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return tributoNCMRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tributo NCM não encontrado com ID: " + id));
    }

    public TributoNcm criar(TributoNcm tributoNCM) {
        if (tributoNCM == null) {
            throw new IllegalArgumentException("Tributo NCM não pode ser nulo");
        }
        if (tributoNCM.getCodigo() == null || tributoNCM.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do NCM é obrigatório");
        }
        if (tributoNCMRepository.findByCodigo(tributoNCM.getCodigo()).isPresent()) {
            throw new AlreadyExistException("Já existe um NCM com este código: " + tributoNCM.getCodigo());
        }
        return tributoNCMRepository.save(tributoNCM);
    }

    public TributoNcm atualizar(Long id, TributoNcm tributoNCMAtualizado) {
        if (id == null || tributoNCMAtualizado == null) {
            throw new IllegalArgumentException("ID e tributo NCM não podem ser nulos");
        }

        TributoNcm existente = listarPorId(id);
        
        if (!existente.getCodigo().equals(tributoNCMAtualizado.getCodigo()) && 
            tributoNCMRepository.findByCodigo(tributoNCMAtualizado.getCodigo()).isPresent()) {
            throw new AlreadyExistException("Já existe um NCM com este código: " + tributoNCMAtualizado.getCodigo());
        }

        existente.setCodigo(tributoNCMAtualizado.getCodigo());
        existente.setDescricao(tributoNCMAtualizado.getDescricao());
        return tributoNCMRepository.save(existente);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!tributoNCMRepository.existsById(id)) {
            throw new NotFoundException("Tributo NCM não encontrado com ID: " + id);
        }
        tributoNCMRepository.deleteById(id);
    }

}
