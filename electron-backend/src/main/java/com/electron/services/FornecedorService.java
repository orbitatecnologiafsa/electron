package com.electron.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.electron.domain.Fornecedor;
import com.electron.repositories.FornecedorRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor listarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não foi encontrado!"));
    }

    public void criar(Fornecedor fornecedor) {
        try {
            fornecedor.setAtivo(fornecedor.getAtivo() != null ? fornecedor.getAtivo() : true);
            fornecedorRepository.save(fornecedor);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK9ilieyd1wui4hh2uiixixdk7m")) {
                throw new RuntimeException("Email já está em uso. Por favor, use um email diferente.", e);
            }
            throw e;
        }
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não foi encontrado!"));
        
        // Atualizar todos os campos herdados de Pessoa
        fornecedorExistente.setCpfCnpj(fornecedor.getCpfCnpj());
        fornecedorExistente.setNomeRazao(fornecedor.getNomeRazao());
        fornecedorExistente.setFantasia(fornecedor.getFantasia());
        fornecedorExistente.setRgInscricaoEstadual(fornecedor.getRgInscricaoEstadual());
        fornecedorExistente.setInscricaoEstadualMunicipal(fornecedor.getInscricaoEstadualMunicipal());
        fornecedorExistente.setContato(fornecedor.getContato());
        fornecedorExistente.setCep(fornecedor.getCep());
        fornecedorExistente.setLogradouro(fornecedor.getLogradouro());
        fornecedorExistente.setNumero(fornecedor.getNumero());
        fornecedorExistente.setBairro(fornecedor.getBairro());
        fornecedorExistente.setComplemento(fornecedor.getComplemento());
        fornecedorExistente.setUf(fornecedor.getUf());
        fornecedorExistente.setMunicipio(fornecedor.getMunicipio());
        fornecedorExistente.setTelefone(fornecedor.getTelefone());
        fornecedorExistente.setCelular(fornecedor.getCelular());
        fornecedorExistente.setEmail(fornecedor.getEmail());
        fornecedorExistente.setObservacao(fornecedor.getObservacao());
        fornecedorExistente.setAtivo(fornecedor.getAtivo());
        
        // Atualizar campo específico de Fornecedor
        fornecedorExistente.setPfOuPj(fornecedor.getPfOuPj());
        
        return fornecedorRepository.save(fornecedorExistente);
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }
}