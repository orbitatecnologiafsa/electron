package com.electron.services;

import com.electron.domain.Endereco;
import com.electron.repositories.EnderecoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    public Endereco criar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco enderecoObj = listarPorId(id);

        enderecoObj.setTipoEndereco(endereco.getTipoEndereco());
        enderecoObj.setCep(endereco.getCep());
        enderecoObj.setLogradouro(endereco.getLogradouro());
        enderecoObj.setNumero(endereco.getNumero());
        enderecoObj.setBairro(endereco.getBairro());
        enderecoObj.setComplemento(endereco.getComplemento());
        enderecoObj.setTelefone(endereco.getTelefone());
        enderecoObj.setPessoa(endereco.getPessoa());
        enderecoObj.setMunicipio(endereco.getMunicipio());

        return enderecoRepository.save(enderecoObj);
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}