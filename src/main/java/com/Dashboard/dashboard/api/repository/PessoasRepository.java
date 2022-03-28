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

    @Query(value = "select * from teste.pessoas pe order by pe.nome_completo", nativeQuery = true)
    List<Pessoas> findAllOrderByNomeCompleto();

//    @Query(value = "select pe from Pessoas pe where mestrado = true",nativeQuery = false)
//    List<Pessoas> getByMes

    List<Pessoas> getByMestrado(boolean mestrado);

    @Query(value = "select pe.id_pessoa from teste.pessoas pe where pe.nome_completo = :nomeCompleto",nativeQuery = true)
    Integer findByNomeCompleto(@Param("nomeCompleto")String nomeCompleto);

    boolean existsBynomeCompleto(String nomeCompleto);
}
