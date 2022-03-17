package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.Pessoas_Vinculo;
import com.Dashboard.dashboard.api.model.Plataforma_Pessoa;
import com.Dashboard.dashboard.api.model.Vinculo;
import com.Dashboard.dashboard.api.request.PessoasVinculoRequest;
import com.Dashboard.dashboard.api.request.VinculoRequest;
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
    public List<Pessoas_Vinculo> listar(){
        return this.service.findAll();
    }

    @GetMapping("/vinculo/{idVinculo}")
    public List<Plataforma_Pessoa> listarEspecifico(@PathVariable Integer idVinculo){
        return this.service.getSemVinculoPorVinculo(idVinculo);
    }

    @GetMapping("/verificar/{idVinculo}")
    public ResponseEntity<List<Pessoas_Vinculo>> verificarLista(@Valid @RequestBody List<Plataforma_Pessoa> pessoas,
                                                @PathVariable("idVinculo") Integer idVinculo){
        return new ResponseEntity<>(this.service.verificacaoLista(pessoas,idVinculo),HttpStatus.OK);

    }

    @GetMapping("/{idVinculo}")
    public List<Plataforma_Pessoa> listarByVinculo(@PathVariable Integer idVinculo){
        return this.service.getByVinculo(idVinculo);
    }

    @PostMapping
    public ResponseEntity<Pessoas_Vinculo> novo(@Valid @RequestBody PessoasVinculoRequest request,
                                        HttpServletResponse response
    ){
        Pessoas_Vinculo pessoaVinculoSalva = this.service.salvarPessoasVinculoByRequest(request);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaVinculoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaVinculoSalva);

    }




}
