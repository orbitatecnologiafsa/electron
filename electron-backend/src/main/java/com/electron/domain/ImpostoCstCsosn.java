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
public class ImpostoCstCsosn {

    @Column(name = "imposto_cst_csosn_ativo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private final Boolean ativo = true;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imposto_cst_csosn_id")
    private Long id;
    @Column(name = "imposto_cst_csosn_codigo", nullable = false, unique = true, length = 10)
    private String codigo;
    @Column(name = "imposto_cst_csosn_tipo")
    private String tipo;
    @Column(name = "imposto_cst_csosn_descricao")
    private String descricao;
    @Column(name = "imposto_cst_csosn_aplicacao")
    private String aplicacao;
}