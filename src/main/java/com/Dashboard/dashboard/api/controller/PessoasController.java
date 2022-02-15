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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {
    @Autowired
    private PessoasService pessoasService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/verificaNome/{nome}")
    public boolean existsBynomeCompleto(@PathVariable String nome){
        return pessoasService.existsBynomeCompleto(nome);
    }

    @GetMapping("/verificaId/{id}")
    public boolean existsByIdPlataforma(@PathVariable String id){return pessoasService.existsByIdPlataforma(id);}

    @GetMapping("/listar")
    public List<Pessoas> listar(){
        return pessoasService.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoas> novo(@Valid @RequestBody PessoasRequest pessoasRequest, HttpServletResponse response){
        Pessoas pessoasSalvo = this.pessoasService.salvarPessoaByPessoaRequest(pessoasRequest);
        publisher.publishEvent(new RecursoCriadoEvent(this,response,pessoasSalvo.getIdPessoa()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoasSalvo);
    }
}
