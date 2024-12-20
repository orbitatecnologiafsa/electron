package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servico_atividade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_ativiade_id")
    private Long id;

    @Column(name = "servico_atividade_codigo", length = 10)
    private String codigo;

    @Column(name = "servico_atividade_nome", columnDefinition = "TEXT")
    private String nome;
}
