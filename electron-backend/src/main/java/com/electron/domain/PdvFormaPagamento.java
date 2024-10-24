package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pdv_forma_pagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdvFormaPagamento {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     //  pdvfpagamento_fk_pdv_empresa INT,

    private String formaDePagamento;
}
