package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributacao_municipal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributacaoMunicipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributacao_municipal_id")
    private Long id;

    @Column(name = "tributacao_municipal_nome", nullable = false)
    private String nome;

    @Column(name = "tributacao_municipal_situacao_tributaria", nullable = false)
    private String situacaoTributaria;

    @Column(name = "tributacao_municipal_aliquota")
    private BigDecimal aliquota;

    @Column(name = "tributacao_municipal_percentual_base_calculo_iss")
    private BigDecimal percentualBaseCalculoIss;

    @Column(name = "tributacao_municipal_percentual_iss")
    private BigDecimal percentualIss;

    @Column(name = "tributacao_municipal_percentual_base_calculo_ir")
    private BigDecimal percentualBaseCalculoIr;

    @Column(name = "tributacao_municipal_percentual_ir")
    private BigDecimal percentualIr;

    @Column(name = "tributacao_municipal_percentual_base_calculo_csll")
    private BigDecimal percentualBaseCalculoCsll;

    @Column(name = "tributacao_municipal_percentual_csll")
    private BigDecimal percentualCsll;

    @Column(name = "tributacao_municipal_percentual_base_calculo_inss")
    private BigDecimal percentualBaseCalculoInss;

    @Column(name = "tributacao_municipal_percentual_inss")
    private BigDecimal percentualInss;

    @Column(name = "tributacao_municipal_descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tributacao_municipal_fk_municipio")
    private Municipio municipio;
}