package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Curriculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CurriculosRepository extends JpaRepository<Curriculos,Integer> {

    Curriculos findAllById(String numeroIdent);

    @Query("select c from Curriculos c where c.id = :idCurriculo")
    Curriculos findByCurriculo(@Param("idCurriculo") Long idCurriculo);

    @Query(value = "select c.nome_completo from teste.curriculos c where c.id = :idCurriculo", nativeQuery = true)
    String findNomeCompleto(@Param("idCurriculo") Long idCurriculo);

    @Query(value = "select cu.id from teste.curriculos cu inner join teste.pessoas pe \n" +
            "on unaccent(pe.nome_completo) like unaccent(cu.nome_completo) \n" +
            "and pe.mestrado = 'true' order by cu.nome_completo", nativeQuery = true)
    List<Long> findAllIdMestrado();

}
