package com.electron.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pdv_empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdvEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk empresa

    @NotEmpty
    private String nome;

    @NotEmpty
    private String numeroCaixa;

    //fk usuario

    @OneToOne // nao sei se é one to one, vai saber ne
    private Dispositivo dispositivo;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo;

    private LocalDateTime dataInstalacao;
}
