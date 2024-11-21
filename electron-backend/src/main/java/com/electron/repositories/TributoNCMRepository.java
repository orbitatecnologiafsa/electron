package com.electron.repositories;

import com.electron.domain.TributoNcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TributoNCMRepository extends JpaRepository<TributoNcm, Long>, PagingAndSortingRepository<TributoNcm, Long> {
}
