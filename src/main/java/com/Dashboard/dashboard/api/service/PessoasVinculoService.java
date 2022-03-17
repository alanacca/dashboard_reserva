package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.Pessoas_Vinculo;
import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.model.Vinculo;
import com.Dashboard.dashboard.api.repository.PessoasVinculoRepository;
import com.Dashboard.dashboard.api.repository.PlataformaPessoaRepository;
import com.Dashboard.dashboard.api.repository.VinculoRepository;
import com.Dashboard.dashboard.api.request.PessoasVinculoRequest;
import com.Dashboard.dashboard.api.request.VinculoRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoasVinculoService {
    @Getter
    @Autowired
    PessoasVinculoRepository repo;

    @Autowired
    PlataformaPessoaRepository repoPlataforma;

    public Pessoas_Vinculo salvarPessoasVinculoByRequest(PessoasVinculoRequest request){
        Pessoas_Vinculo pessoas_vinculo = new Pessoas_Vinculo(request);
        return this.repo.save(pessoas_vinculo);
    }

    public List<Pessoas_Vinculo> verificacaoLista(List<Plataforma_Pessoa> pessoas, Integer idVinculo){
        List<Pessoas_Vinculo> pessoas_vinculos = this.repo.findAll();

        pessoas.stream().forEach( pessoa ->{
            if(!pessoas_vinculos.contains(pessoa)){
                PessoasVinculoRequest pessoasVinculoRequest = new PessoasVinculoRequest();
                pessoasVinculoRequest.setFk_pessoa(pessoa.getFkPessoa().getIdPessoa());
                pessoasVinculoRequest.setVinculoId(idVinculo);
                this.salvarPessoasVinculoByRequest(pessoasVinculoRequest);
            }
        });
        return this.repo.findAll();
    }

    public List<Pessoas_Vinculo> findAll(){
        return this.repo.findAll();
    }

    public List<Plataforma_Pessoa> getSemVinculoPorVinculo(Integer idVinculo){
        List<Integer> listaIds = this.repo.getSemVinculoPorVinculo(idVinculo);
        List<Plataforma_Pessoa> listaPessoas = new ArrayList<>();
        listaIds.stream().forEach( id -> {
            listaPessoas.add(this.repoPlataforma.findByFkPessoa(id));
        });
        return listaPessoas;
    }
    public List<Plataforma_Pessoa> getByVinculo(Integer idVinculo){
        List<Integer> listaIds = this.repo.getbyVinculo(idVinculo);
        List<Plataforma_Pessoa> listaPessoas = new ArrayList<>();
        listaIds.stream().forEach( id -> {
            listaPessoas.add(this.repoPlataforma.findByFkPessoa(id));
        });
        return listaPessoas;
    }


}
