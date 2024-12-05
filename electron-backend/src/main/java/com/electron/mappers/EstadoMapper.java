package com.electron.mappers;

import com.electron.domain.Estado;
import com.electron.domain.dtos.EstadoDTO;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper implements GenericMapper<EstadoDTO, Estado> {

    @Override
    public Estado toEntity(EstadoDTO dto) {
        if (dto == null) return null;

        return new Estado(
                dto.getId(),
                dto.getNome()
        );
    }

    @Override
    public EstadoDTO toDTO(Estado entity) {
        if (entity == null) return null;

        return new EstadoDTO(
                entity.getId(),
                entity.getNome()
        );
    }
} 