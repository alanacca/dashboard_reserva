package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.service.ArtigoEventoAutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/artigoEventoAutores")
public class ArtigoEventoAutoresController {
    @Autowired
    ArtigoEventoAutoresService service;

    @GetMapping("/listar")
    public Page<ArtigoEventoAutores> findAllByCurriculo(@PageableDefault(size=5) @SortDefault(sort = "id",direction = Sort.Direction.ASC) Pageable pageable
            , Long fkCurriculo){
        return this.service.findArtigosFiltro(fkCurriculo,pageable);
    }

    @GetMapping("/contar/{fkCurriculo}")
    public Integer countArtigoEvento(@PathVariable Long fkCurriculo){
        return this.service.countArtigoEvento(fkCurriculo);
    }
}
