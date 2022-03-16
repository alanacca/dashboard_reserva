package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.PessoasVinculo;
import com.Dashboard.dashboard.api.repository.PessoasVinculoRepository;
import com.Dashboard.dashboard.api.request.PessoasVinculoRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoasVinculoService {
    @Getter
    @Autowired
    PessoasVinculoRepository repo;

    public PessoasVinculo salvarPessoasVinculoByRequest(PessoasVinculoRequest request){
        PessoasVinculo pessoas_vinculo = new PessoasVinculo(request);
        return this.repo.save(pessoas_vinculo);
    }

    public List<PessoasVinculo> findAll(){
        return this.repo.findAll();
    }

    public List<Pessoas> getSemVinculoPorVinculo(Integer idVinculo){
        return this.repo.getSemVinculoPorVinculo(idVinculo);
    }

}
