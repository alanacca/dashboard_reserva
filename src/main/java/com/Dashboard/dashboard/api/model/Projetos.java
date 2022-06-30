package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.utils.Utils;
import lombok.Data;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Data
@Entity
@Table(schema = "teste", name="projetos")
@SequenceGenerator(name = "teste.projetos_id_seq", sequenceName = "teste.projetos_id_seq", allocationSize = 1)
public class Projetos {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.projetos_id_seq")
    private Integer idProjeto;

    private String SEQUENCIA_PROJETO;
    private String ANO_INICIO;
    private String ANO_FIM;
    private String NOME_DO_PROJETO;
    private String SITUACAO;
    private String NATUREZA;
    private String DESCRICAO_DO_PROJETO;
    private String NUMERO_GRADUACAO;
    private String NUMERO_ESPECIALIZACAO;
    private String NUMERO_MESTRADO_ACADEMICO;
    private String NUMERO_MESTRADO_PROF;
    private String NUMERO_DOUTORADO;
    private String integrantes = "";
    private Boolean isReponsavel = false;
    private ArrayList<FinanciadoresProjetos> finaciamento = new ArrayList<>();

    public void persist(Connection connection, Curriculos cur) throws SQLException {
        if (SITUACAO.compareTo("DESATIVADO") ==0) return;

        String sql = "select id from teste.projetos where lower(nome) = lower('" + Utils.strFormat(NOME_DO_PROJETO) + "')";
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        //checa se o projeto jÃ¡ existe
        if (!rs.next()) {

            sql = "insert into teste.projetos("
                    + "sequencia_projeto,nome,situacao,"
                    + "ano_inicio, ano_fim, natureza, descricao,"
                    + "nro_graduacao, nro_mestrado_academico,"
                    + "nro_mestrado_profissionalizante, nro_doutorado, integrantes,created_at) values ("
                    + "'" + SEQUENCIA_PROJETO + "', "
                    + "'" + Utils.strFormat(NOME_DO_PROJETO) + "', "
                    + "'" + SITUACAO + "', "
                    + "'" + ANO_INICIO + "', "
                    + "'" + ANO_FIM + "', "
                    + "'" + NATUREZA + "', "
                    + "'" + Utils.strFormat(DESCRICAO_DO_PROJETO) + "', "
                    + "'" + NUMERO_GRADUACAO + "', "
                    + "'" + NUMERO_MESTRADO_ACADEMICO  + "', "
                    + "'" + NUMERO_MESTRADO_PROF + "', "
                    + "'" + NUMERO_DOUTORADO + "', "
                    + "'" + Utils.strFormat(integrantes) + "', now())";
            try {
                stmt.executeUpdate(sql);
            }catch(Exception e) {
                System.out.println("Erro: " + sql);
            }

            sql = "insert into teste.integrantes_projetos(fk_curriculo,fk_projeto, is_responsavel)"
                    + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                    + "        (select max(id) from teste.projetos), " + isReponsavel.toString() + ")";
            stmt.executeUpdate(sql);

            for (FinanciadoresProjetos f:finaciamento) {
                sql = "insert into teste.financiadores_projetos (fk_curriculo, fk_projeto, nome_instituicao, natureza, created_at)"
                        + "values ("
                        + "'" + cur.getId() + "',"
                        + "(select max(id) from teste.projetos),"
                        + "'"+ Utils.strFormat(f.getNOME_INSTITUICAO()) + "',"
                        + "'"+ Utils.strFormat(f.getNATUREZA()) + "', now())";
                stmt.executeUpdate(sql);
            }

        }
        //checa se o projeto estÃ¡ associada ao integrante
        else {
            int id = rs.getInt("id");
            sql = "select fk_curriculo from teste.integrantes_projetos "
                    + "where fk_projeto = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getId() + "'";
            rs = stmt.executeQuery(sql);

            //se nÃ£o estiver associada, inclui
            if (!rs.next()) {
                sql = "insert into teste.integrantes_projetos(fk_curriculo,fk_projeto,created_at)"
                        + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                        + "'"+ id + "', now())";
                stmt.executeUpdate(sql);

            }

        }

    }
}
