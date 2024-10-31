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

    @ManyToOne
    @JoinColumn(name = "tributo_ipi_fk_codigo_enquadramento")
    private CodigoEnquadramentoIpi codigoEnquadramentoIpi;

    @ManyToOne
    @JoinColumn(name = "tributo_ipi_fk_cst_ipi")
    private ImpostoCstIpi impostoCstIpi;

    @Column(name = "tributo_ipi_percentual_basecalculo_ipi", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIpi;

    @Column(name = "tributo_ipi_percentual_ipi", precision = 10, scale = 2)
    private BigDecimal percentualIpi;

    @Column(name = "tributo_ipi_valor_unitario", precision = 10, scale = 2)
    private BigDecimal ipiValorUnitario;

    @Column(name = "tributo_ipi_observacoes")
    private String ipiObservacoes;
}
