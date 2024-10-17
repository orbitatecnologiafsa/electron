package com.electron.services;

import com.electron.domain.Estado;
import com.electron.domain.Municipio;
import com.electron.repositories.EstadoRepository;
import com.electron.repositories.MunicipioRepository;
import com.electron.services.exceptions.NotFoundException;

import java.util.List;

public class EstadoService {
    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository c) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado listarPorId(Long id){
        return estadoRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Não foi possível encontrar"));
    }

    public void criar(Estado estado) {
        try {
            estadoRepository.save(estado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Long id, Estado estado) {
       Estado estadoExistente = estadoRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Estado não encontrado!"));
        estadoExistente.setNome(estado.getNome());
    }

    public void deletar(Long id) {
        if(estadoRepository.findById(id).isPresent()) {
            estadoRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível excluir o estado de id " + id);
    }
}
