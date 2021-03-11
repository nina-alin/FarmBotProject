package com.rostand.FarmBotWEB.Repository;

import com.rostand.FarmBotWEB.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
}
