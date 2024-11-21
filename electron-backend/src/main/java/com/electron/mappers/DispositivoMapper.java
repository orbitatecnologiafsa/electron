package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Dispositivo;
import com.electron.domain.dtos.DispositivoDTO;

@Component
public class DispositivoMapper implements GenericMapper<DispositivoDTO, Dispositivo> {
    
    @Override
    public Dispositivo toEntity(DispositivoDTO dto) {
        if (dto == null) return null;
        return new Dispositivo(
            null,
            dto.getEmpresaProprietaria(),
            dto.getDispositivoTipo(),
            dto.getDispositivoMarca(),
            dto.getDispositivoModelo(),
            dto.getDispositivoNumeroSerie(),
            dto.getDispositivoAtivo(),
            dto.getDispositivoDataInstalacao(),
            dto.getDispositivoFkUsuario(),
            dto.getDispositivoObservacoes()
        );
    }
    
    @Override
    public DispositivoDTO toDTO(Dispositivo entity) {
        if (entity == null) return null;
        return new DispositivoDTO(
            entity.getEmpresaProprietaria(),
            entity.getDispositivoTipo(),
            entity.getDispositivoMarca(),
            entity.getDispositivoModelo(),
            entity.getDispositivoNumeroSerie(),
            entity.getDispositivoAtivo(),
            entity.getDispositivoDataInstalacao(),
            entity.getDispositivoFkUsuario(),
            entity.getDispositivoObservacoes()
        );
    }
} 