package com.electron.repositories;

import com.electron.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>, PagingAndSortingRepository<Vendedor, Long> {
}
