package com.Dashboard.dashboard.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "teste", name="financiadores_projetos")
@SequenceGenerator(name = "teste.financiadores_projetos_id_seq", sequenceName = "teste.financiadores_projetos_id_seq", allocationSize = 1)
public class Financiador {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.financiadores_projetos_id_seq")
    private Integer idFinanciador;

    private String SEQUENCIA_FINANCIADOR="";
    private String NOME_INSTITUICAO="";
    private String NATUREZA="";
}
