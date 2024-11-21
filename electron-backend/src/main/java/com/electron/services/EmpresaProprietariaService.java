package com.electron.services;

import com.electron.domain.EmpresaProprietaria;
import com.electron.repositories.EmpresaProprietariaRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaProprietariaService {

    private final EmpresaProprietariaRepository empresaProprietariaRepository;

    public EmpresaProprietariaService(EmpresaProprietariaRepository empresaProprietariaRepository) {
        this.empresaProprietariaRepository = empresaProprietariaRepository;
    }

    public Page<EmpresaProprietaria> listarTodas(Pageable pageable) {
        return empresaProprietariaRepository.findAll(pageable);
    }

    public EmpresaProprietaria listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return empresaProprietariaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa proprietária não encontrada com ID: " + id));
    }

    public EmpresaProprietaria criar(EmpresaProprietaria empresaProprietaria) {
        if (empresaProprietaria == null) {
            throw new IllegalArgumentException("Empresa proprietária não pode ser nula");
        }
        if (empresaProprietaria.getCpfCnpj() == null || empresaProprietaria.getCpfCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório");
        }
        if (empresaProprietariaRepository.findByCpfCnpj(empresaProprietaria.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("Já existe uma empresa com este CPF/CNPJ: " + empresaProprietaria.getCpfCnpj());
        }
        if (empresaProprietariaRepository.findByEmail(empresaProprietaria.getEmail()).isPresent()) {
            throw new AlreadyExistException("Já existe uma empresa com este email: " + empresaProprietaria.getEmail());
        }
        return empresaProprietariaRepository.save(empresaProprietaria);
    }

    public EmpresaProprietaria atualizar(Long id, EmpresaProprietaria empresaProprietaria) {
        if (id == null || empresaProprietaria == null) {
            throw new IllegalArgumentException("ID e empresa proprietária não podem ser nulos");
        }

        EmpresaProprietaria existente = listarPorId(id);
        
        if (!existente.getCpfCnpj().equals(empresaProprietaria.getCpfCnpj()) && 
            empresaProprietariaRepository.findByCpfCnpj(empresaProprietaria.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("Já existe uma empresa com este CPF/CNPJ: " + empresaProprietaria.getCpfCnpj());
        }
        
        if (!existente.getEmail().equals(empresaProprietaria.getEmail()) && 
            empresaProprietariaRepository.findByEmail(empresaProprietaria.getEmail()).isPresent()) {
            throw new AlreadyExistException("Já existe uma empresa com este email: " + empresaProprietaria.getEmail());
        }

        BeanUtils.copyProperties(empresaProprietaria, existente, "id");
        return empresaProprietariaRepository.save(existente);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!empresaProprietariaRepository.existsById(id)) {
            throw new NotFoundException("Empresa proprietária não encontrada com ID: " + id);
        }
        empresaProprietariaRepository.deleteById(id);
    }
}
