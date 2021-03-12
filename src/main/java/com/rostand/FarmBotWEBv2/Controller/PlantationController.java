package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreatePlantationDTO;
import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.BadRequestException;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ChampRepository;
import com.rostand.FarmBotWEBv2.Repository.PlantationRepository;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class PlantationController {

    // repositories
    @Autowired
    PlantationRepository plantationRepository;

    @Autowired
    ChampRepository champRepository;

    @Autowired
    PlanteRepository planteRepository;


    //------------------- PARTIE GET / CREATE / UPDATE / DELETE PLANTATION BY CHAMP -----------------

    @GetMapping(path = "/champ/{champId}/plantation/list")
    public List<Plantation> getAllPlantationByChampId(@PathVariable(value = "champId") Long champId) {
        return plantationRepository.findByChampId(champId);
    }

    @PostMapping(path = "/champ/{champId}/plantation/create")
    public Plantation createPlantation(@PathVariable(value = "champId") Long champId,@Valid @RequestBody CreatePlantationDTO plantationDTO) {


        Optional<Champ> champOpt = champRepository.findById(champId);
        champOpt.orElseThrow(() -> new ResourceNotFoundException("Champ " + champId + "not found"));

        Optional<Plante> planteOpt = planteRepository.findById(plantationDTO.getPlanteId());
        planteOpt.orElseThrow(() -> new ResourceNotFoundException("Plante " + plantationDTO.getPlanteId() + "not found"));


        // Vérifier qu'une plantation n'existe pas déjà à ces coordonnées.
        if (plantationRepository.checkAlreadyExist(champId, plantationDTO.getX(), plantationDTO.getY())){
            throw new BadRequestException("Une plantation existe déjà aux coordonnées X: "
                    +plantationDTO.getX()+",Y:"+plantationDTO.getY());
        }

        Plantation p = Plantation.builder()
                .x(plantationDTO.getX())
                .y(plantationDTO.getY())
                .plante(planteOpt.get())
                .champ(champOpt.get()).build();

        plantationRepository.save(p);
        return p;
    }

    @PutMapping(path = "champ/{champId}/plantation/update/{plantationId}")
    public Plantation updatePlantation(@PathVariable(value = "champId") Long champId, @PathVariable(value = "plantationId") Long plantationId, @RequestBody CreatePlantationDTO plantationDTO) {

        Optional<Champ> champOpt = champRepository.findById(champId);
        champOpt.orElseThrow(() -> new ResourceNotFoundException("Champ " + champId + "not found"));

        Optional<Plantation> plantationOpt = plantationRepository.findById(plantationId);
        plantationOpt.orElseThrow(() -> new ResourceNotFoundException("Plantation " + plantationId + "not found"));

        Optional<Plante> planteOpt = planteRepository.findById(plantationDTO.getPlanteId());
        planteOpt.orElseThrow(() -> new ResourceNotFoundException("Plante " + plantationDTO.getPlanteId() + "not found"));


        Plantation p = plantationOpt.get();

        if (!p.getChamp().getId().equals(champId)){
            throw new BadRequestException("Le champ de la plantation("+p.getChamp().getNom()+") ne correspond pas au champ en paramètre("+champOpt.get().getNom()+")");
        }

        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());
        p.setPlante(planteOpt.get());

        plantationRepository.save(p);
        return p;
    }

    @DeleteMapping(path = "/champ/{champId}/plantation/delete/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable(value = "plantationId") Long plantationId,
                                              @PathVariable(value = "champId") Long champId) {

        return plantationRepository.findByIdAndChampId(plantationId, champId).map(plantation -> {
            plantationRepository.delete(plantation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Plantation non trouvée avec l'id " + plantationId + "et champId" + champId));
    }
}
