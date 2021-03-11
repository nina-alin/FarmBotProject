package com.rostand.FarmBotWEB.Controller;

import com.rostand.FarmBotWEB.DTO.CreatePlanteDTO;
import com.rostand.FarmBotWEB.Entity.Plante;
import com.rostand.FarmBotWEB.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEB.Repository.PlanteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class PlanteController {
    @Autowired
    PlanteRepository planteRepository;

    @GetMapping(path = "/plante/list")
    public List getPlantes() {
        return planteRepository.findAll();
    }

    /*@GetMapping(path = "/champ/{champId}/plantation/{plantationId}/plante/list")
    public List<Plante> getPlanteByPlantation(@PathVariable (value = "plantationId") Long plantationId,
                                              @PathVariable (value = "champId") Long champId) {
        return planteRepository.findByChampIdAndPlantationId(champId, plantationId);
    }*/

    @PostMapping(path = "/plante/create")
    @ResponseBody
    public void createPlante(@RequestBody CreatePlanteDTO planteDTO) {
        Plante p = new Plante();
        p.setNom(planteDTO.getNom());
        p.setDescription(planteDTO.getDescription());
        p.setDescription_courte(planteDTO.getDescription_courte());
        p.setIcone(planteDTO.getIcone());
        planteRepository.save(p);
    }

    @PutMapping(path = "plante/update/{id}")
    public ResponseEntity<Plante> updatePlante(@PathVariable Long id, @RequestBody CreatePlanteDTO planteDTO) throws NotFoundException {
        Optional<Plante> optionalPlante = planteRepository.findById(id);
        Plante p = optionalPlante.get();
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
}
