package com.rostand.farmbotapi.Repository;

import com.rostand.farmbotapi.Entity.Reglages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReglagesRepository extends JpaRepository<Reglages, Long>  {
    Optional<Reglages> findReglagesById(Long reglagesId);
}
