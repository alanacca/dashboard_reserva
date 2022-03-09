package com.Dashboard.dashboard.api.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="artigo_evento_autores")
@SequenceGenerator(name = "teste.artigo_evento_autores_seq", sequenceName = "teste.artigo_evento_autores_seq", allocationSize = 1)
public class ArtigoEventoAutores {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.artigo_evento_autores_seq")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_curriculo")
    private Curriculos curriculo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_artigo_evento")
    private ArtigoEventos fkArtigoEvento;

    public ArtigoEventoAutores(){

    }


}
