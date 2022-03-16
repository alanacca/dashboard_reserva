package com.Dashboard.dashboard.api.request;

import com.Dashboard.dashboard.api.model.Pessoas;

public class PessoasVinculoRequest {

    public Integer fk_pessoa;

    public Integer vinculoId;

    @Override
    public String toString(){
        return "PessoasVinculoRequest [fk_pessoa="+ fk_pessoa +
                ", vinculo=" + vinculoId +"]";
    }
}
