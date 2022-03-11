package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.service.PesquisaService;
import com.Dashboard.dashboard.api.service.PessoasService;
import com.Dashboard.dashboard.api.service.PlataformaPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Plataforma_Pessoa> pesquisar(@PathVariable("idPessoa") Integer idPessoa,
                                    @PathVariable("idPlat") Integer idPlat) throws SQLException, ClassNotFoundException {
        String defFolder = "";
        if(idPlat == 1){
            defFolder = "/home/alana/Documentos/DashBoard/Curriculos/";
        }

        Plataforma_Pessoa plataformaPessoa = this.plataformaPessoaService.findByFkPlataformaAndFkPessoa(idPlat, idPessoa);

        pesquisaService.Inicializador(defFolder);
        pesquisaService.unzip(defFolder+plataformaPessoa.getIdPlataforma().toString()+".zip",defFolder+plataformaPessoa.getIdPlataforma().toString()+".xml");
        try{
            pesquisaService.parseOneLattes(plataformaPessoa.getIdPlataforma().toString(), defFolder+plataformaPessoa.getIdPlataforma().toString()+".xml/"+"curriculo.xml",null);
        }catch (Exception e) {
            System.out.println("erro");
        }
        pesquisaService.finalizar();
        return new ResponseEntity<>(plataformaPessoa, HttpStatus.OK);
    }
}
