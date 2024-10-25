package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imposto_cest_ncm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpostoCestNcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imposto_cest_ncm_id")
    private Long id;

    @Column(name = "imposto_cest_codigo", nullable = false, length = 7)
    private String codigoCest;

    @Column(name = "imposto_ncm_codigo", nullable = false, length = 8)
    private String codigoNcm;

    @Column(name = "descricao_produto", nullable = false)
    private String descricaoProduto;
}