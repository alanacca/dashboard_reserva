package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.utils.Utils;
import lombok.Data;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Entity
@Table(schema = "teste", name="formacoes")
@SequenceGenerator(name = "teste.formacoes_id_seq", sequenceName = "teste.formacoes_id_seq", allocationSize = 1)
public class Formacoes {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.formacoes_id_seq")
    private Integer idFormacao;

    private String tipo_formacao;
    private String titulo_trabalho;
    private String titulo_residencia_medica;
    private String nome_orientador;
    private String nome_co_orientador;
    private String nome_instituicao;
    private String nome_orgao;
    private String nome_curso;
    private String ano_inicio;
    private String ano_conclusao;
    private String ano_obtencao_titulo;
    private String carga_horaria;

    @ManyToOne
    private Curriculos cur;


    public void persist(Connection connection, Curriculos cur) throws SQLException {
        String sql = "select id from teste.formacoes where fk_curriculo = '" + cur.getId()+ "' "
                + "and titulo_trabalho = '" + Utils.strFormat(titulo_trabalho) + "'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (rs.next()) {

            sql = "delete from teste.formacoes where fk_curriculo = '" + cur.getId() + "' "
                    + "and titulo_trabalho = '" + Utils.strFormat(titulo_trabalho) + "'";
            stmt.executeUpdate(sql);
        }


        sql = "insert into teste.formacoes(fk_curriculo,tipo_formacao,titulo_trabalho,titulo_residencia_medica,"
                + "nome_orientador,nome_co_orientador,nome_instituicao,nome_orgao,nome_curso,ano_inicio,ano_conclusao,"
                + "ano_obtencao_titulo,carga_horaria)"
                + " values ("
                + " (select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                + "'" + tipo_formacao + "',"
                + "'" + Utils.strFormat(titulo_trabalho) + "',"
                + "'" + Utils.strFormat(titulo_residencia_medica) + "',"
                + "'" + Utils.strFormat(nome_orientador) + "',"
                + "'" + Utils.strFormat(nome_co_orientador) + "',"
                + "'" + Utils.strFormat(nome_instituicao) + "',"
                + "'" + Utils.strFormat(nome_orgao) + "',"
                + "'" + Utils.strFormat(nome_curso) + "',"
                + "'" + ano_inicio + "',"
                + "'" + ano_conclusao + "',"
                + "'" + ano_obtencao_titulo + "',"
                + "'" + carga_horaria + "')";
        stmt.executeUpdate(sql);
    }
}
