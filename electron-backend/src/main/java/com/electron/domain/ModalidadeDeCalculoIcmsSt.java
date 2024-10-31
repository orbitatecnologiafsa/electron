package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modalidadecalculo_icms_st")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadeDeCalculoIcmsSt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modalidadecalculo_icms_st_id")
    private Long id;

    @Column(name = "modalidadecalculo_icms_st_nome", nullable = false)
    private String nome;

    @Column(name = "modalidadecalculo_icms_st_descricao", columnDefinition = "TEXT")
    private String descricao;
}
