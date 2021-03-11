package com.rostand.FarmBotWEB.Repository;

import com.rostand.FarmBotWEB.Entity.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {
    List<Plantation> findByChampId(Long champId);
    Optional<Plantation> findByIdAndChampId(Long plantationId, Long champId);

    // https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
}
