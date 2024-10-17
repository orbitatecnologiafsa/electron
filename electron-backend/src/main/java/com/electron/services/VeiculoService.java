package com.electron.services;

import com.electron.domain.Veiculo;
import com.electron.repositories.VeiculoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    private VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo listarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não foi encontrado!"));
    }

    public void criar(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não foi encontrado!"));
        
        veiculoExistente.setPlaca(veiculo.getPlaca());
        veiculoExistente.setUf(veiculo.getUf());
        veiculoExistente.setAntt(veiculo.getAntt());
        veiculoExistente.setTransportadora(veiculo.getTransportadora());
        
        return veiculoRepository.save(veiculoExistente);
    }

    public void deletar(Long id) {
        if(veiculoRepository.findById(id).isPresent()){
            veiculoRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível encontrar o veículo de id " + id);
    }
}