package com.electron.services;

import com.electron.domain.Estado;
import com.electron.repositories.EstadoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado listarPorId(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado!"));
    }

    public Estado criar(Estado estado) {
        try {
            return estadoRepository.save(estado);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao criar estado. Verifique os dados.", e);
        }
    }

    public Estado atualizar(Long id, Estado estado) {
        Estado estadoExistente = estadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado!"));

        estadoExistente.setNome(estado.getNome());

        return estadoRepository.save(estadoExistente);
    }

    public void deletar(Long id) {
        if (estadoRepository.findById(id).isPresent()) {
            estadoRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível deletar o estado de id " + id);
    }
}