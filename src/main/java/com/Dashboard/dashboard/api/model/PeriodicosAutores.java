package com.Dashboard.dashboard.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="periodicos_autores")
@SequenceGenerator(name = "teste.periodicos_autores_seq", sequenceName = "teste.periodicos_autores_seq", allocationSize = 1)
public class PeriodicosAutores {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.periodicos_autores_seq")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_curriculo")
    private Curriculos curriculo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_periodicos")
    private Periodicos fkPeriodicos;

    public PeriodicosAutores(){

    }
}
