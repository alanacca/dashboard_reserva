package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.PeriodicosAutores;
import com.Dashboard.dashboard.api.repository.PeriodicosAutoresRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicosAutoresService {

    @Getter
    @Autowired
    PeriodicosAutoresRepository repo;

    public List<PeriodicosAutores> findAllByCurriculo(Long fkCurriculo){
        return this.repo.getByCurriculo(fkCurriculo);
    }

    public Integer countPeriodicosAutores(Long fkCurriculo){
        return this.repo.countPeriodicosAutores(fkCurriculo);
    }
}
