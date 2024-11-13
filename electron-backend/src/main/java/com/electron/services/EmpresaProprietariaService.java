package com.electron.services;

import com.electron.domain.EmpresaProprietaria;
import com.electron.repositories.EmpresaProprietariaRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaProprietariaService {

    private final EmpresaProprietariaRepository empresaProprietariaRepository;

    public EmpresaProprietariaService(EmpresaProprietariaRepository empresaProprietariaRepository) {
        this.empresaProprietariaRepository = empresaProprietariaRepository;
    }

    public List<EmpresaProprietaria> listarTodas() {
        return empresaProprietariaRepository.findAll();
    }

    public EmpresaProprietaria listarPorId(Long id) {
        return empresaProprietariaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa proprietária não encontrada"));
    }

    public void criar(EmpresaProprietaria empresaProprietaria) {
        empresaProprietariaRepository.save(empresaProprietaria);
    }

    public void atualizar(Long id, EmpresaProprietaria empresaProprietaria) {
        EmpresaProprietaria empresaObj = listarPorId(id);
        BeanUtils.copyProperties(empresaProprietaria, empresaObj, "id", "dataCriacao");
        empresaProprietariaRepository.save(empresaObj);
    }


    public void deletar(Long id) {
        empresaProprietariaRepository.deleteById(id);
    }
}
