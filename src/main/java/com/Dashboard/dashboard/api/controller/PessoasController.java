package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.request.PessoasRequest;
import com.Dashboard.dashboard.api.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {
    @Autowired
    private PessoasService pessoasService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/listar")
    public String listar(){
        return "Testando confirmaas";
    }

    @PostMapping
    public ResponseEntity<Pessoas> novo(@Valid @RequestBody PessoasRequest pessoasRequest, HttpServletResponse response){
        Pessoas pessoasSalvo = this.pessoasService.salvarPessoaByPessoaRequest(pessoasRequest);
        publisher.publishEvent(new RecursoCriadoEvent(this,response,pessoasSalvo.getIdPessoa()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoasSalvo);
    }
}
