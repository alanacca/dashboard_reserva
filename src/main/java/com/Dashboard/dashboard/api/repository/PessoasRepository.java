package com.Dashboard.dashboard.api.repository;

import com.Dashboard.dashboard.api.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<Pessoas, Integer> {
}
