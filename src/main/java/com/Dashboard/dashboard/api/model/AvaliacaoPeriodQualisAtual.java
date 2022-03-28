package com.Dashboard.dashboard.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="avaliacao_period_qualis_atual")
@SequenceGenerator(name = "teste.avaliacao_period_qualis_atual_id_seq", sequenceName = "teste.avaliacao_period_qualis_atual_id_seq", allocationSize = 1)
public class AvaliacaoPeriodQualisAtual {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.avaliacao_period_qualis_atual_id_seq")
    private Integer id;

    @Column( name = "issn")
    private String issn;

    @Column(name = "periodico")
    private String periodico;

    @Column(name = "estrato")
    private String estrato;

    @Column(name = "data_atualizacao")
    private String data_atualizacao;

    public AvaliacaoPeriodQualisAtual(){

    }

    public AvaliacaoPeriodQualisAtual(Integer id){this.id = id;}

}
