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
@Table(schema = "teste", name="artigo_eventos")
@SequenceGenerator(name = "teste.artigo_eventos_seq", sequenceName = "teste.artigo_eventos_seq", allocationSize = 1)
public class ArtigoEventos {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.artigo_eventos_seq")
    private Integer idTrabalhoEvento;

    private String SEQUENCIA_PRODUCAO;
    private String TITULO;  //DADOS-BASICOS-DO-TRABALHO
    private String NATUREZA;
    private String ANO_TRABALHO;
    private String DOI;
    private String PAIS_EVENTO;

    private String CLASSIFICACAO_EVENTO; //DETALHAMENTO-DO-TRABALHO
    private String NOME_EVENTO;
    private String CIDADE_EVENTO;
    private String TITULO_ANAIS_OU_PROCEEDINGS;
    private String VOLUME;
    private String FASCICULO;
    private String SERIE;
    private String PAGINA_INICIO;
    private String PAGINA_FIM;
    private String NOME_EDITORA;

    private String ISBN;   //mesmo que issn

    private String AUTORES = "";

    public void persist(Connection connection, Curriculos cur) throws SQLException {
        String sql = "select id from teste.artigo_eventos where lower(titulo) = lower('" + Utils.strFormat(TITULO) + "')";
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into teste.artigo_eventos("
                    + "tipo_producao,sequencia_producao,doi,natureza,titulo,"
                    + "ano_trabalho,volume,fasciculo,serie,"
                    + "titulo_anais_ou_proceedings,issn,autores,pagina_inicio,pagina_fim,"
                    + "nome_evento, pais_evento, classificacao_evento, cidade_evento,nome_editora, created_at) values ("
                    + "'TRABALHO_EM_EVENTO',"
                    + "'" + SEQUENCIA_PRODUCAO + "', "
                    + "'" + DOI + "', "
                    + "'" + NATUREZA + "', "
                    + "'" + Utils.strFormat(TITULO) + "', "
                    + "'" + ANO_TRABALHO + "', "
                    + "'" + Utils.strFormat(VOLUME) + "', "
                    + "'" + Utils.strFormat(FASCICULO) + "', "
                    + "'" + Utils.strFormat(SERIE) + "', "
                    + "'" + Utils.strFormat(TITULO_ANAIS_OU_PROCEEDINGS) + "', "
                    + "'" + ISBN + "', "
                    + "'" + Utils.strFormat(AUTORES) + "', "
                    + "'" + PAGINA_INICIO + "', "
                    + "'" + PAGINA_FIM + "',"
                    + "'" + Utils.strFormat(NOME_EVENTO) + "',"
                    + "'" + Utils.strFormat(PAIS_EVENTO) + "',"
                    + "'" + Utils.strFormat(CLASSIFICACAO_EVENTO) + "',"
                    + "'" + Utils.strFormat(CIDADE_EVENTO) + "',"
                    + "'" + Utils.strFormat(NOME_EDITORA) + "', now())";
            stmt.executeUpdate(sql);
            sql = "insert into teste.artigo_evento_autores(fk_curriculo,fk_artigo_evento)"
                    + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                    + "        (select max(id) from teste.artigo_eventos) )";
            stmt.executeUpdate(sql);

        }
        //checa se a produÃ§Ã£o estÃ¡ associada ao autor
        else {
            int id = rs.getInt("id");
            sql = "update teste.artigo_eventos set "
                    + "doi='" + DOI + "',"
                    + "natureza='" + NATUREZA + "',"
                    + "titulo='" + Utils.strFormat(TITULO) + "',"
                    + "ano_trabalho='" + ANO_TRABALHO + "',"
                    + "volume='" + Utils.strFormat(VOLUME) + "',"
                    + "fasciculo='" + FASCICULO + "',"
                    + "serie='" + SERIE + "',"
                    + "titulo_anais_ou_proceedings='" + Utils.strFormat(TITULO_ANAIS_OU_PROCEEDINGS)  + "', "
                    + "issn='" + ISBN + "', "
                    + "autores='" + Utils.strFormat(AUTORES) + "', "
                    + "pagina_inicio='" + PAGINA_INICIO + "', "
                    + "pagina_fim='" + PAGINA_FIM + "',"
                    + "nome_evento='" + Utils.strFormat(NOME_EVENTO) + "',"
                    + "pais_evento='" + Utils.strFormat(PAIS_EVENTO) + "',"
                    + "classificacao_evento='" + Utils.strFormat(CLASSIFICACAO_EVENTO) + "',"
                    + "cidade_evento='" + Utils.strFormat(CIDADE_EVENTO) + "',"
                    + "nome_editora='" + Utils.strFormat(NOME_EDITORA) + "',"
                    + "updated_at=now()"
                    + "   where id = " + id;

            stmt.executeUpdate(sql);

            sql = "select fk_curriculo from teste.artigo_evento_autores "
                    + "where fk_artigo_evento = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getId() + "'";
            rs = stmt.executeQuery(sql);



            //se nÃ£o estiver associada, inclui
            if (!rs.next()) {
                sql = "insert into teste.artigo_evento_autores(fk_curriculo,fk_artigo_evento)"
                        + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                        + "'"+ id + "')";
                stmt.executeUpdate(sql);
            }

        }

    }
}
