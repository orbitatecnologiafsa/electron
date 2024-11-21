package com.electron.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electron.domain.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    Optional<Dispositivo> findByDispositivoNumeroSerie(String dispositivoNumeroSerie);
}
