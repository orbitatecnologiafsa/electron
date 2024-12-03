package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributo_csll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoCsll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_csll_id")
    private Long id;

    @Column(name = "tributo_csll_percentual_basecalculo_csll", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoCsll;

    @Column(name = "tributo_csll_percentual_csll", precision = 10, scale = 2)
    private BigDecimal percentualCsll;

    @Column(name = "tributo_csll_observacoes",columnDefinition = "TEXT")
    private String observacoes;
}
