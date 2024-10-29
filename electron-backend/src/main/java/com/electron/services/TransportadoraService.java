package com.electron.services;

import com.electron.domain.Transportadora;
import com.electron.repositories.TransportadoraRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraService {
    private final TransportadoraRepository transportadoraRepository;

    public TransportadoraService(TransportadoraRepository transportadoraRepository) {
        this.transportadoraRepository = transportadoraRepository;
    }

    // Listar todas as transportadoras
    public List<Transportadora> listarTodas() {
        return transportadoraRepository.findAll();
    }

    // Buscar transportadora por ID
    public Transportadora buscarPorId(Long id) {
        return transportadoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transportadora não encontrada com o ID: " + id));
    }

    // Criar nova transportadora
    public Transportadora salvar(Transportadora transportadora) {
        return transportadoraRepository.save(transportadora);
    }

    // Atualizar transportadora existente
    public Transportadora atualizar(Long id, Transportadora transportadoraAtualizada) {
        return transportadoraRepository.findById(id)
                .map(transportadora -> {
                    transportadora.setPessoa(transportadoraAtualizada.getPessoa());
                    transportadora.setEmpresa(transportadoraAtualizada.getEmpresa());
                    transportadora.setPlacaVeiculo(transportadoraAtualizada.getPlacaVeiculo());
                    transportadora.setAnttVeiculo(transportadoraAtualizada.getAnttVeiculo());
                    return transportadoraRepository.save(transportadora);
                })
                .orElseThrow(() -> new NotFoundException("Transportadora não encontrada com o ID: " + id));
    }

    // Excluir transportadora por ID
    public void excluir(Long id) {
        if (!transportadoraRepository.existsById(id)) {
            throw new NotFoundException("Transportadora não encontrada com o ID: " + id);
        }
        transportadoraRepository.deleteById(id);
    }

}
