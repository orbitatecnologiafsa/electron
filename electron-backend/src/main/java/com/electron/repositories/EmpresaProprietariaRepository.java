package com.electron.repositories;

import com.electron.domain.Dispositivo;
import com.electron.domain.EmpresaProprietaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmpresaProprietariaRepository extends JpaRepository<EmpresaProprietaria, Long>, PagingAndSortingRepository<EmpresaProprietaria, Long> {
}
