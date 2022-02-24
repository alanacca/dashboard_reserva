package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.repository.PlataformaPessoaRepository;
import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaPessoaService {

    @Getter
    @Autowired
    PlataformaPessoaRepository repo;

    public Plataforma_Pessoa salvarPlataformaPessoaByRequest(PlataformaPessoaRequest plataformaPessoaRequest){
        Plataforma_Pessoa plataformaPessoa = new Plataforma_Pessoa(plataformaPessoaRequest);
        return this.repo.save(plataformaPessoa);
    }

    public List<Plataforma_Pessoa> findAll(){
        return this.repo.findAll();
    }

    public Plataforma_Pessoa findByFkPlataformaAndFkPessoa(Integer fkPlataforma, Integer fkPessoa){
        return this.repo.findByFkPlataformaAndFkPessoa(fkPlataforma,fkPessoa);
    }
}
