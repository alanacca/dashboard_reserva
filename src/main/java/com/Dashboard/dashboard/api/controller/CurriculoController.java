package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.service.CurriculoService;
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
@RequestMapping("/curriculo")
public class CurriculoController {

    @Autowired
    private CurriculoService service;

    @GetMapping("/{idCurriculo}")
    public ResponseEntity<Curriculos> findByCurriculo(@PathVariable Long idCurriculo){
        Curriculos curriculo = this.service.findByCurriculo(idCurriculo);
        return new ResponseEntity<>(curriculo, HttpStatus.OK);
    }

    @GetMapping("estrato/{ano_inicio}/{ano_final}")
    public ResponseEntity<List<HashMap<String,String>>> findEstratosCurriculo(@PathVariable("ano_inicio") Integer ano_inicio,
                                                                              @PathVariable("ano_final") Integer ano_final){
        List<HashMap<String,String>> estratosCurriculo = this.service.estratoCurriculo(ano_inicio,ano_final);
        return new ResponseEntity<>(estratosCurriculo,HttpStatus.OK);
    }
}
