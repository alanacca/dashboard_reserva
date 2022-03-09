package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Curriculos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculosRepository extends JpaRepository<Curriculos,Integer> {

    Curriculos findAllById(String numeroIdent);

}
