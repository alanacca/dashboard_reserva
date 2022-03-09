package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.PeriodicosAutores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodicosAutoresRepository extends JpaRepository<PeriodicosAutores,Integer> {

    @Query("select p from PeriodicosAutores p where p.curriculo.id = :fkCurriculo")
    List<PeriodicosAutores> getByCurriculo(@Param("fkCurriculo") Long fkCurriculo);

    @Query("select count(p) from PeriodicosAutores p where p.curriculo.id = :fkCurriculo")
    Integer countPeriodicosAutores(@Param("fkCurriculo") Long fkCurriculo);
}
