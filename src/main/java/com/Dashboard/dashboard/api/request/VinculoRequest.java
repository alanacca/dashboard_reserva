package com.Dashboard.dashboard.api.request;

import javax.persistence.Column;

public class VinculoRequest {

    public String sigla;

    public String descricao;

    @Override
    public String toString() {
        return "VinculoRequest{" +
                "sigla='" + sigla + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
