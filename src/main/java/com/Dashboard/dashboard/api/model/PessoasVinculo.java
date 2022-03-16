package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.PessoasVinculoRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="pessoas_vinculo")
@SequenceGenerator(name = "teste.pessoa_vinculo_id_seq", sequenceName = "teste.pessoa_vinculo_id_seq", allocationSize = 1)
public class PessoasVinculo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.pessoa_vinculo_id_seq")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_pessoa")
    private Pessoas fk_pessoa;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="vinculo")
    private Vinculo vinculo;

    public PessoasVinculo(){

    }

    public PessoasVinculo(Integer id){this.id = id;}

    public PessoasVinculo(PessoasVinculoRequest vinculoPessoaRequest){
        this.fk_pessoa = new Pessoas(vinculoPessoaRequest.fk_pessoa);
        this.vinculo = new Vinculo(vinculoPessoaRequest.vinculoId);
    }

}
