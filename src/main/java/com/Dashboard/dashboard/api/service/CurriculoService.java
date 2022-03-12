package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.repository.CurriculosRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculoService {

    @Getter
    @Autowired
    CurriculosRepository repo;

    public Curriculos findByCurriculo(Long idCurriculo){
        return this.repo.findByCurriculo(idCurriculo);
    }
}
