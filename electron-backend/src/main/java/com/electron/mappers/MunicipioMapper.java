package com.electron.mappers;

import com.electron.domain.Municipio;
import com.electron.domain.dtos.MunicipioDTO;
import com.electron.repositories.EstadoRepository;
import org.springframework.stereotype.Component;

@Component
public class MunicipioMapper implements GenericMapper<MunicipioDTO, Municipio> {

    private final EstadoRepository estadoRepository;

    public MunicipioMapper(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Municipio toEntity(MunicipioDTO dto) {
        var municipio = new Municipio();
        municipio.setId(dto.getId());
        municipio.setNome(dto.getNome());

        var estado = estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new IllegalArgumentException("Estado não encontrado"));
        municipio.setEstado(estado);

        return municipio;
    }

    @Override
    public MunicipioDTO toDTO(Municipio entity) {
        var dto = new MunicipioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEstadoId(entity.getEstado().getId());

        return dto;
    }
}
