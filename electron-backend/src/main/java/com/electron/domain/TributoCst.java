package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tributo_cst")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoCst {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_cst_id")
    private Long id;

    @Column(name = "tributo_cst_codigo", nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(name = "tributo_cst_descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tributo_cst_csosn_tipo")
    private String tipo;

    @Column(name = "tributo_cst_ativo")
    private Boolean ativo = true;

    @Column(name = "tributo_cst_aplicacao")
    private String aplicacao;

}