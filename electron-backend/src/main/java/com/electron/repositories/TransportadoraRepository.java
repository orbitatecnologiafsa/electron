package com.electron.repositories;

import com.electron.domain.Transportadora;
import com.electron.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    Optional<Transportadora> findByTelefone(String telefone);

    Optional<Transportadora> findByCelular(String celular);

    Optional<Transportadora> findByEmail(String email);

    Optional<Transportadora> findByCpfCnpj(String cpfCnpj);
}
