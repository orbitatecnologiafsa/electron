package com.electron.mappers;

import org.springframework.stereotype.Component;

import com.electron.domain.Produto;
import com.electron.domain.dtos.ProdutoDTO;

@Component
public class ProdutoMapper implements GenericMapper<ProdutoDTO, Produto> {
    
    @Override
    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;
        return new Produto(
            null,
            dto.getCodigo(),
            dto.getBarras(),
            dto.getNome(),
            dto.getDescricao(),
            dto.getUnidadeEntrada(),
            dto.getUnidadeSaida(),
            dto.getUnidadeEstocagem(),
            dto.getUnidadeTributacao(),
            dto.getFatorConversao(),
            dto.getGrupoProdServ(),
            dto.getClassificacao(),
            dto.getDiasValidade(),
            dto.getTipoControle(),
            dto.getPrecoCusto(),
            dto.getPrecoCustoMedio(),
            dto.getPrecoMargemLucro(),
            dto.getPrecoVenda(),
            dto.getPrecoRevenda(),
            dto.getSaldo(),
            dto.getBloqueado(),
            dto.getPedidoVenda(),
            dto.getDisponivel(),
            dto.getSaldoIdeal(),
            dto.getCodigoAjuste(),
            dto.getCodigoCestNcm(),
            dto.getTributacaoEstadual(),
            dto.getTributacaoFederal(),
            dto.getObservacoes(),
            dto.getInformacaoExtraBalanca(),
            dto.getUnidadeEmbalagem(),
            dto.getQuantidadeEmbalagem(),
            dto.getCodigoAnp(),
            dto.getReferencia(),
            dto.getLocalizacao()
        );
    }
    
    @Override
    public ProdutoDTO toDTO(Produto entity) {
        if (entity == null) return null;
        return new ProdutoDTO(
            entity.getCodigo(),
            entity.getBarras(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getUnidadeEntrada(),
            entity.getUnidadeSaida(),
            entity.getUnidadeEstocagem(),
            entity.getUnidadeTributacao(),
            entity.getFatorConversao(),
            entity.getGrupoProdServ(),
            entity.getClassificacao(),
            entity.getDiasValidade(),
            entity.getTipoControle(),
            entity.getPrecoCusto(),
            entity.getPrecoCustoMedio(),
            entity.getPrecoMargemLucro(),
            entity.getPrecoVenda(),
            entity.getPrecoRevenda(),
            entity.getSaldo(),
            entity.getBloqueado(),
            entity.getPedidoVenda(),
            entity.getDisponivel(),
            entity.getSaldoIdeal(),
            entity.getCodigoAjuste(),
            entity.getCodigoCestNcm(),
            entity.getTributacaoEstadual(),
            entity.getTributacaoFederal(),
            entity.getObservacoes(),
            entity.getInformacaoExtraBalanca(),
            entity.getUnidadeEmbalagem(),
            entity.getQuantidadeEmbalagem(),
            entity.getCodigoAnp(),
            entity.getReferencia(),
            entity.getLocalizacao()
        );
    }
} 