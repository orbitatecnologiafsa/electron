package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.OutrasInformacoes;
import com.electron.domain.dtos.OutrasInformacoesDTO;

@Component
public class OutrasInformacoesMapper implements GenericMapper<OutrasInformacoesDTO, OutrasInformacoes> {
    
    @Override
    public OutrasInformacoes toEntity(OutrasInformacoesDTO dto) {
        if (dto == null) return null;
        return new OutrasInformacoes(
            null,
            dto.getPessoa(),
            dto.getEstadoCivil(),
            dto.getConjuge(),
            dto.getNomeMae(),
            dto.getNomePai(),
            dto.getLocalTrabalho(),
            dto.getOutrasProfissao(),
            dto.getDataNascimento(),
            dto.getNaturalidade(),
            dto.getDiaAcerto(),
            dto.getRendaMensal(),
            dto.getLimiteCredito(),
            dto.getCreditoDisponivel(),
            dto.getCreditoData(),
            dto.getDiaFaturamento(),
            dto.getFormaPagamento(),
            dto.getNumParcelasFaturamento(),
            dto.getReterIr(),
            dto.getReterCsll(),
            dto.getReterPrevidenciaSocial(),
            dto.getReterCofins(),
            dto.getReterPis()
        );
    }
    
    @Override
    public OutrasInformacoesDTO toDTO(OutrasInformacoes entity) {
        if (entity == null) return null;
        return new OutrasInformacoesDTO(
            entity.getPessoa(),
            entity.getEstadoCivil(),
            entity.getConjuge(),
            entity.getNomeMae(),
            entity.getNomePai(),
            entity.getLocalTrabalho(),
            entity.getOutrasProfissao(),
            entity.getDataNascimento(),
            entity.getNaturalidade(),
            entity.getDiaAcerto(),
            entity.getRendaMensal(),
            entity.getLimiteCredito(),
            entity.getCreditoDisponivel(),
            entity.getCreditoData(),
            entity.getDiaFaturamento(),
            entity.getFormaPagamento(),
            entity.getNumParcelasFaturamento(),
            entity.getReterIr(),
            entity.getReterCsll(),
            entity.getReterPrevidenciaSocial(),
            entity.getReterCofins(),
            entity.getReterPis()
        );
    }
} 