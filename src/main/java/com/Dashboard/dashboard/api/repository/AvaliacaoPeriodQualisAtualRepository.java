package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.AvaliacaoPeriodQualisAtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface AvaliacaoPeriodQualisAtualRepository extends JpaRepository<AvaliacaoPeriodQualisAtual,Integer> {


    @Query(value = "select awq.estrato from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) "+
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 "+
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
    nativeQuery = true)
    List<String> estratosCurriculoPeriodicos(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select ac.estrato from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final" ,
          nativeQuery = true)
    List<String> estratosCurriculoEventos(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final", nativeQuery = true)
    Integer countEstratosEventos(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) "+
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 "+
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
            nativeQuery = true)
    Integer countEstratosPeriodicos(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select pea.fk_periodicos,awq.estrato from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on upper(pe.titulo_periodico) like upper(awq.periodico) " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final" ,
            nativeQuery = true)
    List<Object[]> estratosCurriculo2Forma(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value = "select aea.fk_artigo_evento, ac.estrato from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) > .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final",
            nativeQuery = true)
    List<Object[]> estratosCurriculoEventos2Forma(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
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
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_curriculo = cur.id " +
            "and fk_artigo_evento = :numPeriod " +
            "and pe.doutorado = true", nativeQuery = true)
    Double countAutoresEventoDoutorado(@Param("numPeriod") Integer numPeriod);

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

    @Query(value = "select count(*) from teste.pessoas pe " +
            "inner join teste.plataforma_pessoa plat " +
            "on id_pessoa = fk_pessoa " +
            "inner join teste.curriculos cur " +
            "on CAST(plat.id_plataforma as bigint) = cur.id " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_curriculo = cur.id " +
            "and fk_artigo_evento = :numPeriod " +
            "and pe.mestrado = true", nativeQuery = true)
    Double countAutoresEventoMestrado(@Param("numPeriod") Integer numPeriod);


    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'A1'", nativeQuery = true)
    Integer countEstratosPeriodicosA1(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'A2'", nativeQuery = true)
    Integer countEstratosPeriodicosA2(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'A3'", nativeQuery = true)
    Integer countEstratosPeriodicosA3(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'A4'", nativeQuery = true)
    Integer countEstratosPeriodicosA4(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'B1'", nativeQuery = true)
    Integer countEstratosPeriodicosB1(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'B2'", nativeQuery = true)
    Integer countEstratosPeriodicosB2(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'B3'", nativeQuery = true)
    Integer countEstratosPeriodicosB3(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.periodicos pe " +
            "inner join teste.periodicos_autores pea " +
            "on pe.id = pea.fk_periodicos " +
            "inner join teste.avaliacao_period_qualis_atual awq " +
            "on (pe.titulo_periodico != awq.periodico " +
            "or pe.titulo_periodico = awq.periodico) " +
            "and similarity(awq.periodico,pe.titulo_periodico) >= .8 " +
            "and pea.fk_curriculo = :fkCurriculo " +
            "and pe.ano_trabalho >= :ano_inicio and pe.ano_trabalho <= :ano_final " +
            "and awq.estrato like 'B4'", nativeQuery = true)
    Integer countEstratosPeriodicosB4(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'A1'",nativeQuery = true)
    Integer countEstratosEventosA1(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'A2'",nativeQuery = true)
    Integer countEstratosEventosA2(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'A3'",nativeQuery = true)
    Integer countEstratosEventosA3(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'A4'",nativeQuery = true)
    Integer countEstratosEventosA4(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'B1'",nativeQuery = true)
    Integer countEstratosEventosB1(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'B2'",nativeQuery = true)
    Integer countEstratosEventosB2(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'B3'",nativeQuery = true)
    Integer countEstratosEventosB3(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);

    @Query(value="select count(*) from teste.artigo_eventos ae " +
            "inner join teste.artigo_evento_autores aea " +
            "on aea.fk_artigo_evento = ae.id " +
            "inner join teste.avaliacoes_de_congressos ac " +
            "on ae.nome_evento != ac.nome_evento " +
            "and similarity(ae.nome_evento,ac.nome_evento) >= .8 " +
            "and aea.fk_curriculo = :fkCurriculo " +
            "and ae.ano_trabalho >= :ano_inicio " +
            "and ae.ano_trabalho <= :ano_final " +
            "and ac.estrato like 'B4'",nativeQuery = true)
    Integer countEstratosEventosB4(@Param("fkCurriculo") Long fkCurriculo,@Param("ano_inicio")Integer ano_inicio
            ,@Param("ano_final")Integer ano_final);
}
