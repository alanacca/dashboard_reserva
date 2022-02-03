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
@Table(schema = "teste", name="producoes_tecnicas")
@SequenceGenerator(name = "teste.producoes_tecnicas_id_seq", sequenceName = "teste.producoes_tecnicas_id_seq", allocationSize = 1)
public class Tecnica {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.producoes_tecnicas_id_seq")
    private Integer idTecnica;

    private String sequencia_producao;
    private String tipo;
    private String ano;
    private String titulo;
    private String financiadora="";
    private String outras_informacoes="";
    private String autores = "";

    public void persist(Connection connection, Curriculo cur) throws SQLException {
        String sql = "select id from producoes_tecnicas where lower(titulo) = lower('" + Utils.strFormat(titulo) + "')";
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        //checa se a produÃ§Ã£o jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into producoes_tecnicas("
                    + "sequencia_producao, tipo, ano, titulo, outras_informacoes, financiadora, autores,created_at"
                    + ") values ("
                    + "'" + sequencia_producao + "', "
                    + "'" + tipo + "', "
                    + "'" + ano +"', "
                    + "'" + Utils.strFormat(titulo) + "', "
                    + "'" + Utils.strFormat(outras_informacoes) + "', "
                    + "'" + Utils.strFormat(financiadora) + "', "
                    + "'" + Utils.strFormat(autores) + "', now())";
            try {
                stmt.executeUpdate(sql);
            }catch(Exception e)
            {
                System.out.println("erro:" + sql);
            }
            sql = "insert into autores_prod_tecnicas(fk_curriculo,fk_producao_tecnica)"
                    + "values ('" + cur.getNUMERO_IDENTIFICADOR() + "',"
                    + "        (select max(id) from producoes_tecnicas) )";
            stmt.executeUpdate(sql);

        }
        //checa se a produÃ§Ã£o estÃ¡ associada ao autor
        else {

            int id = rs.getInt("id");
            sql= "update producoes_tecnicas set "
                    + "ano='" + ano +"', "
                    + "titulo='" + Utils.strFormat(titulo) + "', "
                    + "outras_informacoes='" + Utils.strFormat(outras_informacoes) + "', "
                    + "financiadora='" + Utils.strFormat(financiadora) + "', "
                    + "autores='" + Utils.strFormat(autores) + "',"
                    + "updated_at =now()"
                    + "   where id = " + id;

            stmt.executeUpdate(sql);

            sql = "select fk_curriculo from autores_prod_tecnicas "
                    + "where fk_producao_tecnica = '" + id + "'"
                    + "     and fk_curriculo = '" + cur.getNUMERO_IDENTIFICADOR() + "'";
            rs = stmt.executeQuery(sql);



            //se nÃ£o estiver associada, inclui
            if (!rs.next()) {
                sql = "insert into autores_prod_tecnicas(fk_curriculo,fk_producao_tecnica,created_at)"
                        + "values ('" + cur.getNUMERO_IDENTIFICADOR() + "',"
                        + "'"+ id + "',now())";
                stmt.executeUpdate(sql);
            }

        }

    }
}

