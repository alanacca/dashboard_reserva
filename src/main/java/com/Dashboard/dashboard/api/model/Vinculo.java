package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.VinculoRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="vinculo")
@SequenceGenerator(name = "teste.vinculo_id_seq", sequenceName = "teste.vinculo_id_seq", allocationSize = 1)
public class Vinculo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.vinculo_id_seq")
    private Integer id;

    @Column(name="sigla")
    private String sigla;

    @Column(name="descricao")
    private String descricao;

    public Vinculo(){

    }

    public Vinculo(Integer id){
        this.id = id;
    }

    public Vinculo(VinculoRequest request){
        this.sigla = request.sigla;

        this.descricao = request.descricao;
    }
}
