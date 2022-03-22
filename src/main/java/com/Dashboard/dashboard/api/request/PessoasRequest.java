package com.Dashboard.dashboard.api.request;

public class PessoasRequest {
    public String Nome_Completo;

    public Boolean mestrado;

    public Boolean doutorado;

    @Override
    public String toString() {
        return "PessoasRequest{" +
                "Nome_Completo='" + Nome_Completo + '\'' +
                ", mestrado=" + mestrado +
                ", doutorado=" + doutorado +
                '}';
    }
}
