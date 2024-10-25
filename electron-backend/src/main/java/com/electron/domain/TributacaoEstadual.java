package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributacao_estadual")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributacaoEstadual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributacao_estadual_id")
    private Long id;

    @Column(name = "tributacao_estadual_nome", nullable = false)
    private String nome;

    @Column(name = "tributacao_estadual_aliquota")
    private BigDecimal aliquota;

    @Column(name = "tributacao_estadual_UF_origem")
    private Integer ufOrigem;

    @Column(name = "tributacao_estadual_UF_destino")
    private Integer ufDestino;

    @Column(name = "tributacao_estadual_percentual_basecalculo_ICMS")
    private BigDecimal percentualBaseCalculoIcms;

    @Column(name = "tributacao_estadual_percentual_basecalculo_ICMSST")
    private BigDecimal percentualBaseCalculoIcmsSt;

    @Column(name = "tributacao_estadual_percentual_mva_pauta")
    private BigDecimal percentualMvaPauta;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_cfop")
    private ImpostoCfop cfop;

    @Column(name = "tributacao_estadual_percentual_icms")
    private BigDecimal percentualIcms;

    @Column(name = "tributacao_estadual_percentual_ICMS_aplicado_destinatario")
    private BigDecimal percentualIcmsAplicadoDestinatario;

    @Column(name = "tributacao_estadual_percentual_diferimento")
    private BigDecimal percentualDiferimento;

    @Column(name = "tributacao_estadual_contribuinte", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private final Boolean contribuinte = false;

    @Column(name = "tributacao_estadual_nao_contribuinte", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private final Boolean naoContribuinte = false;

    @Column(name = "tributacao_estadual_isento", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private final Boolean isento = false;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_cst_csosn")
    private ImpostoCstCsosn cstCsosn;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_modalidadecalculo_ICMS_ST")
    private ImpostoIcmsSt modalidadeCalculoIcmsSt;

    @Column(name = "tributacao_estadual_percentual_fcpobreza")
    private BigDecimal percentualFcpobreza;

    @Column(name = "tributacao_estadual_descricao")
    private String descricao;
}