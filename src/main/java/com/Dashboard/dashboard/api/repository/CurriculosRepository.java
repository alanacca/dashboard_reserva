package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Curriculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurriculosRepository extends JpaRepository<Curriculos,Integer> {

    Curriculos findAllById(String numeroIdent);

    @Query("select c from Curriculos c where c.id = :idCurriculo")
    Curriculos findByCurriculo(@Param("idCurriculo") Long idCurriculo);

}
