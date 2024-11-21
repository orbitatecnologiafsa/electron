package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.dtos.EmpresaProprietariaDTO;

@Component
public class EmpresaProprietariaMapper implements GenericMapper<EmpresaProprietariaDTO, EmpresaProprietaria> {
    
    @Override
    public EmpresaProprietaria toEntity(EmpresaProprietariaDTO dto) {
        if (dto == null) return null;
        return new EmpresaProprietaria(
            null,
            dto.getTipoUnidade(),
            dto.getTipoPessoa(),
            dto.getCpfCnpj(),
            dto.getVersao(),
            dto.getRegimeTributario(),
            dto.getCnae(),
            dto.getRazaoSocial(),
            dto.getNumeroFilial(),
            dto.getDigitoVerificador(),
            dto.getAtivo(),
            dto.getNomeFantasia(),
            dto.getNomeExibicao(),
            dto.getNaturezaJuridica(),
            dto.getInscricaoEstadual(),
            dto.getInscricaoMunicipal(),
            dto.getContato(),
            dto.getTelefone(),
            dto.getEmail(),
            dto.getCep(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getBairro(),
            dto.getComplemento(),
            dto.getMunicipio(),
            dto.getChaveAcesso(),
            dto.getDataCriacao(),
            dto.getDescricaoAtividades(),
            dto.getObservacoes(),
            dto.getCaixas()
        );
    }
    
    @Override
    public EmpresaProprietariaDTO toDTO(EmpresaProprietaria entity) {
        if (entity == null) return null;
        return new EmpresaProprietariaDTO(
            entity.getTipoUnidade(),
            entity.getTipoPessoa(),
            entity.getCpfCnpj(),
            entity.getVersao(),
            entity.getRegimeTributario(),
            entity.getCnae(),
            entity.getRazaoSocial(),
            entity.getNumeroFilial(),
            entity.getDigitoVerificador(),
            entity.getAtivo(),
            entity.getNomeFantasia(),
            entity.getNomeExibicao(),
            entity.getNaturezaJuridica(),
            entity.getInscricaoEstadual(),
            entity.getInscricaoMunicipal(),
            entity.getContato(),
            entity.getTelefone(),
            entity.getEmail(),
            entity.getCep(),
            entity.getLogradouro(),
            entity.getNumero(),
            entity.getBairro(),
            entity.getComplemento(),
            entity.getMunicipio(),
            entity.getChaveAcesso(),
            entity.getDataCriacao(),
            entity.getDescricaoAtividades(),
            entity.getObservacoes(),
            entity.getCaixas()
        );
    }
} 