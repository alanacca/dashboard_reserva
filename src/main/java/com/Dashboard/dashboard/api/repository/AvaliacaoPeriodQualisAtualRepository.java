package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.AvaliacaoPeriodQualisAtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvaliacaoPeriodQualisAtualRepository extends JpaRepository<AvaliacaoPeriodQualisAtual,Integer> {

    @Query(value = "select awq.estrato from teste.periodicos pe inner join teste.periodicos_autores pea on pe.id = pea.fk_periodicos inner join teste.avaliacao_period_qualis_atual awq on upper(pe.titulo_periodico) " +
            "like upper(awq.periodico) and pea.fk_curriculo = :fkCurriculo" +
            " and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
    nativeQuery = true)
    List<String> estratosCurriculo(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select pe.titulo_periodico from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos" +
            " and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final", nativeQuery = true)
    List<String> periodicosCurriculo(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select awq.estrato from teste.avaliacao_period_qualis_atual awq" +
            " where upper(:nomePeriod) like upper(awq.periodico)", nativeQuery = true)
    String estratoPeriodico(@Param("nomePeriod") String nomePeriod);

}
