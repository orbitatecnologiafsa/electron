package com.electron.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imposto_cst_ipi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoCstIpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String codigo;

    @Column
    private boolean impostoRetencaoIpi = false;

    private String observacoes;
}
