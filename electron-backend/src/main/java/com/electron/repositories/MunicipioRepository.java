package com.electron.repositories;

import com.electron.domain.Estado;
import com.electron.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
