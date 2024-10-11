package com.electron.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electron.domain.Transportadora;
import com.electron.domain.Veiculo;
import com.electron.repositories.TransportadoraRepository;
import com.electron.repositories.VeiculoRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class TransportadoraService {

    private final TransportadoraRepository transportadoraRepository;
    private final VeiculoRepository veiculoRepository;

    public TransportadoraService(TransportadoraRepository transportadoraRepository, VeiculoRepository veiculoRepository) {
        this.transportadoraRepository = transportadoraRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Transportadora> listarTodas() {
        return transportadoraRepository.findAll();
    }

    public Transportadora listarPorId(Long id) {
        return transportadoraRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public Transportadora criar(Transportadora transportadora) {
        if (transportadora.getVeiculos() != null) {
            for (Veiculo veiculo : transportadora.getVeiculos()) {
                veiculo.setTransportadora(transportadora);
            }
        }
        return transportadoraRepository.save(transportadora);
    }

    @Transactional
    public Transportadora atualizar(Long id, Transportadora transportadora) {
        Transportadora transportadoraExistente = transportadoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transportadora não encontrada com o ID: " + id));

        // Atualizar veículos
        if (transportadora.getVeiculos() != null) {
            transportadoraExistente.getVeiculos().clear();
            for (Veiculo veiculo : transportadora.getVeiculos()) {
                veiculo.setTransportadora(transportadoraExistente);
                transportadoraExistente.addVeiculo(veiculo);
            }
        }

        return transportadoraRepository.save(transportadoraExistente);
    }

    @Transactional
    public void deletar(Long id) {
        transportadoraRepository.deleteById(id);
    }
}