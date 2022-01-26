package com.Dashboard.dashboard.api.request;

public class PessoasRequest {
    public String Nome_Completo;

    public String idPlataforma;

    public String Plataforma;

    @Override
    public String toString(){
        return "PessoasRequest [nome="+Nome_Completo+", idPlataforma="+idPlataforma+",Plataforma="+Plataforma+"]";
    }
}
