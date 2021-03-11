package com.rostand.FarmBotWEB.Repository;

import com.rostand.FarmBotWEB.Entity.Reglages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReglagesRepository extends JpaRepository<Reglages, Long>  {
}
