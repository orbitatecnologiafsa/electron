package com.electron.domain;

import java.math.BigDecimal;

import com.electron.domain.enums.ClassificacaoProduto;
import com.electron.domain.enums.TipoControle;
import com.electron.domain.enums.Unidade;
import com.electron.domain.enums.UnidadeEmbalagem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    @org.hibernate.annotations.Index(name = "idx_produto_codigo")
    private String codigo;

    @Column(length = 20)
    @org.hibernate.annotations.Index(name = "idx_produto_barras")
    private String barras;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_entrada")
    private Unidade unidadeEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_saida")
    private Unidade unidadeSaida;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_estocagem")
    private Unidade unidadeEstocagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_tributacao")
    private Unidade unidadeTributacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_fk_fator_conversao")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FatorConversao fatorConversao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_fk_grupo_prod_serv")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private GrupoProdServ grupoProdServ;

    @Enumerated(EnumType.STRING)
    private ClassificacaoProduto classificacao;

    private Integer diasValidade;

    @Enumerated(EnumType.STRING)
    private TipoControle tipoControle;

    // Valores
    @Column(precision = 10, scale = 2)
    private BigDecimal precoCusto;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoCustoMedio;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoMargemLucro;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoRevenda;

    // Quantidades
    @Column(precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(precision = 10, scale = 2)
    private BigDecimal bloqueado;

    @Column(precision = 10, scale = 2)
    private BigDecimal pedidoVenda;

    @Column(precision = 10, scale = 2)
    private BigDecimal disponivel;

    @Column(precision = 10, scale = 2)
    private BigDecimal saldoIdeal;

    @Column(length = 100)
    private String codigoAjuste;

    // Tributação
    @ManyToOne
    @JoinColumn(name = "produtos_fk_cest_ncm")
    private TributoCest codigoCestNcm;

    @ManyToOne
    @JoinColumn(name = "produtos_fk_tributacao_estadual")
    private TributacaoEstadual tributacaoEstadual;

    @ManyToOne
    @JoinColumn(name = "produtos_fk_tributacao_federal")
    private TributacaoFederal tributacaoFederal;

    // Outras informações
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(columnDefinition = "TEXT")
    private String informacaoExtraBalanca;

    @Enumerated(EnumType.STRING)
    private UnidadeEmbalagem unidadeEmbalagem;

    @Column(precision = 10, scale = 2)
    private BigDecimal quantidadeEmbalagem;

    @ManyToOne
    @JoinColumn(name = "produtos_fk_codigo_anp")
    private CodigoAnp codigoAnp;

    @Column(length = 100)
    private String referencia;

    @Column(length = 100)
    private String localizacao;
}
