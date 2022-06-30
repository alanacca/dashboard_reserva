package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Plataformas;
import com.Dashboard.dashboard.api.service.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlatafomaController {

    @Autowired
    PlataformaService plataformaService;

    @GetMapping("/listar")
    public List<Plataformas> findAll(){
        return plataformaService.findAll();
    }


}
