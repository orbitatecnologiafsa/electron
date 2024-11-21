package com.electron.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electron.domain.Transportadora;
import com.electron.repositories.TransportadoraRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;

@Service
public class TransportadoraService {
    private final TransportadoraRepository transportadoraRepository;

    public TransportadoraService(TransportadoraRepository transportadoraRepository) {
        this.transportadoraRepository = transportadoraRepository;
    }

    public Page<Transportadora> listarTodas(Pageable pageable) {
        return transportadoraRepository.findAll(pageable);
    }

    public Transportadora buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return transportadoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transportadora não encontrada com ID: " + id));
    }

    public Transportadora salvar(Transportadora transportadora) {
        if (transportadora == null) {
            throw new IllegalArgumentException("Transportadora não pode ser nula");
        }
        
        if (transportadora.getCpfCnpj() == null || transportadora.getCpfCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório");
        }
        
        validarUnicidade(transportadora);
        return transportadoraRepository.save(transportadora);
    }

    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!transportadoraRepository.existsById(id)) {
            throw new NotFoundException("Transportadora não encontrada com ID: " + id);
        }
        transportadoraRepository.deleteById(id);
    }

    public Transportadora atualizar(Long id, Transportadora transportadora) {
        if (id == null || transportadora == null) {
            throw new IllegalArgumentException("ID e transportadora não podem ser nulos");
        }
        
        Transportadora existente = buscarPorId(id);
        validarUnicidadeAtualizacao(transportadora, existente);
        
        BeanUtils.copyProperties(transportadora, existente, "id");
        return transportadoraRepository.save(existente);
    }

    private void validarUnicidade(Transportadora transportadora) {
        if (transportadoraRepository.findByEmail(transportadora.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + transportadora.getEmail());
        }
        if (transportadoraRepository.findByCpfCnpj(transportadora.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + transportadora.getCpfCnpj());
        }
    }

    private void validarUnicidadeAtualizacao(Transportadora nova, Transportadora existente) {
        if (!existente.getEmail().equals(nova.getEmail()) && 
            transportadoraRepository.findByEmail(nova.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + nova.getEmail());
        }
        if (!existente.getCpfCnpj().equals(nova.getCpfCnpj()) && 
            transportadoraRepository.findByCpfCnpj(nova.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + nova.getCpfCnpj());
        }
    }
}
