package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Periodicos;
import com.Dashboard.dashboard.api.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {
    @Autowired
    ArtigoService artigoService;

    @GetMapping("/listar")
    public List<Periodicos> findAll(){
        return artigoService.findAll();
    }
}
