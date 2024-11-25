package com.electron.mappers;

import com.electron.domain.*;
import com.electron.domain.dtos.ProdutoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper implements GenericMapper<ProdutoDTO, Produto> {

    @Override
    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();

        produto.setId(dto.getId());
        produto.setCodigo(dto.getCodigo());
        produto.setBarras(dto.getBarras());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setUnidadeEntrada(dto.getUnidadeEntrada());
        produto.setUnidadeSaida(dto.getUnidadeSaida());
        produto.setUnidadeEstocagem(dto.getUnidadeEstocagem());
        produto.setUnidadeTributacao(dto.getUnidadeTributacao());

        // Associações (usando os IDs)
        produto.setFatorConversao(new FatorConversao(dto.getFatorConversaoId()));
        produto.setGrupoProdServ(new GrupoProdServ(dto.getGrupoProdServId()));
        produto.setCodigoCestNcm(new TributoCest(dto.getCodigoCestNcmId()));
        produto.setTributacaoEstadual(new TributacaoEstadual(dto.getTributacaoEstadualId()));
        produto.setCodigoAnp(new CodigoAnp(dto.getCodigoAnpId()));

        produto.setClassificacao(dto.getClassificacao());
        produto.setDiasValidade(dto.getDiasValidade());
        produto.setTipoControle(dto.getTipoControle());
        produto.setPrecoCusto(dto.getPrecoCusto());
        produto.setPrecoCustoMedio(dto.getPrecoCustoMedio());
        produto.setPrecoMargemLucro(dto.getPrecoMargemLucro());
        produto.setPrecoVenda(dto.getPrecoVenda());
        produto.setPrecoRevenda(dto.getPrecoRevenda());
        produto.setSaldo(dto.getSaldo());
        produto.setBloqueado(dto.getBloqueado());
        produto.setPedidoVenda(dto.getPedidoVenda());
        produto.setDisponivel(dto.getDisponivel());
        produto.setSaldoIdeal(dto.getSaldoIdeal());
        produto.setCodigoAjuste(dto.getCodigoAjuste());
        produto.setObservacoes(dto.getObservacoes());
        produto.setInformacaoExtraBalanca(dto.getInformacaoExtraBalanca());
        produto.setUnidadeEmbalagem(dto.getUnidadeEmbalagem());
        produto.setQuantidadeEmbalagem(dto.getQuantidadeEmbalagem());
        produto.setReferencia(dto.getReferencia());
        produto.setLocalizacao(dto.getLocalizacao());

        return produto;
    }

    @Override
    public ProdutoDTO toDTO(Produto entity) {
        if (entity == null) {
            return null;
        }

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(entity.getId());
        produtoDTO.setCodigo(entity.getCodigo());
        produtoDTO.setBarras(entity.getBarras());
        produtoDTO.setNome(entity.getNome());
        produtoDTO.setDescricao(entity.getDescricao());
        produtoDTO.setUnidadeEntrada(entity.getUnidadeEntrada());
        produtoDTO.setUnidadeSaida(entity.getUnidadeSaida());
        produtoDTO.setUnidadeEstocagem(entity.getUnidadeEstocagem());
        produtoDTO.setUnidadeTributacao(entity.getUnidadeTributacao());

        // Associações (usando os IDs das entidades)
        if (entity.getFatorConversao() != null) {
            produtoDTO.setFatorConversaoId(entity.getFatorConversao().getId());
        }
        if (entity.getGrupoProdServ() != null) {
            produtoDTO.setGrupoProdServId(entity.getGrupoProdServ().getId());
        }
        if (entity.getCodigoCestNcm() != null) {
            produtoDTO.setCodigoCestNcmId(entity.getCodigoCestNcm().getId());
        }
        if (entity.getTributacaoEstadual() != null) {
            produtoDTO.setTributacaoEstadualId(entity.getTributacaoEstadual().getId());
        }
        if (entity.getCodigoAnp() != null) {
            produtoDTO.setCodigoAnpId(entity.getCodigoAnp().getId());
        }

        produtoDTO.setClassificacao(entity.getClassificacao());
        produtoDTO.setDiasValidade(entity.getDiasValidade());
        produtoDTO.setTipoControle(entity.getTipoControle());
        produtoDTO.setPrecoCusto(entity.getPrecoCusto());
        produtoDTO.setPrecoCustoMedio(entity.getPrecoCustoMedio());
        produtoDTO.setPrecoMargemLucro(entity.getPrecoMargemLucro());
        produtoDTO.setPrecoVenda(entity.getPrecoVenda());
        produtoDTO.setPrecoRevenda(entity.getPrecoRevenda());
        produtoDTO.setSaldo(entity.getSaldo());
        produtoDTO.setBloqueado(entity.getBloqueado());
        produtoDTO.setPedidoVenda(entity.getPedidoVenda());
        produtoDTO.setDisponivel(entity.getDisponivel());
        produtoDTO.setSaldoIdeal(entity.getSaldoIdeal());
        produtoDTO.setCodigoAjuste(entity.getCodigoAjuste());
        produtoDTO.setObservacoes(entity.getObservacoes());
        produtoDTO.setInformacaoExtraBalanca(entity.getInformacaoExtraBalanca());
        produtoDTO.setUnidadeEmbalagem(entity.getUnidadeEmbalagem());
        produtoDTO.setQuantidadeEmbalagem(entity.getQuantidadeEmbalagem());
        produtoDTO.setReferencia(entity.getReferencia());
        produtoDTO.setLocalizacao(entity.getLocalizacao());

        return produtoDTO;
    }
}
