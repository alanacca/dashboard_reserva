package com.Dashboard.dashboard.api.controller;

import com.Dashboard.dashboard.api.event.RecursoCriadoEvent;
import com.Dashboard.dashboard.api.model.Vinculo;
import com.Dashboard.dashboard.api.request.VinculoRequest;
import com.Dashboard.dashboard.api.service.VinculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationEventPublisher;



import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vinculo")
public class VinculoController {

    private final VinculoService vinculoService;
    private final ApplicationEventPublisher publisher;

    public VinculoController(VinculoService vinculoService,
    ApplicationEventPublisher publisher) {
        this.vinculoService = vinculoService;
        this.publisher = publisher;
    }

    @GetMapping("/listar")
    public List<Vinculo> listar(){
        return this.vinculoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Vinculo> novo(@Valid @RequestBody VinculoRequest request,
                                        HttpServletResponse response
    ){
        Vinculo vinculoSalva = this.vinculoService.salvarVinculoByVinculoRequest(request);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, vinculoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(vinculoSalva);

    }
}
