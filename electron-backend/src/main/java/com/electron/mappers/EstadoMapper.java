package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Estado;
import com.electron.domain.dtos.EstadoDTO;

@Component
public class EstadoMapper implements GenericMapper<EstadoDTO, Estado> {
    
    @Override
    public Estado toEntity(EstadoDTO dto) {
        if (dto == null) return null;
        return new Estado(null, dto.getNome());
    }
    
    @Override
    public EstadoDTO toDTO(Estado entity) {
        if (entity == null) return null;
        return new EstadoDTO(entity.getNome());
    }
} 