package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ecf_usuario_relacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcfUsuarioRelacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relacao_fk_ecf")
    private Long relacaoFkEcf;

    @Column(name = "ecfs_fk_usuario")
    private Long ecfsFkUsuario;

    // Relacionamentos
    @ManyToOne
    @JoinColumn(name = "relacao_fk_ecf", insertable = false, updatable = false)
    private Ecf ecf;

    @ManyToOne
    @JoinColumn(name = "ecfs_fk_usuario", insertable = false, updatable = false)
    private EcfUsuario usuario;
}
