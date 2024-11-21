package com.electron.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.electron.domain.TributoNcm;

public interface TributoNCMRepository extends JpaRepository<TributoNcm, Long>, PagingAndSortingRepository<TributoNcm, Long> {
    Optional<TributoNcm> findByCodigo(String codigo);
}
