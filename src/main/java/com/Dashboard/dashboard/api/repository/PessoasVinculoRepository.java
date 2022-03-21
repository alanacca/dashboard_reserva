package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.Pessoas_Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoasVinculoRepository extends JpaRepository<Pessoas_Vinculo,Integer> {
    @Query(value = "select pe.id_pessoa from teste.pessoas_vinculo pv full outer join teste.pessoas pe on pv.fk_pessoa = pe.id_pessoa where pv.fk_pessoa is null",
    nativeQuery = true)
    List<Integer> getSemVinculoPorVinculo(@Param("idVinculo") Integer idVinculo);

    @Query(value = "select fk_pessoa from teste.pessoas_vinculo where vinculo = :idVinculo", nativeQuery = true)
    List<Integer> getbyVinculo(@Param("idVinculo") Integer idVinculo);

    @Query(value = "select fk_pessoa from teste.pessoas_vinculo", nativeQuery = true)
    List<Integer> findAllFkPessoa();

    @Query(value = "delete from teste.pessoas_vinculo where fk_pessoa = :fkPessoa",nativeQuery = true)
    void deleteByFkPessoa(@Param("fkPessoa") Integer fkPessoa);

    @Query(value = "select * from teste.pessoas_vinculo where fk_pessoa = :fkPessoa",nativeQuery = true)
    List<Pessoas_Vinculo> existsByFkPessoa(@Param("fkPessoa") Integer fkPessoa);


}
