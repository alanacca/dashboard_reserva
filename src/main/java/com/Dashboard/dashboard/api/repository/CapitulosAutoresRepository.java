package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import com.Dashboard.dashboard.api.repository.custom.DashBoardQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CapitulosAutoresRepository extends JpaRepository<CapitulosAutores, Integer>, DashBoardQuery {

    @Query("select a from CapitulosAutores a where a.curriculo.id = :fkCurriculo")
    List<CapitulosAutores> getByCurriculo(@Param("fkCurriculo") Long fkCurriculo);

    @Query("select count(a) from CapitulosAutores a where a.curriculo.id = :fkCurriculo")
    Integer countCapitulosAutores(@Param("fkCurriculo") Long fkCurriculo);

}
