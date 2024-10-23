package com.electron.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.electron.domain.Cliente;
import com.electron.repositories.ClienteRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente listarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não foi encontrado!"));
    }

    public void criar(Cliente cliente) {
        try {
            cliente.setAtivo(cliente.getAtivo() != null ? cliente.getAtivo() : true);
            clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK9ilieyd1wui4hh2uiixixdk7m")) {
                throw new RuntimeException("Email já está em uso. Por favor, use um email diferente.", e);
            }
            throw e;
        }
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não foi encontrado!"));
        
        // Atualizar todos os campos herdados de Pessoa
        clienteExistente.setCpfCnpj(cliente.getCpfCnpj());
        clienteExistente.setNomeRazao(cliente.getNomeRazao());
        clienteExistente.setFantasia(cliente.getFantasia());
        clienteExistente.setRgInscricaoEstadual(cliente.getRgInscricaoEstadual());
        //clienteExistente.setInscricaoEstadualMunicipal(cliente.getInscricaoEstadualMunicipal());
        clienteExistente.setContato(cliente.getContato());
        clienteExistente.setCep(cliente.getCep());
        clienteExistente.setLogradouro(cliente.getLogradouro());
        clienteExistente.setNumero(cliente.getNumero());
        clienteExistente.setBairro(cliente.getBairro());
        clienteExistente.setComplemento(cliente.getComplemento());
        clienteExistente.setUf(cliente.getUf());
        clienteExistente.setMunicipio(cliente.getMunicipio());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setCelular(cliente.getCelular());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setObservacao(cliente.getObservacao());
        clienteExistente.setAtivo(cliente.getAtivo());
        
        // Atualizar campos específicos de Cliente
        clienteExistente.setRevenda(cliente.getRevenda());
        clienteExistente.setPfOuPj(cliente.getPfOuPj());
        
        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        if(clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível excluir o cliente de id " + id);
    }
}
