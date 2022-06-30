package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.PeriodicosAutores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeriodicosQuery {
    Page<PeriodicosAutores> periodicosFiltroPaginado(Long fk_curriculo, Pageable pageable);

}
