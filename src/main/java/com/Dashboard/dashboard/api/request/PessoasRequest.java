package com.Dashboard.dashboard.api.request;

public class PessoasRequest {
    public String Nome_Completo;

    public Boolean mestrado;

    public Boolean doutorado;

    public void setNome_Completo(String nome_Completo) {
        Nome_Completo = nome_Completo;
    }

    public void setMestrado(Boolean mestrado) {
        this.mestrado = mestrado;
    }

    public void setDoutorado(Boolean doutorado) {
        this.doutorado = doutorado;
    }

    @Override
    public String toString() {
        return "PessoasRequest{" +
                "Nome_Completo='" + Nome_Completo + '\'' +
                ", mestrado=" + mestrado +
                ", doutorado=" + doutorado +
                '}';
    }
}
