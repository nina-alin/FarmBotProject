package com.rostand.FarmBotWEBv2.Repository;

import com.rostand.FarmBotWEBv2.Entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
