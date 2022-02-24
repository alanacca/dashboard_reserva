package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Plataformas;
import com.Dashboard.dashboard.api.repository.PlataformasRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaService {

    @Getter
    @Autowired
    PlataformasRepository plataformasRepository;

    public List<Plataformas> findAll(){return plataformasRepository.findAll();}

    public Optional<Plataformas> findById(Integer id){return this.plataformasRepository.findById(id);}
}
