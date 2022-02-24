package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Inicio;
import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.service.PesquisaService;
import com.Dashboard.dashboard.api.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    PessoasService pessoasService;

    @Autowired
    PesquisaService pesquisaService;

    @GetMapping("/{id}")
    public void pesquisar(@PathVariable Integer id) throws SQLException, ClassNotFoundException {

//        System.out.println(pessoasService.findByNome(id));

        Optional<Pessoas> activePessoa = pessoasService.findByNome(id);
        Pessoas pessoa = activePessoa.get();
//        return pessoa;
//        pesquisaService.Inicializador();
//        pesquisaService.unzip(Inicio.defFolder+pessoa.getIdPlataforma()+".zip",Inicio.defFolder+pessoa.getIdPlataforma()+".xml");
//        try{
//            pesquisaService.parseOneLattes(pessoa.getIdPlataforma(), Inicio.defFolder+pessoa.getIdPlataforma()+".xml/"+"curriculo.xml",null);
//        }catch (Exception e) {
//            System.out.println("erro");
//        }
//        pesquisaService.finalizar();
    }
}
