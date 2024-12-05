package com.electron.services;

import com.electron.domain.Estado;
import com.electron.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado buscarPorId(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado"));
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estado) {
        var estadoExistente = buscarPorId(id);

        estadoExistente.setNome(estado.getNome());
        estadoExistente.setUf(estado.getUf());

        return estadoRepository.save(estadoExistente);
    }

    public void deletar(Long id) {
        if (id != null)
            estadoRepository.deleteById(id);
    }
}
