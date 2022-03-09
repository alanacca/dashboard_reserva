package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.service.ArtigoEventoAutoresService;
import com.Dashboard.dashboard.api.service.CapitulosAutoresService;
import com.Dashboard.dashboard.api.service.PeriodicosAutoresService;
import com.Dashboard.dashboard.api.utils.Producao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
    @Autowired
    PeriodicosAutoresService periodicosService;

    @Autowired
    CapitulosAutoresService capitulosService;

    @Autowired
    ArtigoEventoAutoresService artigosService;

    @GetMapping("/contar/{fkCurriculo}")
    public ResponseEntity<HashMap<String, Integer>> countProducoes(@PathVariable Long fkCurriculo){
        HashMap<String,Integer> list = new HashMap<>();
        list.put("Capitulo", this.capitulosService.countCapitulosAutores(fkCurriculo));
        list.put("Artigo_Eventos", this.artigosService.countArtigoEvento(fkCurriculo));
        list.put("Periodico", this.periodicosService.countPeriodicosAutores(fkCurriculo));
        return new ResponseEntity<HashMap<String, Integer>>(list, HttpStatus.OK);
    }
}
