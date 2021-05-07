package com.rostand.farmbotapi.Repository;

import com.rostand.farmbotapi.Entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
