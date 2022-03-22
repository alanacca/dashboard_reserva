package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoasRepository extends JpaRepository<Pessoas, Integer> {

    @Override
    Optional<Pessoas> findById(Integer id);
//
//    Optional<Pessoas> findByIdPlataforma(String idPlataforma);
//
//    Optional<Pessoas> findBynomeCompleto(String nomeCompleto);
//
//    boolean existsByIdPlataforma(String idPlataforma);
    @Query(value = "select pe.id_pessoa from teste.pessoas pe where pe.nome_completo = :nomeCompleto",nativeQuery = true)
    Integer findByNomeCompleto(@Param("nomeCompleto")String nomeCompleto);

    boolean existsBynomeCompleto(String nomeCompleto);
}
