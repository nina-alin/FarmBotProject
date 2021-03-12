package com.rostand.FarmBotWEBv2.Repository;

import com.rostand.FarmBotWEBv2.Entity.Reglages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReglagesRepository extends JpaRepository<Reglages, Long>  {
}
