package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "codigo_enquadramento_ipi")
@AllArgsConstructor
public class CodigoEnquadramentoIpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_enquadramento_ipi_id")
    private Long id;

    @Column(name = "codigo_enquadramento_ipi_codigo")
    private String codigo;

    @Column(name = "codigo_enquadramento_ipi_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
