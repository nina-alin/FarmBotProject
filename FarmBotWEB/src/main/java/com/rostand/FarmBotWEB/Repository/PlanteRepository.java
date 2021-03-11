package com.rostand.FarmBotWEB.Repository;

import com.rostand.FarmBotWEB.Entity.Plantation;
import com.rostand.FarmBotWEB.Entity.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanteRepository extends JpaRepository<Plante, Long> {
   // List<Plante> findByChampIdAndPlantationId(Long champId, Long plantationId);
}
