package com.electron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electron.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
