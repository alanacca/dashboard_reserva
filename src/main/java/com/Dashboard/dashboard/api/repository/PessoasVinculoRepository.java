package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.PessoasVinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoasVinculoRepository extends JpaRepository<PessoasVinculo,Integer> {
    @Query(value = "select pe from Pessoas pe [full [outer]] join PessoasVinculo pv on pv.fk_pessoa = pe.id_pessoa where pv.fk_pessoa is null or pv.vinculo = :idVinculo",
    nativeQuery = false)
    List<Pessoas> getSemVinculoPorVinculo(@Param("idVinculo") Integer idVinculo);
}
