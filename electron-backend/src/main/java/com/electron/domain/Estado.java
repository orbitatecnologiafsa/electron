package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estados_id")
    private Long id;

    @Column(name = "estados_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "estados_uf", nullable = false, length = 2)
    private String uf;  // Campo UF

    @Column(name = "estados_codigo_ibge", nullable = false)
    private Integer codigoIbge;  // Campo código IBGE
}
