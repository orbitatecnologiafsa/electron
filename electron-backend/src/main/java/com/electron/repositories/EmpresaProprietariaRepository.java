package com.electron.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electron.domain.EmpresaProprietaria;

@Repository
public interface EmpresaProprietariaRepository extends JpaRepository<EmpresaProprietaria, Long> {
    Optional<EmpresaProprietaria> findByCpfCnpj(String cpfCnpj);
    Optional<EmpresaProprietaria> findByEmail(String email);
}
