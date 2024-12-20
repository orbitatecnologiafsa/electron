package com.electron.repositories;

import com.electron.domain.Pessoa;
import com.electron.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, PagingAndSortingRepository<Produto, Long> {
  Optional<Produto> findByCodigo(String codigo);
}
