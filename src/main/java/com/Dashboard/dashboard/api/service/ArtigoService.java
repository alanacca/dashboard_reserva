package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Periodicos;
import com.Dashboard.dashboard.api.repository.ArtigoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {

    @Getter
    @Autowired
    ArtigoRepository artigoRepository;

    public List<Periodicos> findAll(){return this.artigoRepository.findAll();}
}
