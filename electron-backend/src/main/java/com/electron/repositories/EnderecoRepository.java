package com.electron.repositories;

import com.electron.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
//    List<Endereco> findByEnderecoFkPessoas(Long pessoaId);
}