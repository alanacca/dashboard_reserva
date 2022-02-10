package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Artigo;
import com.Dashboard.dashboard.api.repository.ArtigoRepository;
import com.Dashboard.dashboard.api.repository.PessoasRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {

    @Getter
    @Autowired
    ArtigoRepository artigoRepository;

    public List<Artigo> findAll(){return this.artigoRepository.findAll();}
}
