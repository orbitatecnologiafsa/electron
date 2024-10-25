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
public class ImpostoIcmsSt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modalidadecalculo_icms_st_id")
    private Long id;

    @Column(name = "modalidadecalculo_icms_st_nome")
    private String nome;

    @Column(name = "modalidadecalculo_icms_st_descricao")
    private String descricao;
}
