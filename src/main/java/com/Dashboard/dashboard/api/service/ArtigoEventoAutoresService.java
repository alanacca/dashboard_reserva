package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.repository.ArtigoEventoAutoresRepository;
import com.Dashboard.dashboard.api.repository.CurriculosRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class ArtigoEventoAutoresService {

    @Getter
    @Autowired
    ArtigoEventoAutoresRepository repo;

    public List<ArtigoEventoAutores> findAllByCurriculo(Long fkCurriculo){
        return this.repo.getByCurriculo(fkCurriculo);
    }

    public Integer countArtigoEvento(Long fkCurriculo){
        return this.repo.countArtigoEvento(fkCurriculo);
    }

    public Page<ArtigoEventoAutores> findArtigosFiltro(Long fkCurriculo, Pageable pageable){
        return this.repo.artigosFiltroPaginado(fkCurriculo,pageable);
    }
}
