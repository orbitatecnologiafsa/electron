package com.electron.mappers;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Municipio;
import com.electron.domain.Transportadora;
import com.electron.domain.dtos.TransportadoraDTO;
import org.springframework.stereotype.Component;

@Component
public class TransportadoraMapper implements GenericMapper<TransportadoraDTO, Transportadora> {

    @Override
    public Transportadora toEntity(TransportadoraDTO dto) {
        if (dto == null) return null;

        Municipio municipio = new Municipio();
        municipio.setId(dto.getMunicipioId());

        EmpresaProprietaria empresa = new EmpresaProprietaria();
        empresa.setId(dto.getEmpresaId());

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
                municipio, // Usa o objeto Municipio criado
                dto.getTelefone(),
                dto.getCelular(),
                dto.getEmail(),
                dto.getDataDeNascimento(),
                dto.getObservacoes(),
                empresa,
                dto.getPlacaVeiculo(),
                dto.getAnttVeiculo()
        );
    }

    @Override
    public TransportadoraDTO toDTO(Transportadora entity) {
        if (entity == null) return null;

        return new TransportadoraDTO(
                entity.getId(),
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
                entity.getMunicipio() != null ? entity.getMunicipio().getId() : null, // Extrai o ID do Municipio
                entity.getEmpresa() != null ? entity.getEmpresa().getId() : null, // Extrai o ID do Empresa
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