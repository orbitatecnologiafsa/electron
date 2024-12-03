package com.electron.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributo_cofins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoConfins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_cofins_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tributo_cofins_fk_cst_cofins")
    private CstCofins cstCofins;

    @Column(name = "tributo_cofins_percentual_basecalculo_cofins", precision = 10, scale = 4)
    private BigDecimal percentualBaseCalculoCofins;

    @Column(name = "tributo_cofins_percentual_cofins", precision = 10, scale = 4)
    private BigDecimal percentualCofins;

    @Column(name = "tributo_cofins_percentual_basecalculo_cofins_st", precision = 10, scale = 4)
    private BigDecimal percentualBaseCalculoCofinsSt;

    @Column(name = "tributo_cofins_percentual_cofins_st", precision = 10, scale = 4)
    private BigDecimal percentualCofinsSt;

    @Column(name = "tributo_cofins_percentual_bc_cofins_retido", precision = 10, scale = 4)
    private BigDecimal percentualBcCofinsRetido;

    @Column(name = "tributo_cofins_percentual_cofins_retido", precision = 10, scale = 4)
    private BigDecimal percentualCofinsRetido;

    @Column(name = "tributo_cofins_observacoes", columnDefinition = "TEXT")
    private String observacoes;

}
