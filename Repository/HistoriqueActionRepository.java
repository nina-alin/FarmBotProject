package com.rostand.farmbotapi.Repository;

import com.rostand.farmbotapi.Entity.HistoriqueAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, Long> {
    Optional<HistoriqueAction> findHistoriqueActionById(Long historiqueActionId);

   /* @Query("SELECT MAX(h.dateAction) FROM HistoriqueAction h where h.typeAction = :type")
    Timestamp getDateActionArrosage(HistoriqueAction.eTypeAction type);*/

}
