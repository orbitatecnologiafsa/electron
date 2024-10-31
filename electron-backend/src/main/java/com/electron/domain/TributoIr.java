package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_ir")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoIr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_ir_id")
    private Long id;

    @Column(name = "tributo_ir_percentual_basecalculo_ir", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIr;

    @Column(name = "tributo_ir_percentual_ir", precision = 10, scale = 2)
    private BigDecimal percentualIr;

    @Column(name = "tributo_ir_observacoes",columnDefinition = "TEXT")
    private String observacoes;
}