package com.electron.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electron.domain.Endereco;
import com.electron.repositories.EnderecoRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Page<Endereco> listarTodos(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    public Endereco listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado com ID: " + id));
    }

    public Endereco criar(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        if (endereco.getCep() == null || endereco.getCep().trim().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if (id == null || endereco == null) {
            throw new IllegalArgumentException("ID e endereço não podem ser nulos");
        }

        Endereco existente = listarPorId(id);
        
        existente.setTipoEndereco(endereco.getTipoEndereco());
        existente.setCep(endereco.getCep());
        existente.setLogradouro(endereco.getLogradouro());
        existente.setNumero(endereco.getNumero());
        existente.setBairro(endereco.getBairro());
        existente.setComplemento(endereco.getComplemento());
        existente.setTelefone(endereco.getTelefone());
        existente.setPessoa(endereco.getPessoa());
        existente.setMunicipio(endereco.getMunicipio());

        return enderecoRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!enderecoRepository.existsById(id))
            throw new NotFoundException("Endereço id: " + id + " não encontrado");

        enderecoRepository.deleteById(id);
    }
}