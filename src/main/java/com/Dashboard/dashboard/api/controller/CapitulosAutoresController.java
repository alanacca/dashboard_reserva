package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import com.Dashboard.dashboard.api.service.CapitulosAutoresService;
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

import java.util.List;

@RestController
@RequestMapping("/capitulosAutores")
public class CapitulosAutoresController {

    @Autowired
    CapitulosAutoresService service;

    @GetMapping("/listar")
    public Page<CapitulosAutores> findAllByCurriculo(@PageableDefault(size=5) @SortDefault(sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
                                                     Long fkCurriculo){
        return this.service.findCapitulosFiltro(fkCurriculo,pageable);
    }

    @GetMapping("/contar/{fkCurriculo}")
    public Integer countCapitulosAutores(@PathVariable Long fkCurriculo){
        return this.service.countCapitulosAutores(fkCurriculo);
    }

}
