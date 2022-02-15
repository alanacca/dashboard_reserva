package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.*;
import com.Dashboard.dashboard.api.repository.PessoasRepository;
import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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

    public Optional<Pessoas> findByNome(Integer id){return this.pessoasRepository.findById(id);}

    public boolean existsByIdPlataforma(String IdPlataforma){ return this.pessoasRepository.existsByIdPlataforma(IdPlataforma);}

    public boolean existsBynomeCompleto(String nomeCompleto){ return this.pessoasRepository.existsBynomeCompleto(nomeCompleto);}


}