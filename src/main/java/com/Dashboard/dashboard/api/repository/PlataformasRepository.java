package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import com.Dashboard.dashboard.api.model.Plataformas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlataformasRepository extends JpaRepository<Plataformas, Integer> {

    @Override
    Optional<Plataformas> findById(Integer id);

}
