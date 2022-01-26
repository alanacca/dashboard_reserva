package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public", name="Pessoas")
public class Pessoas {
    @Id
    @Column(name="Id_pessoa")
    private Integer idPessoa;

    @Column(name="Nome_Completo")
    private String Nome_Completo;

    @Column(name="Id_Plataforma")
    private String idPlataforma;

    @Column(name="Plataforma")
    private String Platraforma;

    public Pessoas(){

    }

    public Pessoas(Integer id){
        this.idPessoa = id;
    }

    public Pessoas(PessoasRequest request){
        this.Nome_Completo = request.Nome_Completo;
        this.idPlataforma = request.idPlataforma;
        this.Platraforma = request.Plataforma;
    }
}

