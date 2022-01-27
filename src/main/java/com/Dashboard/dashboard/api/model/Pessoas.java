package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="pessoas")
@SequenceGenerator(name = "teste.pessoas_id_pessoa_seq", sequenceName = "teste.pessoas_id_pessoa_seq", allocationSize = 1)
public class Pessoas {
    @Id
    @Column(name="id_pessoa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.pessoas_id_pessoa_seq")
    private Integer idPessoa;

    @Column(name="nome_completo")
    private String Nome_Completo;

    @Column(name="id_plataforma")
    private String idPlataforma;

    @Column(name="plataforma")
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

