package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promocoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promocoes_id")
    private Long id;

    @Column(name = "promocoes_tipo", length = 100)
    private String tipo;

}
