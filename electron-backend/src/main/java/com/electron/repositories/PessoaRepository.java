package com.electron.repositories;

import com.electron.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PagingAndSortingRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByCpfCnpj(String cpfCnpj);
}
