package com.electron.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electron.domain.GrupoProdServ;

@Repository
public interface GrupoProdServRepository extends JpaRepository<GrupoProdServ, Long> {
    Optional<GrupoProdServ> findByNome(String nome);
}
