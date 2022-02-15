package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoasRepository extends JpaRepository<Pessoas, Integer> {

    @Override
    Optional<Pessoas> findById(Integer id);

    Optional<Pessoas> findByIdPlataforma(String idPlataforma);

    Optional<Pessoas> findBynomeCompleto(String nomeCompleto);

    boolean existsByIdPlataforma(String idPlataforma);

    boolean existsBynomeCompleto(String nomeCompleto);
}
