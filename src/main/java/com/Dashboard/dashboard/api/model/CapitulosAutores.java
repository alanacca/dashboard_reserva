package com.Dashboard.dashboard.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="capitulos_autores")
@SequenceGenerator(name = "teste.capitulos_autores_seq", sequenceName = "teste.capitulos_autores_seq", allocationSize = 1)
public class CapitulosAutores {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.capitulos_autores_seq")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_curriculo")
    private Curriculos curriculo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_capitulo")
    private Capitulos fkCapitulo;

    public CapitulosAutores(){

    }
}
