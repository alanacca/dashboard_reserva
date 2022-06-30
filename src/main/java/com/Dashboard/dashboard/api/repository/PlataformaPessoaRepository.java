package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlataformaPessoaRepository extends JpaRepository<Plataforma_Pessoa, Integer> {


    @Query(value = "select * from teste.Plataforma_Pessoa p where p.fk_plataforma = :fkPlataforma and p.fk_pessoa = :fkPessoa", nativeQuery = true)
    Plataforma_Pessoa findByFkPlataformaAndFkPessoa(@Param("fkPlataforma") Integer fkPlataforma, @Param("fkPessoa") Integer fkPessoa);

    @Query(value = "select * from teste.Plataforma_Pessoa p where p.fk_pessoa = :fkPessoa", nativeQuery = true)
    Plataforma_Pessoa findByFkPessoa(@Param("fkPessoa") Integer fkPessoa);
}
