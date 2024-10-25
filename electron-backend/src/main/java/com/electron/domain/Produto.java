package com.electron.domain;

import com.electron.domain.enums.ClassificacaoProduto;
import com.electron.domain.enums.TipoControle;
import com.electron.domain.enums.Unidade;
import com.electron.domain.enums.UnidadeEmbalagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private String codigo;

    @Column(length = 20)
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

    @ManyToOne
    @JoinColumn(name = "produtos_fk_fator_conversao")
    private FatorConversao fatorConversao;

    @ManyToOne
    @JoinColumn(name = "produtos_fk_grupo_prod_serv")
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
    private ImpostoCestNcm codigoCestNcm;

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
