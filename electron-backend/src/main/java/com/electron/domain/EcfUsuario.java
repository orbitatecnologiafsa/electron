package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ecfs_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcfUsuario {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ecfs_empresa_usuario_cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "ecfs_usuario_nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "ecfs_usuario_email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "ecfs_usuario_ativo")
    private Boolean ativo = true;
}
