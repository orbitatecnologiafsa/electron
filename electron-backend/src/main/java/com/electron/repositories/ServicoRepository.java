package com.electron.repositories;

import com.electron.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long>, PagingAndSortingRepository<Servico, Long> {
}
