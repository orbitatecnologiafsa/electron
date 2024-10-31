package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profissoes_tipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissoesTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profissoes_tipo_id")
    private Long id;

    @Column(name = "profissoes_tipo_nome", nullable = false, unique = true, length = 50)
    private String nome;
}
