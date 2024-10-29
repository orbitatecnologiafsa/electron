package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imposto_cst_csosn")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoCst {

    @Column(name = "tributo_cst_ativo")
    private final Boolean ativo = true;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_cst_id")
    private Long id;
    @Column(name = "tributo_cst_codigo", nullable = false, unique = true, length = 10)
    private String codigo;
    @Column(name = "tributo_cst_csosn_tipo")
    private String tipo;

}