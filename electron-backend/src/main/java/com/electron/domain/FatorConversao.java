package com.electron.domain;

import com.electron.domain.enums.Operador;
import com.electron.domain.enums.Unidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fator_de_conversao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatorConversao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fator_conversao_id")
    private Long id;

    @Column(name = "fator_conversao_descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "fator_conversao_operador")
    private Operador operador;

    @Column(name = "fator_conversao_fator")
    private Float fator;

    @Enumerated(EnumType.STRING)
    @Column(name = "fator_conversao_un_origem")
    private Unidade unidadeOrigem;

    @Enumerated(EnumType.STRING)
    @Column(name = "fator_conversao_un_convertida")
    private Unidade unidadeConvertida;
}