package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.CapitulosAutores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtigosQuery {
    Page<ArtigoEventoAutores> artigosFiltroPaginado(Long fk_curriculo, Pageable pageable);

}
