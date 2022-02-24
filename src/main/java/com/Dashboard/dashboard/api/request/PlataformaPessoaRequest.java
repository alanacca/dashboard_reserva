package com.Dashboard.dashboard.api.request;


import com.Dashboard.dashboard.api.model.Pessoas;

public class PlataformaPessoaRequest {
    public String idPlataforma;

    public Integer fkPlataforma;

    public Pessoas fkPessoa;

    @Override
    public String toString(){
        return "PlataformaPessoa [idPLataforma="+idPlataforma+", fkPlataforma="+fkPlataforma+", fkPessoa="+fkPessoa+"]";
    }

}
