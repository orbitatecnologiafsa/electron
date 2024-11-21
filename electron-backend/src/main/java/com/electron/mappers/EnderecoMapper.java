package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Endereco;
import com.electron.domain.dtos.EnderecoDTO;

@Component
public class EnderecoMapper implements GenericMapper<EnderecoDTO, Endereco> {
    
    @Override
    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;
        return new Endereco(
            null,
            dto.getTipoEndereco(),
            dto.getCep(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getBairro(),
            dto.getComplemento(),
            dto.getTelefone(),
            dto.getPessoa(),
            dto.getMunicipio()
        );
    }
    
    @Override
    public EnderecoDTO toDTO(Endereco entity) {
        if (entity == null) return null;
        return new EnderecoDTO(
            entity.getTipoEndereco(),
            entity.getCep(),
            entity.getLogradouro(),
            entity.getNumero(),
            entity.getBairro(),
            entity.getComplemento(),
            entity.getTelefone(),
            entity.getPessoa(),
            entity.getMunicipio()
        );
    }
} 