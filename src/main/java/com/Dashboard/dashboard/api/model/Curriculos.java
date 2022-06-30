package com.Dashboard.dashboard.api.model;

import com.Dashboard.dashboard.api.utils.Utils;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Entity
@Table(schema = "teste", name="curriculos")
@SequenceGenerator(name = "teste.curriculos_id_seq", sequenceName = "teste.curriculos_id_seq", allocationSize = 1)
public class Curriculos {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teste.curriculos_id_seq")
    private Long id;


    private String DATA_ATUALIZACAO;
    private String NOME_COMPLETO;
    private String RESUMO_CV;
    private String NOME_CITACOES;
    private String NOME_INSTITUICAO_EMPRESA;
    private String NOME_ORGAO;
    private String DDD;
    private String TELEFONE;
    private String HOMEPAGE;

    @Override
    public String toString() {
        return id + "\n" + DATA_ATUALIZACAO + "\n" +
                NOME_COMPLETO + "\n" +  RESUMO_CV;
    }

    public Boolean checkUpdate(Connection connection) throws SQLException {
        String sql = "select id from teste.curriculos where id= '" + id.toString()+"'"
                + "and data_atualizacao >= '" + Utils.dateFormat(DATA_ATUALIZACAO) + "'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return false;
        }
        return true;
    }

    public void persist(Connection connection, String cpf, String nasc, String email) throws SQLException{

        if (email == null) email = "";

        String sql = "select id from teste.curriculos where id = '" + id.toString() + "'";
        Statement stmt = connection.createStatement();


        ResultSet rs = stmt.executeQuery(sql);
        //checa se o curriculo jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into teste.curriculos(id,data_atualizacao,nome_completo,resumo_cv,"
                    + "nome_citacoes,nome_instituicao_empresa,nome_orgao,ddd,telefone,homepage,created_at,"
                    + "cpf, data_nascimento, email)"
                    + "values ('" + id + "',"
                    + "'" + Utils.dateFormat(DATA_ATUALIZACAO) + "', "
                    + "'" + Utils.strFormat(NOME_COMPLETO) + "', "
                    + "'" + Utils.strFormat(RESUMO_CV) + "', "
                    + "'" + Utils.strFormat(NOME_CITACOES) + "',"
                    + "'" + Utils.strFormat(NOME_INSTITUICAO_EMPRESA) + "',"
                    + "'" + Utils.strFormat(NOME_ORGAO) + "',"
                    + "'" + DDD + "',"
                    + "'" + TELEFONE + "',"
                    + "'" + Utils.strFormat(HOMEPAGE) + "', now(),"
                    + "'" + cpf + "',";
            if (nasc==null)
                sql+= "" + nasc + ",";
            else
                sql+= "'" + nasc + "',";
            sql+= "'" + email + "'"
                    + ")";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
        }
        else {
            sql = "update teste.curriculos set "
                    + "data_atualizacao='" + Utils.dateFormat(DATA_ATUALIZACAO) + "', "
                    + "nome_completo='" + Utils.strFormat(NOME_COMPLETO) + "', "
                    + "resumo_cv='" + Utils.strFormat(RESUMO_CV) + "', "
                    + "nome_citacoes='" + Utils.strFormat(NOME_CITACOES) + "',"
                    + "nome_instituicao_empresa='" + Utils.strFormat(NOME_INSTITUICAO_EMPRESA) + "',"
                    + "nome_orgao='" + Utils.strFormat(NOME_ORGAO) + "',"
                    + "ddd='" + DDD + "',"
                    + "telefone='" + TELEFONE + "',"
                    + "homepage='" + Utils.strFormat(HOMEPAGE) + "',"
                    + "cpf='" + cpf + "',";
            if (nasc==null)
                sql+= "data_nascimento=" + nasc + ",";
            else
                sql+= "data_nascimento='" + nasc + "',";

            sql+= "email='" + email + "',"
                    + "updated_at=now() "
                    + "where id='" + id + "'";

            stmt.executeUpdate(sql);
        }
    }

    public void persist(Connection connection) throws SQLException{

        String sql = "select id from teste.curriculos where id = '" + id.toString() + "'";
        Statement stmt = connection.createStatement();


        ResultSet rs = stmt.executeQuery(sql);
        //checa se o curriculo jÃ¡ existe
        if (!rs.next()) {
            sql = "insert into teste.curriculos(id,data_atualizacao,nome_completo,resumo_cv,"
                    + "nome_citacoes,nome_instituicao_empresa,nome_orgao,ddd,telefone,homepage,created_at)"
                    + "values ('" + id.toString() + "',"
                    + "'" + Utils.dateFormat(DATA_ATUALIZACAO) + "', "
                    + "'" + Utils.strFormat(NOME_COMPLETO) + "', "
                    + "'" + Utils.strFormat(RESUMO_CV) + "', "
                    + "'" + Utils.strFormat(NOME_CITACOES) + "',"
                    + "'" + Utils.strFormat(NOME_INSTITUICAO_EMPRESA) + "',"
                    + "'" + Utils.strFormat(NOME_ORGAO) + "',"
                    + "'" + DDD + "',"
                    + "'" + TELEFONE + "',"
                    + "'" + Utils.strFormat(HOMEPAGE) + "', now()"
                    + ")";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
        }
        else {
            sql = "update teste.curriculos set "
                    + "data_atualizacao='" + Utils.dateFormat(DATA_ATUALIZACAO) + "', "
                    + "nome_completo='" + Utils.strFormat(NOME_COMPLETO) + "', "
                    + "resumo_cv='" + Utils.strFormat(RESUMO_CV) + "', "
                    + "nome_citacoes='" + Utils.strFormat(NOME_CITACOES) + "',"
                    + "nome_instituicao_empresa='" + Utils.strFormat(NOME_INSTITUICAO_EMPRESA) + "',"
                    + "nome_orgao='" + Utils.strFormat(NOME_ORGAO) + "',"
                    + "ddd='" + DDD + "',"
                    + "telefone='" + TELEFONE + "',"
                    + "homepage='" + Utils.strFormat(HOMEPAGE) + "',"
                    + "updated_at=now() "
                    + "where id='" + id.toString() + "'";

            stmt.executeUpdate(sql);
        }
    }
}
