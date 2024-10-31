package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tributo_ncm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributoNcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_ncm_id")
    private Long id;

    @Column(name = "tributo_ncm_codigo",unique = true, nullable = false, length = 8)
    private String codigo;

    @Column(name = "tributo_ncm_descricao", columnDefinition = "TEXT")
    private String descricao;

}