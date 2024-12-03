package com.electron.repositories;

import com.electron.domain.OutrasInformacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OutrasInformacoesRepository extends JpaRepository<OutrasInformacoes, Long>, PagingAndSortingRepository<OutrasInformacoes, Long> {
}
