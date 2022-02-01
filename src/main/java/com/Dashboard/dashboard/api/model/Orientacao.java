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
@Table(schema = "teste", name="orientacoes")
@SequenceGenerator(name = "teste.orientacoes_id_seq", sequenceName = "teste.orientacoes_id_seq", allocationSize = 1)
public class Orientacao {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.orientacoes_id_seq")
    private Integer idOrientacao;

    private String sequencia_orientacao;
    private String natureza;
    private String tipo;
    private String titulo;
    private String ano;
    private String doi;
    private String tipo_orientacao;
    private String nome_orientando;
    private String nome_instituicao;
    private String nome_orgao;
    private String nome_curso;
    private Boolean is_finalizado=false;

    public void persist(Connection connection, Curriculo cur) throws SQLException {
        String sql = "select id from orientacoes where fk_curriculo = '" + cur.getNUMERO_IDENTIFICADOR() + "' "
                + "and titulo = '" + Utils.strFormat(titulo) + "'";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (rs.next()) {

            sql = "delete from orientacoes where fk_curriculo = '" + cur.getNUMERO_IDENTIFICADOR() + "' "
                    + "and titulo = '" + Utils.strFormat(titulo) + "'";
            stmt.executeUpdate(sql);
        }

        sql = "insert into orientacoes(fk_curriculo,sequencia_orientacao, natureza, tipo, titulo, "
                + "ano, tipo_orientacao, nome_orientando, nome_instituicao, nome_curso, "
                + "is_finalizado,created_at) "
                + " values ("
                + " (select id from curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                + "'" + sequencia_orientacao + "',"
                + "'" + natureza + "',"
                + "'" + tipo + "',"
                + "'" + Utils.strFormat(titulo) + "',"
                + "'" + ano + "',"
                + "'" + Utils.strFormat(tipo_orientacao) + "',"
                + "'" + Utils.strFormat(nome_orientando) + "',"
                + "'" + Utils.strFormat(nome_instituicao) + "',"
                + "'" + Utils.strFormat(nome_curso) + "',"
                + "'" + is_finalizado + "',now())";

        stmt.executeUpdate(sql);

    }
}
