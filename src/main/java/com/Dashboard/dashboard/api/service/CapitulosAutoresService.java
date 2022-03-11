package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import com.Dashboard.dashboard.api.repository.CapitulosAutoresRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitulosAutoresService {

    @Getter
    @Autowired
    CapitulosAutoresRepository repo;

    public List<CapitulosAutores> findAllByCurriculo(Long fkCurriculo){
        return this.repo.getByCurriculo(fkCurriculo);
    }

    public Integer countCapitulosAutores(Long fkCurriculo){
        return this.repo.countCapitulosAutores(fkCurriculo);
    }

    public Page<CapitulosAutores> findCapitulosFiltro(Long fkCurriculo, Pageable pageable){
        return this.repo.capitulosFiltroPaginado(fkCurriculo,pageable);
    }



}
