package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.PeriodicosAutores;
import com.Dashboard.dashboard.api.repository.PeriodicosAutoresRepository;
import com.Dashboard.dashboard.api.service.PeriodicosAutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/periodicosAutores")
public class PeriodicosAutoresController {

    @Autowired
    PeriodicosAutoresService service;

    @GetMapping("/listar/{fkCurriculo}")
    public List<PeriodicosAutores> findAllByCurriculo(@PathVariable Long fkCurriculo){
        return this.service.findAllByCurriculo(fkCurriculo);
    }

    @GetMapping("/contar/{fkCurriculo}")
    public Integer countPeriodicosAutores(@PathVariable Long fkCurriculo){
        return this.service.countPeriodicosAutores(fkCurriculo);
    }
}
