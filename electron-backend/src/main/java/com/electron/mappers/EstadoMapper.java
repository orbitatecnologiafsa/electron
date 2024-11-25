package com.electron.mappers;

import com.electron.domain.Estado;
import com.electron.domain.dtos.EstadoDTO;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper implements GenericMapper<EstadoDTO, Estado> {

    @Override
    public Estado toEntity(EstadoDTO dto) {
        if (dto == null) return null;

        // Agora mapeamos todos os campos
        return new Estado(
                dto.getId(),  // Mapeando o ID
                dto.getNome(),  // Nome do estado
                dto.getUf(),  // UF
                dto.getCodigoIbge()  // Código IBGE
        );
    }

    @Override
    public EstadoDTO toDTO(Estado entity) {
        if (entity == null) return null;

        // Agora retornamos todos os campos no DTO
        return new EstadoDTO(
                entity.getId(),  // ID do estado
                entity.getNome(),  // Nome do estado
                entity.getUf(),  // UF
                entity.getCodigoIbge()  // Código IBGE
        );
    }
} 