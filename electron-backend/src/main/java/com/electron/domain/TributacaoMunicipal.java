package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tributacao_municipal")
public class TributacaoMunicipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributacao_municipal_id")
    private Long id;

    @Column(name = "tributacao_municipal_nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "tributacao_municipal_situacao_tributaria", nullable = false, length = 100)
    private String situacaoTributaria;

    @Column(name = "tributacao_municipal_aliquota", precision = 10, scale = 2)
    private BigDecimal aliquota;

    @Column(name = "tributacao_municipal_percentual_base_calculo_iss", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIss;

    @Column(name = "tributacao_municipal_percentual_iss", precision = 10, scale = 2)
    private BigDecimal percentualIss;

    @Column(name = "tributacao_municipal_percentual_base_calculo_ir", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIr;

    @Column(name = "tributacao_municipal_percentual_ir", precision = 10, scale = 2)
    private BigDecimal percentualIr;

    @Column(name = "tributacao_municipal_percentual_base_calculo_csll", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoCsll;

    @Column(name = "tributacao_municipal_percentual_csll", precision = 10, scale = 2)
    private BigDecimal percentualCsll;

    @Column(name = "tributacao_municipal_percentual_base_calculo_inss", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoInss;

    @Column(name = "tributacao_municipal_percentual_inss", precision = 10, scale = 2)
    private BigDecimal percentualInss;

    @Column(name = "tributacao_municipal_descricao", columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tributacao_municipal_fk_municipio", nullable = false)
    private Municipio municipio;

}
