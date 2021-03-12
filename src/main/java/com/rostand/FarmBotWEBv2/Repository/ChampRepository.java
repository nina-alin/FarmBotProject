package com.rostand.FarmBotWEBv2.Repository;

import com.rostand.FarmBotWEBv2.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
}
