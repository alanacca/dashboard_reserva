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
public class Capitulos {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.capitulos_seq")
    private Integer idCapitulo;

    private String SEQUENCIA_PRODUCAO;
    private String CAPITULO_TIPO;
    private String TITULO;
    private String ANO_TRABALHO;
    private String DOI;
    private String LIVRO_TITULO;
    private String LIVRO_NRO_VOLUMES;
    private String PAGINA_INICIO;
    private String PAGINA_FIM;
    private String ISBN;
    private String LIVRO_NRO_SERIE;
    private String NOME_EDITORA;
    private String AUTORES = "";

    public void persist(Connection connection, Curriculos cur) throws SQLException {
        String sql = "select id from teste.capitulos where lower(titulo) = lower('" + Utils.strFormat(TITULO) + "')";
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
                    + "'" + CAPITULO_TIPO + "', "
                    + "'" + Utils.strFormat(TITULO) + "', "
                    + "'" + ANO_TRABALHO + "', "
                    + "'" + Utils.strFormat(LIVRO_NRO_VOLUMES) + "', "
                    + "'" + Utils.strFormat(LIVRO_NRO_SERIE) + "', "
                    + "'" + Utils.strFormat(LIVRO_TITULO) + "', "
                    + "'" + ISBN + "', "
                    + "'" + Utils.strFormat(AUTORES) + "', "
                    + "'" + PAGINA_INICIO + "', "
                    + "'" + PAGINA_FIM + "',"
                    + "'" + Utils.strFormat(NOME_EDITORA) + "', now())";
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
                    + "capitulo_tipo='" + CAPITULO_TIPO + "', "
                    + "titulo='" + Utils.strFormat(TITULO) + "', "
                    + "ano_trabalho='" + ANO_TRABALHO + "', "
                    + "livro_nro_volumes='" + Utils.strFormat(LIVRO_NRO_VOLUMES) + "', "
                    + "livro_nro_serie='" + Utils.strFormat(LIVRO_NRO_SERIE) + "', "
                    + "livro_titulo='" + Utils.strFormat(LIVRO_TITULO) + "', "
                    + "isbn='" + ISBN + "', "
                    + "autores='" + Utils.strFormat(AUTORES) + "', "
                    + "pagina_inicio='" + PAGINA_INICIO + "', "
                    + "pagina_fim='" + PAGINA_FIM + "',"
                    + "nome_editora='" + Utils.strFormat(NOME_EDITORA) + "',"
                    + "updated_at=now()"
                    + "   where id = " + id;

            stmt.executeUpdate(sql);

            sql = "select fk_curriculo from teste.capitulos_autores "
                    + "where fk_capitulo = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getId()+ "'";
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
