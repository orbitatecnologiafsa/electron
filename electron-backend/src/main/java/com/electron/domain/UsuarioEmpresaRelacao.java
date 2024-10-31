package com.electron.domain;

import com.electron.domain.enums.TipoAcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_empresa_relacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpresaRelacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_empresa_relacao_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "relacao_tipo_acesso", nullable = false)
    private TipoAcesso tipoAcesso;

    @ManyToOne
    @JoinColumn(name = "relacao_fk_usuario_empresa")
    private UsuarioEmpresa usuarioEmpresa;

    @ManyToOne
    @JoinColumn(name = "relacao_fk_empresa")
    private EmpresaProprietaria empresaProprietaria;

}
