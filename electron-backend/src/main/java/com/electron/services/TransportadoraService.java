package com.electron.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.electron.domain.Transportadora;
import com.electron.repositories.TransportadoraRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class TransportadoraService {
    private TransportadoraRepository transportadoraRepository;

    public TransportadoraService(TransportadoraRepository transportadoraRepository) {
        this.transportadoraRepository = transportadoraRepository;
    }

    public List<Transportadora> listarTodas() {
        return transportadoraRepository.findAll();
    }

    public Transportadora listarPorId(Long id) {
        return transportadoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transportadora não foi encontrada!"));
    }

    public void criar(Transportadora transportadora) {
        try {
            transportadora.setAtivo(transportadora.getAtivo() != null ? transportadora.getAtivo() : true);
            transportadoraRepository.save(transportadora);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK9ilieyd1wui4hh2uiixixdk7m")) {
                throw new RuntimeException("Email já está em uso. Por favor, use um email diferente.", e);
            }
            throw e;
        }
    }

    public Transportadora atualizar(Long id, Transportadora transportadora) {
        Transportadora transportadoraExistente = transportadoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transportadora não foi encontrada!"));
        
        // Atualizar todos os campos herdados de Pessoa
        transportadoraExistente.setCpfCnpj(transportadora.getCpfCnpj());
        transportadoraExistente.setNomeRazao(transportadora.getNomeRazao());
        transportadoraExistente.setFantasia(transportadora.getFantasia());
        transportadoraExistente.setRgInscricaoEstadual(transportadora.getRgInscricaoEstadual());
        transportadoraExistente.setInscricaoEstadualMunicipal(transportadora.getInscricaoEstadualMunicipal());
        transportadoraExistente.setContato(transportadora.getContato());
        transportadoraExistente.setCep(transportadora.getCep());
        transportadoraExistente.setLogradouro(transportadora.getLogradouro());
        transportadoraExistente.setNumero(transportadora.getNumero());
        transportadoraExistente.setBairro(transportadora.getBairro());
        transportadoraExistente.setComplemento(transportadora.getComplemento());
        transportadoraExistente.setUf(transportadora.getUf());
        transportadoraExistente.setMunicipio(transportadora.getMunicipio());
        transportadoraExistente.setTelefone(transportadora.getTelefone());
        transportadoraExistente.setCelular(transportadora.getCelular());
        transportadoraExistente.setEmail(transportadora.getEmail());
        transportadoraExistente.setObservacao(transportadora.getObservacao());
        transportadoraExistente.setAtivo(transportadora.getAtivo());
        
        // Atualizar campos específicos de Transportadora
        transportadoraExistente.setPfOuPj(transportadora.getPfOuPj());
        
        return transportadoraRepository.save(transportadoraExistente);
    }

    public void deletar(Long id) {
        transportadoraRepository.deleteById(id);
    }
}