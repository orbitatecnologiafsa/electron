package com.electron.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Vendedor extends Pessoa {

    private LocalDate nascimento;
    private Float desconto = 0.00f;
    private Float comissao = 0.00f;
    private String tipoComissao;
    private String baseCalculo;

}