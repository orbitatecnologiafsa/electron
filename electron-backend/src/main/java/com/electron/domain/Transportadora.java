package com.electron.domain;

import java.util.ArrayList;
import java.util.List;

import com.electron.domain.enums.PForPJ;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transportadoras")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Transportadora extends Pessoa {

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

    @OneToMany(mappedBy = "transportadora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veiculo> veiculos = new ArrayList<>();

    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        veiculo.setTransportadora(this);
    }

    public void removeVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
        veiculo.setTransportadora(null);
    }
}