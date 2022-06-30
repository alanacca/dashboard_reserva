package com.Dashboard.dashboard.api.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Entity
@Table(schema = "teste", name="setor_curriculo")
@SequenceGenerator(name = "teste.setor_curriculo_id_seq", sequenceName = "teste.setor_curriculo_id_seq", allocationSize = 1)
public class Setores {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.setor_curriculo_id_seq")
    private Integer idSetor;
    private String nome;

    public void persist(Connection connection, Curriculos cur, boolean principal) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "delete from teste.setor_curriculo where fk_curriculo = " + cur.getId();
        stmt.executeUpdate(sql);

        sql = "select id from teste.setores where '" + nome + "' = nome";
        ResultSet rs = stmt.executeQuery(sql);

        //checa se o setor jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into teste.setores (nome) "
                    + "values ('" + nome + "')";
            stmt.executeUpdate(sql);
            sql = "select id from teste.setores where '" + nome + "' = nome";
            rs = stmt.executeQuery(sql);
            rs.next();

        }
        sql = "insert into teste.setor_curriculo (fk_curriculo, fk_setor, is_principal) "
                + "values (" + cur.getId() + ", "
                + "" + rs.getInt(1) + ","
                + "" + principal + ")";
        stmt.executeUpdate(sql);
    }

}
