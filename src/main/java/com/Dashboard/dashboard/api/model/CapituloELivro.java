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
@Table(schema = "teste", name="capitulos")
@SequenceGenerator(name = "teste.capitulos_seq", sequenceName = "teste.capitulos_seq", allocationSize = 1)
public class CapituloELivro {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.capitulos_seq")
    private Integer idCapitulo;

    private String SEQUENCIA_PRODUCAO;
    private String TIPO;
    private String TITULO_DO_CAPITULO_DO_LIVRO;
    private String ANO;
    private String PAIS_DE_PUBLICACAO;
    private String DOI;
    private String TITULO_DO_LIVRO;
    private String NUMERO_DE_VOLUMES;
    private String PAGINA_INICIAL;
    private String PAGINA_FINAL;
    private String ISBN;
    private String ORGANIZADORES;
    private String NUMERO_DA_EDICAO_REVISAO;
    private String NUMERO_DA_SERIE;
    private String CIDADE_DA_EDITORA;
    private String NOME_DA_EDITORA;
    private String AUTORES = "";

    public void persist(Connection connection, Curriculo cur) throws SQLException {
        String sql = "select id from teste.capitulos where lower(titulo) = lower('" + Utils.strFormat(TITULO_DO_CAPITULO_DO_LIVRO) + "')";
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into teste.capitulos("
                    + "tipo_producao,sequencia_producao,doi,capitulo_tipo,titulo,"
                    + "ano_trabalho,livro_nro_volumes,livro_nro_serie,livro_titulo,"
                    + "isbn,autores,pagina_inicio,pagina_fim,nome_editora,created_at"
                    + ") values ("
                    + "'CAPITULO-DE-LIVRO',"
                    + "'" + SEQUENCIA_PRODUCAO + "', "
                    + "'" + DOI + "', "
                    + "'" + TIPO + "', "
                    + "'" + Utils.strFormat(TITULO_DO_CAPITULO_DO_LIVRO) + "', "
                    + "'" + ANO + "', "
                    + "'" + Utils.strFormat(NUMERO_DE_VOLUMES) + "', "
                    + "'" + Utils.strFormat(NUMERO_DA_SERIE) + "', "
                    + "'" + Utils.strFormat(TITULO_DO_LIVRO) + "', "
                    + "'" + ISBN + "', "
                    + "'" + Utils.strFormat(AUTORES) + "', "
                    + "'" + PAGINA_INICIAL + "', "
                    + "'" + PAGINA_FINAL + "',"
                    + "'" + Utils.strFormat(NOME_DA_EDITORA) + "', now())";
            stmt.executeUpdate(sql);
            sql = "insert into teste.capitulos_autores(fk_curriculo,fk_capitulo)"
                    + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                    + "        (select max(id) from teste.capitulos) )";
            stmt.executeUpdate(sql);

        }
        //checa se a produÃ§Ã£o estÃ¡ associada ao autor
        else {
            int id = rs.getInt("id");
            sql = "update teste.capitulos set "
                    + "doi='" + DOI + "', "
                    + "capitulo_tipo='" + TIPO + "', "
                    + "titulo='" + Utils.strFormat(TITULO_DO_CAPITULO_DO_LIVRO) + "', "
                    + "ano_trabalho='" + ANO + "', "
                    + "livro_nro_volumes='" + Utils.strFormat(NUMERO_DE_VOLUMES) + "', "
                    + "livro_nro_serie='" + Utils.strFormat(NUMERO_DA_SERIE) + "', "
                    + "livro_titulo='" + Utils.strFormat(TITULO_DO_LIVRO) + "', "
                    + "isbn='" + ISBN + "', "
                    + "autores='" + Utils.strFormat(AUTORES) + "', "
                    + "pagina_inicio='" + PAGINA_INICIAL + "', "
                    + "pagina_fim='" + PAGINA_FINAL + "',"
                    + "nome_editora='" + Utils.strFormat(NOME_DA_EDITORA) + "',"
                    + "updated_at=now()"
                    + "   where id = " + id;

            stmt.executeUpdate(sql);

            sql = "select fk_curriculo from teste.capitulos_autores "
                    + "where fk_capitulo = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getNUMERO_IDENTIFICADOR()+ "'";
            rs = stmt.executeQuery(sql);



            //se nÃ£o estiver associada, inclui
            if (!rs.next()) {
                sql = "insert into teste.capitulos_autores(fk_curriculo,fk_capitulo)"
                        + "values ((select id from teste.curriculos where nome_completo = '" + Utils.strFormat(cur.getNOME_COMPLETO()) + "'),"
                        + "'"+ id + "')";
                stmt.executeUpdate(sql);
            }

        }

    }


}
