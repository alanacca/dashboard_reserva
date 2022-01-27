package com.Dashboard.dashboard.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @GetMapping("/{nome}")
    public String pesquisar(@PathVariable String nome){
        return nome;
    }
}
