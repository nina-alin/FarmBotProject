package com.rostand.FarmBotWEBv2.Repository;

import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {
    Iterable<Plantation> findByChampId(Long champId);
    Optional<Plantation> findByIdAndChampId(Long plantationId, Long champId);
    Iterable<Plantation> findByChampIdAndXAndY(Long champId, Integer x, Integer y);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END from Plantation p where p.champ.id = :champId and p.x = :x and p.y = :y")
    Boolean checkAlreadyExist(Long champId, int x, int y);
}
