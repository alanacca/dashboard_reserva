package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.PlataformaPessoa;
import com.Dashboard.dashboard.api.repository.PlataformaPessoaRepository;
import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlataformaPessoaService {

    @Getter
    @Autowired
    PlataformaPessoaRepository repo;

    public PlataformaPessoa salvarPlataformaPessoaByRequest(PlataformaPessoaRequest plataformaPessoaRequest){
        PlataformaPessoa plataformaPessoa = new PlataformaPessoa(plataformaPessoaRequest);
        return this.repo.save(plataformaPessoa);
    }
}
