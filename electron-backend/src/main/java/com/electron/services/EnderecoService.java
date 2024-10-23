package com.electron.services;

import com.electron.domain.Endereco;
import com.electron.repositories.EnderecoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco listarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    public List<Endereco> listarPorPessoa(Long pessoaId) {
        return enderecoRepository.findByEnderecoFkPessoas(pessoaId);
    }

    public void criar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public void atualizar(Long id, Endereco endereco) {
        Endereco enderecoObj = listarPorId(id);

        enderecoObj.setTipoEndereco(endereco.getTipoEndereco());
        enderecoObj.setEnderecoFkPessoas(endereco.getEnderecoFkPessoas());
        enderecoObj.setCep(endereco.getCep());
        enderecoObj.setLogradouro(endereco.getLogradouro());
        enderecoObj.setNumero(endereco.getNumero());
        enderecoObj.setBairro(endereco.getBairro());
        enderecoObj.setComplemento(endereco.getComplemento());
        enderecoObj.setTelefone(endereco.getTelefone());
        enderecoObj.setEnderecoFkMunicipios(endereco.getEnderecoFkMunicipios());

        enderecoRepository.save(enderecoObj);
    }

    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}