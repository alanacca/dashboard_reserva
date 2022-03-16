package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.Vinculo;
import com.Dashboard.dashboard.api.repository.VinculoRepository;
import com.Dashboard.dashboard.api.request.PessoasRequest;
import com.Dashboard.dashboard.api.request.VinculoRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinculoService {

    @Getter
    @Autowired
    VinculoRepository repo;

    public Vinculo salvarVinculoByVinculoRequest(VinculoRequest vinculoRequest){
        Vinculo vinculo = new Vinculo(vinculoRequest);
        return this.repo.save(vinculo);
    }

    public List<Vinculo> findAll(){
        return this.repo.findAll();
    }

}
