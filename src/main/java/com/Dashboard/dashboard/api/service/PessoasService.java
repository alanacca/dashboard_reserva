package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.*;
import com.Dashboard.dashboard.api.repository.PessoasRepository;
import com.Dashboard.dashboard.api.request.PessoasRequest;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

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

//    public boolean existsByIdPlataforma(String IdPlataforma){ return this.pessoasRepository.existsByIdPlataforma(IdPlataforma);}

    public boolean existsBynomeCompleto(String nomeCompleto){ return this.pessoasRepository.existsBynomeCompleto(nomeCompleto);}

    public List<Pessoas> atualizarPessoas(List<Pessoas> pessoas){
        List<Pessoas> pessoasList = new ArrayList<>();
        pessoas.stream().forEach(item->{
            PessoasRequest pessoasRequest = new PessoasRequest();
            pessoasRequest.setNome_Completo(item.getNomeCompleto());
            pessoasRequest.setDoutorado(item.getDoutorado());
            pessoasRequest.setMestrado(item.getMestrado());
            Pessoas pessoa = new Pessoas(pessoasRequest);
            Pessoas pessoaAtualiza = new Pessoas(item.getIdPessoa());
            BeanUtils.copyProperties(pessoa,pessoaAtualiza,"idPessoa");
            Pessoas pessoaSalva = this.pessoasRepository.save(pessoaAtualiza);
            pessoasList.add(pessoaSalva);
        });
        return pessoasList;
    }
}