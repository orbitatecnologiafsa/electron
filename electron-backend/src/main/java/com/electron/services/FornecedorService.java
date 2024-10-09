package com.electron.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electron.domain.Fornecedor;
import com.electron.repositories.FornecedorRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedors = fornecedorRepository.findAll();
        return fornecedors;
    }

    public Fornecedor listarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Fornedor não encontrado" + id));
        return fornecedor;
    }

    public void create(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public void update(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorVar = fornecedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com ID: " + id));
        fornecedorVar.setRegistro(fornecedor.getRegistro());
        fornecedorVar.setPfOuPj(fornecedor.getPfOuPj());
    }

}
