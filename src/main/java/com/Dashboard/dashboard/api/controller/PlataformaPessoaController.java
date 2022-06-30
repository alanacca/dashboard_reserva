package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import com.Dashboard.dashboard.api.service.PlataformaPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plataformapessoa")
public class PlataformaPessoaController {

    @Autowired
    private PlataformaPessoaService plataformaPessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Plataforma_Pessoa> novo(@Valid @RequestBody PlataformaPessoaRequest plataformaPessoaRequest, HttpServletResponse response){
        Plataforma_Pessoa plataformaPessoaSalvo = this.plataformaPessoaService.salvarPlataformaPessoaByRequest(plataformaPessoaRequest);
        publisher.publishEvent(new RecursoCriadoEvent(this,response, plataformaPessoaSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(plataformaPessoaSalvo);
    }

    @GetMapping("/listar")
    public List<Plataforma_Pessoa> listar(){
        return plataformaPessoaService.findAll();
    }


}
