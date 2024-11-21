package com.electron.repositories;

import com.electron.domain.Caixa;
import com.electron.domain.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>, PagingAndSortingRepository<Dispositivo, Long> {
}
