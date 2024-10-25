package com.electron.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_cofins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoCofins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(max = 10)
    private String codigo;

    private String nome;

    @Column(name = "percentual_basecalculo_cofins", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoCofins;

    @Column(name = "percentual_cofins", precision = 10, scale = 2)
    private BigDecimal percentualCofins;

    @Column(name = "percentual_basecalculo_cofins_st", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoCofinsSt;

    @Column(name = "percentual_cofins_st", precision = 10, scale = 2)
    private BigDecimal percentualCofinsSt;

    @Column(name = "percentual_bc_cofins_retido", precision = 10, scale = 2)
    private BigDecimal percentualBcCofinsRetido;

    @Column(name = "percentual_cofins_retido", precision = 10, scale = 2)
    private BigDecimal percentualCofinsRetido;

    @Column(name = "imposto_retencao_cofins")
    private Boolean impostoRetencaoCofins = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
