package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Caixa;
import com.electron.domain.dtos.CaixaDTO;

@Component
public class CaixaMapper implements GenericMapper<CaixaDTO, Caixa> {
    
    @Override
    public Caixa toEntity(CaixaDTO dto) {
        if (dto == null) return null;
        return new Caixa(
            null,
            dto.getTipo(),
            dto.getNumero(),
            dto.getModelo(),
            dto.getSerie(),
            dto.getAtivo(),
            dto.getDataInstalacao(),
            dto.getObservacoes(),
            dto.getEmpresaProprietaria()
        );
    }
    
    @Override
    public CaixaDTO toDTO(Caixa entity) {
        if (entity == null) return null;
        return new CaixaDTO(
            entity.getTipo(),
            entity.getNumero(),
            entity.getModelo(),
            entity.getSerie(),
            entity.getAtivo(),
            entity.getDataInstalacao(),
            entity.getObservacoes(),
            entity.getEmpresaProprietaria()
        );
    }
} 