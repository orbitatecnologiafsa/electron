package com.electron.repositories;

import com.electron.domain.Dispositivo;
import com.electron.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>, PagingAndSortingRepository<Endereco, Long> {
}
