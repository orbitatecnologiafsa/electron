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
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente listarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não foi achado!"));
        return cliente;
    }

    public void criar(Cliente cliente) {
        try {

            cliente.setPfOuPj(cliente.getPfOuPj());
            cliente.setRevenda(cliente.getRevenda());
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
        
        clienteExistente.setCpfCnpj(cliente.getCpfCnpj());
        clienteExistente.setNomeRazao(cliente.getNomeRazao());
        clienteExistente.setFantasia(cliente.getFantasia());
        clienteExistente.setRevenda(cliente.getRevenda());
        clienteExistente.setPfOuPj(cliente.getPfOuPj());
        
        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

}
