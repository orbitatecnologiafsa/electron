package com.electron.repositories;

import com.electron.domain.Dispositivo;
import com.electron.domain.GrupoProdServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GrupoProdServRepository extends JpaRepository<GrupoProdServ, Long>, PagingAndSortingRepository<GrupoProdServ, Long> {
}
