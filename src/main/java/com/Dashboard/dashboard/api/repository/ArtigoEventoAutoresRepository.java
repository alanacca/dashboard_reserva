package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.Curriculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ArtigoEventoAutoresRepository extends JpaRepository<ArtigoEventoAutores, Integer> {

    @Query("select a from ArtigoEventoAutores a where a.curriculo.id = :fkCurriculo")
    List<ArtigoEventoAutores> getByCurriculo(@Param("fkCurriculo") Long fkCurriculo);

    @Query("select count(a) from ArtigoEventoAutores a where a.curriculo.id = :fkCurriculo")
    Integer countArtigoEvento(@Param("fkCurriculo") Long fkCurriculo);

}
