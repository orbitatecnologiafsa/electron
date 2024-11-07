package com.electron.domain.dtos;

import com.electron.domain.*;
import com.electron.domain.enums.ClassificacaoProduto;
import com.electron.domain.enums.TipoControle;
import com.electron.domain.enums.Unidade;
import com.electron.domain.enums.UnidadeEmbalagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private String codigo;

    private String barras;

    private String nome;

    private String descricao;

    private Unidade unidadeEntrada;

    private Unidade unidadeSaida;

    private Unidade unidadeEstocagem;

    private Unidade unidadeTributacao;

    private FatorConversao fatorConversao;

    private GrupoProdServ grupoProdServ;

    private ClassificacaoProduto classificacao;

    private Integer diasValidade;

    private TipoControle tipoControle;

    private BigDecimal precoCusto;

    private BigDecimal precoCustoMedio;

    private BigDecimal precoMargemLucro;

    private BigDecimal precoVenda;

    private BigDecimal precoRevenda;

    private BigDecimal saldo;

    private BigDecimal bloqueado;

    private BigDecimal pedidoVenda;

    private BigDecimal disponivel;

    private BigDecimal saldoIdeal;

    private String codigoAjuste;

    private TributoCest codigoCestNcm;

    private TributacaoEstadual tributacaoEstadual;

    private TributacaoFederal tributacaoFederal;

    private String observacoes;

    private String informacaoExtraBalanca;

    private UnidadeEmbalagem unidadeEmbalagem;

    private BigDecimal quantidadeEmbalagem;

    private CodigoAnp codigoAnp;

    private String referencia;

    private String localizacao;

    public Produto toProduto() {
        return new Produto(
                null, codigo, barras, nome, descricao, unidadeEntrada, unidadeSaida, unidadeEstocagem, unidadeTributacao,
                fatorConversao, grupoProdServ, classificacao, diasValidade, tipoControle, precoCusto, precoCustoMedio,
                precoMargemLucro, precoVenda, precoRevenda, saldo, bloqueado, pedidoVenda, disponivel, saldoIdeal,
                codigoAjuste, codigoCestNcm, tributacaoEstadual, tributacaoFederal, observacoes, informacaoExtraBalanca,
                unidadeEmbalagem, quantidadeEmbalagem, codigoAnp, referencia, localizacao
        );
    }
}
