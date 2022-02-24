package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.service.PesquisaService;
import com.Dashboard.dashboard.api.service.PessoasService;
import com.Dashboard.dashboard.api.service.PlataformaPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    PessoasService pessoasService;

    @Autowired
    PesquisaService pesquisaService;

    @Autowired
    PlataformaPessoaService plataformaPessoaService;

    @GetMapping("/{idPessoa}/{idPlat}")
    public void pesquisar(@PathVariable("idPessoa") Integer idPessoa,
                          @PathVariable("idPlat") Integer idPlat) throws SQLException, ClassNotFoundException {
        String defFolder = "";
        if(idPlat == 1){
            defFolder = "/home/alana/Documentos/DashBoard/Curriculos/";
        }

        Plataforma_Pessoa plataformaPessoa = this.plataformaPessoaService.findByFkPlataformaAndFkPessoa(idPlat, idPessoa);
        System.out.println("asd");
        System.out.println(plataformaPessoa);

        pesquisaService.Inicializador(defFolder);
        pesquisaService.unzip(defFolder+plataformaPessoa.getIdPlataforma()+".zip",defFolder+plataformaPessoa.getIdPlataforma()+".xml");
        try{
            pesquisaService.parseOneLattes(plataformaPessoa.getIdPlataforma(), defFolder+plataformaPessoa.getIdPlataforma()+".xml/"+"curriculo.xml",null);
        }catch (Exception e) {
            System.out.println("erro");
        }
        pesquisaService.finalizar();
    }
}
