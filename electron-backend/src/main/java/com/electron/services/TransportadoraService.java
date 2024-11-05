package com.electron.services;

import com.electron.domain.Transportadora;
import com.electron.repositories.TransportadoraRepository;
import com.electron.services.exceptions.AlreadyExistException;
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

    // Criar nova transportadora com validações
    public Transportadora salvar(Transportadora transportadora) {
        validarUnicidade(transportadora);
        return transportadoraRepository.save(transportadora);
    }

    // Atualizar transportadora existente com validações
    public Transportadora atualizar(Long id, Transportadora transportadoraAtualizada) {
        Transportadora transportadoraExistente = buscarPorId(id); // Lança exceção se não encontrar
        validarUnicidadeAtualizacao(transportadoraAtualizada, transportadoraExistente);

        transportadoraExistente.setPessoa(transportadoraAtualizada.getPessoa());
        transportadoraExistente.setEmpresa(transportadoraAtualizada.getEmpresa());
        transportadoraExistente.setPlacaVeiculo(transportadoraAtualizada.getPlacaVeiculo());
        transportadoraExistente.setAnttVeiculo(transportadoraAtualizada.getAnttVeiculo());

        return transportadoraRepository.save(transportadoraExistente);
    }

    // Excluir transportadora por ID
    public void excluir(Long id) {
        if (!transportadoraRepository.existsById(id)) {
            throw new NotFoundException("Transportadora não encontrada com o ID: " + id);
        }
        transportadoraRepository.deleteById(id);
    }

    private void validarUnicidade(Transportadora transportadora) {
        if (transportadoraRepository.findByTelefone(transportadora.getTelefone()).isPresent()) {
            throw new AlreadyExistException("Telefone já está em uso: " + transportadora.getTelefone());
        }
        if (transportadoraRepository.findByCelular(transportadora.getCelular()).isPresent()) {
            throw new AlreadyExistException("Celular já está em uso: " + transportadora.getCelular());
        }
        if (transportadoraRepository.findByEmail(transportadora.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + transportadora.getEmail());
        }
        if (transportadoraRepository.findByCpfCnpj(transportadora.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + transportadora.getCpfCnpj());
        }
    }

    private void validarUnicidadeAtualizacao(Transportadora transportadoraAtualizada, Transportadora transportadoraExistente) {
        if (!transportadoraExistente.getTelefone().equals(transportadoraAtualizada.getTelefone())
                && transportadoraRepository.findByTelefone(transportadoraAtualizada.getTelefone()).isPresent()) {
            throw new AlreadyExistException("Telefone já está em uso: " + transportadoraAtualizada.getTelefone());
        }
        if (!transportadoraExistente.getCelular().equals(transportadoraAtualizada.getCelular())
                && transportadoraRepository.findByCelular(transportadoraAtualizada.getCelular()).isPresent()) {
            throw new AlreadyExistException("Celular já está em uso: " + transportadoraAtualizada.getCelular());
        }
        if (!transportadoraExistente.getEmail().equals(transportadoraAtualizada.getEmail())
                && transportadoraRepository.findByEmail(transportadoraAtualizada.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + transportadoraAtualizada.getEmail());
        }
        if (!transportadoraExistente.getCpfCnpj().equals(transportadoraAtualizada.getCpfCnpj())
                && transportadoraRepository.findByCpfCnpj(transportadoraAtualizada.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + transportadoraAtualizada.getCpfCnpj());
        }
    }
}
