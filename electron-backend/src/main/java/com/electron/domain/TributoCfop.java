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
public class TributoCfop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_cfop_id")
    private Long id;

    @Column(name = "tributo_cfop_codigo", nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(name = "tributo_cfop_descricao", columnDefinition = "TEXT")
    private String descricao;
}