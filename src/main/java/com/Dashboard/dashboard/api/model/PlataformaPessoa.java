package com.Dashboard.dashboard.api.model;


import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="plataforma_pessoa")
@SequenceGenerator(name = "teste.plataforma_pessoa_seq", sequenceName = "teste.plataforma_pessoa_seq", allocationSize = 1)
public class PlataformaPessoa {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.plataforma_pessoa_seq")
    private Integer id;

    private String idPlataforma;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_plataforma")
    private Plataformas fkPlataforma;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_pessoa", referencedColumnName = "id_pessoa")
    private Pessoas fkPessoa;

    public PlataformaPessoa(){

    }

    public PlataformaPessoa(Integer id){this.id = id;}

    public PlataformaPessoa(PlataformaPessoaRequest plataformaPessoaRequest){
        this.idPlataforma = plataformaPessoaRequest.idPlataforma;
        this.fkPlataforma = new Plataformas(plataformaPessoaRequest.fkPlataforma);
        this.fkPessoa = plataformaPessoaRequest.fkPessoa;
    }
}
