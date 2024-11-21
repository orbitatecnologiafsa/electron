package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.SubgrupoProdServ;
import com.electron.domain.dtos.SubgrupoProdServDTO;
import com.electron.services.GrupoProdServService;

@Component
public class SubgrupoProdServMapper implements GenericMapper<SubgrupoProdServDTO, SubgrupoProdServ> {

    private final GrupoProdServService grupoProdServService;

    public SubgrupoProdServMapper(GrupoProdServService grupoProdServService) {
        this.grupoProdServService = grupoProdServService;
    }

    @Override
    public SubgrupoProdServ toEntity(SubgrupoProdServDTO dto) {
        if (dto == null) return null;
        
        SubgrupoProdServ entity = new SubgrupoProdServ();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        
        if (dto.getGrupoProdServId() != null) {
            entity.setGrupoProdServ(grupoProdServService.buscarPorId(dto.getGrupoProdServId()));
        }
        
        return entity;
    }

    @Override
    public SubgrupoProdServDTO toDTO(SubgrupoProdServ entity) {
        if (entity == null) return null;
        
        return new SubgrupoProdServDTO(
            entity.getId(),
            entity.getNome(),
            entity.getGrupoProdServ() != null ? entity.getGrupoProdServ().getId() : null
        );
    }
} 