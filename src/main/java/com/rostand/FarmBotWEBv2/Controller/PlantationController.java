package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreatePlantationDTO;
import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ChampRepository;
import com.rostand.FarmBotWEBv2.Repository.PlantationRepository;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // IL MANQUE LA LIAISON avec la table Plante qui fait tout buguer

    @GetMapping(path = "/champ/{champId}/plantation/list")
    public List<Plantation> getAllPlantationByChampId(@PathVariable (value = "champId") Long champId) {
        return plantationRepository.findByChampId(champId);
    }

    // PROBLEME : lorsqu'on crée une nouvelle plantation l'ID_CHAMP ne s'affecte pas tout seul
    @PostMapping(path = "/champ/{champId}/plantation/create")
    public Plantation createPlantation(@PathVariable (value = "champId") Long champId,
                                       @RequestBody CreatePlantationDTO plantationDTO) {
        Plantation p = new Plantation();

        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());

        return champRepository.findById(champId).map(champ -> {
            p.setChamp(plantationDTO.getChamp());
            return plantationRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("ChampId " + champId + " not found"));
    }

    @PutMapping(path = "/plantation/update/{plantationId}")
    public Plantation updatePlantation(@PathVariable (value = "champId") Long champId,
                                       @PathVariable (value = "plantationId") Long plantationId,
                                       @RequestBody CreatePlantationDTO plantationDTO) {

        Plantation p = new Plantation();

        if(!champRepository.existsById(champId)) {
            throw new ResourceNotFoundException("ChampId " + champId + " not found");
        }

        return plantationRepository.findById(plantationId).map(plantation -> {
            p.setX(plantationDTO.getX());
            p.setY(plantationDTO.getY());

            return plantationRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("PlantationId " + plantationId + " not found"));
    }

    @DeleteMapping(path = "/champ/{champId}/plantation/delete/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable (value = "plantationId") Long plantationId,
                                              @PathVariable (value = "champId") Long champId) {

        return plantationRepository.findByIdAndChampId(plantationId, champId).map(plantation -> {
            plantationRepository.delete(plantation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Plantation non trouvée avec l'id " + plantationId + "et champId" + champId));
    }
}
