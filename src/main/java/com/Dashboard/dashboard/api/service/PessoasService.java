package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.repository.PessoasRepository;
import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoasService {

    @Getter
    @Autowired
    PessoasRepository pessoasRepository;

    public Pessoas salvarPessoaByPessoaRequest(PessoasRequest pessoasRequest){
        Pessoas pessoa = new Pessoas(pessoasRequest);
        return this.pessoasRepository.save(pessoa);
    }

    public List<Pessoas> findAll(){
        return this.pessoasRepository.findAll();
    }


}