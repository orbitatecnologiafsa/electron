package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imposto_cfop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpostoCfop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imposto_cfop_id")
    private Long id;

    @Column(name = "imposto_cfop_codigo", nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(name = "imposto_cfop_nome")
    private String nome;

    @Column(name = "imposto_cfop_descricao")
    private String descricao;
}