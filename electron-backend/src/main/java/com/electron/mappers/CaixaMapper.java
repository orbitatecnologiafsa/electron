package com.electron.mappers;

import com.electron.domain.Caixa;
import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.dtos.CaixaDTO;
import com.electron.repositories.EmpresaProprietariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaixaMapper implements GenericMapper<CaixaDTO, Caixa> {

    @Autowired
    private EmpresaProprietariaRepository empresaProprietariaRepository;

    @Override
    public Caixa toEntity(CaixaDTO dto) {
        if (dto == null) return null;

        EmpresaProprietaria empresaProprietaria = empresaProprietariaRepository
                .findById(dto.getEmpresaProprietariaId())
                .orElse(null);

        return new Caixa(
                null,
                dto.getTipo(),
                dto.getNumero(),
                dto.getModelo(),
                dto.getSerie(),
                dto.getAtivo(),
                dto.getDataInstalacao(),
                dto.getObservacoes(),
                empresaProprietaria
        );
    }

    @Override
    public CaixaDTO toDTO(Caixa entity) {
        if (entity == null) return null;
        return new CaixaDTO(
                entity.getId(),
                entity.getTipo(),
                entity.getNumero(),
                entity.getModelo(),
                entity.getSerie(),
                entity.getAtivo(),
                entity.getDataInstalacao(),
                entity.getObservacoes(),
                entity.getEmpresaProprietaria() != null ? entity.getEmpresaProprietaria().getId() : null
        );
    }
} 