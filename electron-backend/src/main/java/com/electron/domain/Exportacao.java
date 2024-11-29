package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exportação")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exportacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportacao_id")
    private Long id;

    @Column(name = "exportacao_fabricnte", length = 100)
    private String fabricante;

}