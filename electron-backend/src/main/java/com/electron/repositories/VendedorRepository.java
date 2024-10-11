package com.electron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electron.domain.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}