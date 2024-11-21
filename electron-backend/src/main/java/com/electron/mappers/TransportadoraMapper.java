package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Transportadora;
import com.electron.domain.dtos.TransportadoraDTO;

@Component
public class TransportadoraMapper implements GenericMapper<TransportadoraDTO, Transportadora> {
    
    @Override
    public Transportadora toEntity(TransportadoraDTO dto) {
        if (dto == null) return null;
        return new Transportadora(
            dto.getTipo(),
            dto.getFoto(),
            dto.getEntidade(),
            dto.getCpfCnpj(),
            dto.getNomeRazaoSocial(),
            dto.getNomeFantasia(),
            dto.getPassaporte(),
            dto.getRgInscricaoEstadual(),
            dto.getInscricaoMunicipal(),
            dto.getContato(),
            dto.getCep(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getBairro(),
            dto.getComplemento(),
            dto.getMunicipio(),
            dto.getTelefone(),
            dto.getCelular(),
            dto.getEmail(),
            dto.getDataDeNascimento(),
            dto.getObservacoes(),
            null,
            dto.getPlacaVeiculo(),
            dto.getAnttVeiculo()
        );
    }
    
    @Override
    public TransportadoraDTO toDTO(Transportadora entity) {
        if (entity == null) return null;
        return new TransportadoraDTO(
            entity.getTipo(),
            entity.getFoto(),
            entity.getEntidade(),
            entity.getCpfCnpj(),
            entity.getNomeRazaoSocial(),
            entity.getNomeFantasia(),
            entity.getPassaporte(),
            entity.getRgInscricaoEstadual(),
            entity.getInscricaoMunicipal(),
            entity.getContato(),
            entity.getCep(),
            entity.getLogradouro(),
            entity.getNumero(),
            entity.getBairro(),
            entity.getComplemento(),
            entity.getMunicipio(),
            entity.getTelefone(),
            entity.getCelular(),
            entity.getEmail(),
            entity.getDataDeNascimento(),
            entity.getObservacoes(),
            entity.getPlacaVeiculo(),
            entity.getAnttVeiculo()
        );
    }
} 