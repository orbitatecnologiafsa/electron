package com.electron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electron.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}