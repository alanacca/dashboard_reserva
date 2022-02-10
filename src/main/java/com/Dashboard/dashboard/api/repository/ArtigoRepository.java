package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<Artigo, Integer> {
}
