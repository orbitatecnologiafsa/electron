package com.electron.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.electron.domain.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long>, PagingAndSortingRepository<Caixa, Long> {
}
