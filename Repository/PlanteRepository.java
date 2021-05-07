package com.rostand.farmbotapi.Repository;

import com.rostand.farmbotapi.Entity.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanteRepository extends JpaRepository<Plante, Long> {
    Optional<Plante> findPlanteById(Long planteId);
}