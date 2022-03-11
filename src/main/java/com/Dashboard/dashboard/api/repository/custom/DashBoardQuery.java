package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.CapitulosAutores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DashBoardQuery {
    Page<CapitulosAutores> capitulosPaginado(Long fk_curriculo, Pageable pageable);
}
