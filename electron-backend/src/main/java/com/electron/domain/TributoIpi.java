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
public class TributoIpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_ipi_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tributo_ipi_fk_codigo_enquadramento")
    private CodigoEnquadramentoIpi codigoEnquadramentoIpi;

    @ManyToOne
    @JoinColumn(name = "tributo_ipi_fk_cst_ipi")
    private CstIpi cstIpi;

    @Column(name = "tributo_ipi_percentual_basecalculo_ipi", precision = 10, scale = 4)
    private BigDecimal percentualBaseCalculoIpi;

    @Column(name = "tributo_ipi_percentual_ipi", precision = 10, scale = 4)
    private BigDecimal percentualIpi;

    @Column(name = "tributo_ipi_valor_unitario", precision = 10, scale = 4)
    private BigDecimal valorUnitario;

    @Column(name = "tributo_ipi_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
