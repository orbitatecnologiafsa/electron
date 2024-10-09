package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate nascimento;
    private Float desconto = 0.00f;
    private Float comissao = 0.00f;
    private String tipoComissao;
    private String baseCalculo;

    @ManyToOne
    @JoinColumn(name = "registro_id", nullable = false)
    private Registro registro;
}
