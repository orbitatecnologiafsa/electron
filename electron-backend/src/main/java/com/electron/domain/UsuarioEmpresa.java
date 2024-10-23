package com.electron.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "usuario_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "usuario_nome", nullable = false)
    private String nome;

    @Column(name = "usuario_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usuario_ativo")
    private Boolean ativo = true;

}
