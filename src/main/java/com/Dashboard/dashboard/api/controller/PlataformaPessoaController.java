package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.PlataformaPessoa;
import com.Dashboard.dashboard.api.model.Plataformas;
import com.Dashboard.dashboard.api.request.PlataformaPessoaRequest;
import com.Dashboard.dashboard.api.service.PlataformaPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/plataformapessoa")
public class PlataformaPessoaController {

    @Autowired
    private PlataformaPessoaService plataformaPessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<PlataformaPessoa> novo(@Valid @RequestBody PlataformaPessoaRequest plataformaPessoaRequest, HttpServletResponse response){
        PlataformaPessoa plataformaPessoaSalvo = this.plataformaPessoaService.salvarPlataformaPessoaByRequest(plataformaPessoaRequest);
        publisher.publishEvent(new RecursoCriadoEvent(this,response, plataformaPessoaSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(plataformaPessoaSalvo);
    }

}
