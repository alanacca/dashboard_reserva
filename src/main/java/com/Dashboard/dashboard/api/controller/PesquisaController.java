package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    PessoasService pessoasService;

    @GetMapping("/{nome}")
    public Pessoas pesquisar(@PathVariable String nome){
        return pessoasService.findByNome(nome);
    }
}
