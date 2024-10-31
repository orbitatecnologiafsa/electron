package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "situacao_tributaria_municipal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoTributariaMunicipal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "situacao_tributaria_municipal_id")
    private Long id;

    @Column(name = "situacao_tributaria_municipal_codigo", length = 10)
    private String codigo;

    @Column(name = "situacao_tributaria_municipal_descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

}
