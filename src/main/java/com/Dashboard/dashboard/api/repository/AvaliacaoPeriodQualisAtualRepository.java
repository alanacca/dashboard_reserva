package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.AvaliacaoPeriodQualisAtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface AvaliacaoPeriodQualisAtualRepository extends JpaRepository<AvaliacaoPeriodQualisAtual,Integer> {

    @Query(value = "select awq.estrato from teste.periodicos pe inner join teste.periodicos_autores pea on pe.id = pea.fk_periodicos inner join teste.avaliacao_period_qualis_atual awq on upper(pe.titulo_periodico) " +
            "like upper(awq.periodico) and pea.fk_curriculo = :fkCurriculo" +
            " and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
    nativeQuery = true)
    List<String> estratosCurriculo(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select awq.estrato,fk_periodicos from teste.periodicos pe inner join teste.periodicos_autores pea on pe.id = pea.fk_periodicos inner join teste.avaliacao_period_qualis_atual awq on upper(pe.titulo_periodico) " +
            "like upper(awq.periodico) and pea.fk_curriculo = :fkCurriculo" +
            " and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
            nativeQuery = true)
    List<Object[]> estratosCurriculo2Forma(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select distinct pe.titulo,pe.titulo_periodico from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq on upper(pe.titulo_periodico) "+
            "like upper(awq.periodico) " +
            " and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final", nativeQuery = true)
    List<Object[]> periodicosCurriculo(@Param("fkCurriculo") Long fkCurriculo, @Param("ano_inicio")Integer ano_inicio
            , @Param("ano_final")Integer ano_final);

    @Query(value = "select distinct awq.estrato from teste.avaliacao_period_qualis_atual awq" +
            " where upper(:nomePeriod) like upper(awq.periodico)", nativeQuery = true)
    String estratoPeriodico(@Param("nomePeriod") String nomePeriod);

    @Query(value = "select count(*) from teste.pessoas pe " +
            "inner join teste.plataforma_pessoa plat " +
            "on id_pessoa = fk_pessoa " +
            "inner join teste.curriculos cur " +
            "on CAST(plat.id_plataforma as bigint) = cur.id " +
            "inner join teste.periodicos_autores pea " +
            "on pea.fk_curriculo = cur.id " +
            "and fk_periodicos = :numPeriod " +
            "and pe.doutorado = true", nativeQuery = true)
    Double countAutoresPeriodicoDoutorado(@Param("numPeriod") Integer numPeriod);

    @Query(value = "select count(*) from teste.pessoas pe " +
            "inner join teste.plataforma_pessoa plat " +
            "on id_pessoa = fk_pessoa " +
            "inner join teste.curriculos cur " +
            "on CAST(plat.id_plataforma as bigint) = cur.id " +
            "inner join teste.periodicos_autores pea " +
            "on pea.fk_curriculo = cur.id " +
            "and fk_periodicos = :numPeriod " +
            "and pe.mestrado = true", nativeQuery = true)
    Double countAutoresPeriodicoMestrado(@Param("numPeriod") Integer numPeriod);

}
