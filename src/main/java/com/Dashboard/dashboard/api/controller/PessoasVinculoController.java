package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.PessoasVinculo;
import com.Dashboard.dashboard.api.request.PessoasVinculoRequest;
import com.Dashboard.dashboard.api.service.PessoasVinculoService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoasVinculo")
public class PessoasVinculoController {

    private final PessoasVinculoService service;
    private final ApplicationEventPublisher publisher;

    public PessoasVinculoController(PessoasVinculoService service, ApplicationEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @GetMapping("/listar")
    public List<PessoasVinculo> listar(){
        return this.service.findAll();
    }

    @GetMapping("/vinculo/{idVinculo}")
    public List<Pessoas> listarEspecifico(@PathVariable Integer idVinculo){

        List<Pessoas> pessoas = this.service.getSemVinculoPorVinculo(idVinculo);
        return pessoas;
    }

    @PostMapping
    public ResponseEntity<PessoasVinculo> novo(@Valid @RequestBody PessoasVinculoRequest request,
                                               HttpServletResponse response
    ){
        PessoasVinculo pessoaVinculoSalva = this.service.salvarPessoasVinculoByRequest(request);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaVinculoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaVinculoSalva);

    }
}
