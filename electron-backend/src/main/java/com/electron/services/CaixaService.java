package com.electron.services;

import com.electron.domain.Caixa;
import com.electron.repositories.CaixaRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    private CaixaRepository caixaRepository;

    public CaixaService(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
    }

    public Page<Caixa> listarTodos(Pageable pageable) {
        return caixaRepository.findAll(pageable);
    }

    public Caixa listarPorId(Long id) {
        return caixaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caixa não encontrada"));
    }

    public Caixa criar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }

    public Caixa atualizar(Long id, Caixa caixa) {
        Caixa caixaObj = listarPorId(id);

        caixaObj.setAtivo(caixa.getAtivo());
        caixaObj.setModelo(caixa.getModelo());
        caixaObj.setNumero(caixa.getNumero());
        caixaObj.setSerie(caixa.getSerie());
        caixaObj.setObservacoes(caixa.getObservacoes());
        caixaObj.setDataInstalacao(caixa.getDataInstalacao());
        caixaObj.setTipo(caixa.getTipo());
        caixaObj.setEmpresaProprietaria(caixa.getEmpresaProprietaria());

        return caixaRepository.save(caixaObj);
    }

    public void deletar(Long id) {
        if (!caixaRepository.existsById(id))
            throw new NotFoundException("Grupo de Produto/Serviço não encontrado com o ID: " + id);
        caixaRepository.deleteById(id);
    }
}
