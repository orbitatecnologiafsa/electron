package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "municipio_id")
    private Long id;

    @Column(name = "municipios_nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "municipios_fk_estados", nullable = false)
    private Estado estado;
}
