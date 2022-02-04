package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    PessoasService pessoasService;

    @GetMapping("/{id}")
    public Optional<Pessoas> pesquisar(@PathVariable Integer id){
        return pessoasService.findByNome(id);
    }
}
