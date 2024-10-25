package com.electron.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributacao_federal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributacaoFederal {

    @Id
    private Long id;
    private String nome;

    @Column(precision = 10, scale = 2)
    private BigDecimal aliquota;

    //(imposto_ipi_id)
}
