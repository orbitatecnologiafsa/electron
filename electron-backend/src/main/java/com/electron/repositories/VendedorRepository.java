package com.electron.repositories;

import com.electron.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
