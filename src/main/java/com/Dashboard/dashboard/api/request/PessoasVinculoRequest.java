package com.Dashboard.dashboard.api.request;

import com.Dashboard.dashboard.api.model.Pessoas;

public class PessoasVinculoRequest {

    public Integer fk_pessoa;

    public Integer vinculoId;

    public Integer getFk_pessoa() {
        return fk_pessoa;
    }

    public void setFk_pessoa(Integer fk_pessoa) {
        this.fk_pessoa = fk_pessoa;
    }

    public Integer getVinculoId() {
        return vinculoId;
    }

    public void setVinculoId(Integer vinculoId) {
        this.vinculoId = vinculoId;
    }

    @Override
    public String toString(){
        return "PessoasVinculoRequest [fk_pessoa="+ fk_pessoa +
                ", vinculo=" + vinculoId +"]";
    }
}
