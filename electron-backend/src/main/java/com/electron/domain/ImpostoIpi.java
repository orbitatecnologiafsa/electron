package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_ipi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoIpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigo;

    private String codigoNome;

    //cst ipi int ???? sla

    @Column(precision = 10, scale = 2)
    private BigDecimal alipercentualBasealculoIpiquota;

    @Column(precision = 10, scale = 2)
    private BigDecimal percentualIpi;

    @Column(precision = 10, scale = 2)
    private BigDecimal ipiValorUnitario;

    private String ipiObservacoes;

    //FOREIGN KEY (imposto_ipi_fk_cst_ipi) REFERENCES imposto_cst_ipi(imposto_cst_ipi_id)
}
