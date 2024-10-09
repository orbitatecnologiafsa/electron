package com.electron.services;

import com.electron.domain.Cliente;
import com.electron.repositories.ClienteRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteVar = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não foi achado!"));
        clienteVar.setRegistro(cliente.getRegistro());
        clienteVar.setRevenda(cliente.getRevenda());
        clienteVar.setPfOuPj(cliente.getPfOuPj());
        return clienteVar;
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

}
