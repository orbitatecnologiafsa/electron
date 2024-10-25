package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "codigo_anp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigoAnp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_anp_id")
    private Long id;

    @Column(name = "codigo_anp_nome", length = 20)
    private String nome;
}
