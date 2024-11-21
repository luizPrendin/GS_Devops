package com.api.gs.repository;

import com.api.gs.model.PrecoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoEnergiaRepository extends JpaRepository<PrecoEnergia,Long> {
}
