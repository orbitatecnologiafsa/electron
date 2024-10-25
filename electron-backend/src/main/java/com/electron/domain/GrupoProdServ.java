package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo_prod_serv")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoProdServ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupo_prod_serv_id")
    private Long id;

    @Column(name = "grupo_prod_serv_nome", length = 255)
    private String nome;
}
