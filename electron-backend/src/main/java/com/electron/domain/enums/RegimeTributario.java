package com.electron.domain.enums;

public enum RegimeTributario {
    SIMPLES_NACIONAL("Simples Nacional"),
    MEI("Microempreendedor Individual (MEI)"),
    LUCRO_PRESUMIDO("Lucro Presumido"),
    LUCRO_REAL("Lucro Real");

    private final String descricao;

    RegimeTributario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}