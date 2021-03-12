package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreatePlanteDTO;
import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class PlanteController {

    // repository
    @Autowired
    PlanteRepository planteRepository;

    // ------------------------ PARTIE GET / CREATE / UPDATE / DELETE PLANTE ------------------------
    @GetMapping(path = "/plante/list")
    public List getPlantes() {
        return planteRepository.findAll();
    }

    @PostMapping(path = "/plante/create")
    @ResponseBody
    public void createPlante(@RequestBody CreatePlanteDTO planteDTO) {
        Plante p = Plante.builder()
                .nom(planteDTO.getNom())
                .description(planteDTO.getDescription())
                .description_courte(planteDTO.getDescription_courte())
                .icone(planteDTO.getIcone()).build();

        planteRepository.save(p);
    }

    @PutMapping(path = "plante/update/{planteId}")
    public ResponseEntity<Plante> updatePlante(@PathVariable Long planteId,
                                               @RequestBody CreatePlanteDTO planteDTO) throws NotFoundException {

        Optional<Plante> planteOpt = planteRepository.findById(planteId);
        planteOpt.orElseThrow(() -> new ResourceNotFoundException("Plante " + planteId + "not found"));

        Plante p = planteOpt.get();

        p.setNom(planteDTO.getNom());
        p.setDescription(planteDTO.getDescription());
        p.setDescription_courte(planteDTO.getDescription_courte());
        p.setIcone(planteDTO.getIcone());

        final Plante updatePlante = planteRepository.save(p);
        return ResponseEntity.ok(p);
    }

    @PostMapping(path = "plante/delete/{id}")
    @ResponseBody
    public void deletePlante(@PathVariable Long id) {
        planteRepository.deleteById(id);
    }

    // -------------------- PARTIE GET / DELETE PLANTE BY PLANTATION ---------------

   /* @GetMapping(path = "/plantation/{plantationId}/plante/list")
    public List<Plante> getAllPlantesByPlantation(@PathVariable (value = "plantationId") Long plantationId) {
        return planteRepository.findByPlantationId(plantationId);
    }*/
}
