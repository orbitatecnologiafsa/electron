package com.electron.repositories;

import com.electron.domain.SubgrupoProdServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubgrupoProdServRepository extends JpaRepository<SubgrupoProdServ, Long>, PagingAndSortingRepository<SubgrupoProdServ, Long> {
}
