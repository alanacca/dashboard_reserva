package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import com.Dashboard.dashboard.api.service.CapitulosAutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/capitulosAutores")
public class CapitulosAutoresController {

    @Autowired
    CapitulosAutoresService service;

    @GetMapping("/listar/{fkCurriculo}")
    public List<CapitulosAutores> findAllByCurriculo(@PathVariable Long fkCurriculo){
        return this.service.findAllByCurriculo(fkCurriculo);
    }

    @GetMapping("/contar/{fkCurriculo}")
    public Integer countCapitulosAutores(@PathVariable Long fkCurriculo){
        return this.service.countCapitulosAutores(fkCurriculo);
    }

}
