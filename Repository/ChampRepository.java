package com.rostand.farmbotapi.Repository;

import com.rostand.farmbotapi.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
    Optional<Champ> findChampById(Long champId);
}
