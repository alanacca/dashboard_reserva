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
@Table(schema = "teste", name="periodicos")
@SequenceGenerator(name = "teste.periodicos_seq", sequenceName = "teste.periodicos_seq", allocationSize = 1)
public class Artigo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.periodicos_seq")
    private Integer idArtigo;

    private String SEQUENCIA_PRODUCAO;


    private String TITULO_DO_ARTIGO;  //dados bÃ¡sicos

    private String ANO_DO_ARTIGO;     //dados bÃ¡sicos

    private String TITULO_DO_PERIODICO_OU_REVISTA; //detalhamento

    private String ISSN; //detalhamento

    private String VOLUME;

    private String FASCICULO;

    private String SERIE;

    private String PAGINA_INICIAL;

    private String PAGINA_FINAL;

    private String AUTORES = "";

    private String DOI;

    private String NATUREZA;

    private String TIPO;

    public String toString() {
        return  SEQUENCIA_PRODUCAO + "\n" + AUTORES + "\n" + TITULO_DO_ARTIGO+ "\n" +ANO_DO_ARTIGO+ "\n" +TITULO_DO_PERIODICO_OU_REVISTA
                + "\n" + ISSN+ "\n" +VOLUME+ "\n" +FASCICULO+ "\n" +SERIE+ "\n" +PAGINA_INICIAL+ "\n" +PAGINA_FINAL;
    }

    public void persist(Connection connection, Curriculo cur) throws SQLException {
        String sql = "select id from periodicos where lower(titulo) = lower('" + Utils.strFormat(TITULO_DO_ARTIGO) + "')";
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into periodicos("
                    + "tipo_producao,sequencia_producao,doi,natureza,titulo,"
                    + "ano_trabalho,volume,fasciculo,serie,"
                    + "titulo_periodico,issn,autores,pagina_inicio,pagina_fim,created_at) values ("
                    + "'" + TIPO + "', "
                    + "'" + SEQUENCIA_PRODUCAO + "', "
                    + "'" + DOI + "', "
                    + "'" + NATUREZA + "', "
                    + "'" + Utils.strFormat(TITULO_DO_ARTIGO) + "', "
                    + "'" + ANO_DO_ARTIGO + "', "
                    + "'" + VOLUME + "', "
                    + "'" + FASCICULO + "', "
                    + "'" + SERIE + "', "
                    + "'" + Utils.strFormat(TITULO_DO_PERIODICO_OU_REVISTA)  + "', "
                    + "'" + ISSN + "', "
                    + "'" + Utils.strFormat(AUTORES) + "', "
                    + "'" + PAGINA_INICIAL + "', "
                    + "'" + PAGINA_FINAL + "', now())";
            try {
                stmt.executeUpdate(sql);
            }catch(Exception e)
            {
                System.out.println("erro:" + sql);
            }
            sql = "insert into periodicos_autores(fk_curriculo,fk_periodicos)"
                    + "values ((select id from curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                    + "        (select max(id) from periodicos) )";
            stmt.executeUpdate(sql);

        }
        //checa se a produÃ§Ã£o estÃ¡ associada ao autor
        else {
            int id = rs.getInt("id");
            sql = "update periodicos set "
                    + "tipo_producao='" + TIPO + "',"
                    + "doi='" + DOI + "',"
                    + "natureza='" + NATUREZA + "',"
                    + "titulo='" + Utils.strFormat(TITULO_DO_ARTIGO) + "',"
                    + "ano_trabalho='" + ANO_DO_ARTIGO + "',"
                    + "volume='" + VOLUME + "',"
                    + "fasciculo='" + FASCICULO + "',"
                    + "serie='" + SERIE + "',"
                    + "titulo_periodico='" + Utils.strFormat(TITULO_DO_PERIODICO_OU_REVISTA)  + "', "
                    + "issn='" + ISSN + "', "
                    + "autores='" + Utils.strFormat(AUTORES) + "', "
                    + "pagina_inicio='" + PAGINA_INICIAL + "', "
                    + "pagina_fim='" + PAGINA_FINAL + "',"
                    + "updated_at=now()"
                    + "   where id = " + id;

            stmt.executeUpdate(sql);


            sql = "select fk_curriculo from periodicos_autores "
                    + "where fk_periodicos = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getNUMERO_IDENTIFICADOR() + "'";
            rs = stmt.executeQuery(sql);



            //se nÃ£o estiver associada, inclui
            if (!rs.next()) {
                sql = "insert into periodicos_autores(fk_curriculo,fk_periodicos)"
                        + "values ((select id from curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                        + "'"+ id + "')";
                stmt.executeUpdate(sql);
            }

        }

    }
}
