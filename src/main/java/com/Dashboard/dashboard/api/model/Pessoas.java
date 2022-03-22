package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.AllArgsConstructor;
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
    private String nomeCompleto;

    @Column(name="mestrado")
    private Boolean mestrado;

    @Column(name="doutorado")
    private Boolean doutorado;

    public Pessoas(){

    }

    public Pessoas(Integer id){
        this.idPessoa = id;
    }

    public Pessoas(PessoasRequest request){
        this.nomeCompleto = request.Nome_Completo;
        this.mestrado = request.mestrado;
        this.doutorado = request.doutorado;
    }

    public Pessoas(Pessoas pessoa){

        this.nomeCompleto = pessoa.nomeCompleto;
        this.mestrado = pessoa.mestrado;
        this.doutorado = pessoa.doutorado;
    }
}

