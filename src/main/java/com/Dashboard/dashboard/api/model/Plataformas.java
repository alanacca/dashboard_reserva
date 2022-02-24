package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="plataformas")
@SequenceGenerator(name = "teste.plataformas_seq", sequenceName = "teste.plataformas_seq", allocationSize = 1)
public class Plataformas {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.plataformas_seq")
    private Integer id;

    private String descricao;

    private String sigla;

    public Plataformas(){

    }

    public Plataformas(Integer id){this.id = id;}


}
