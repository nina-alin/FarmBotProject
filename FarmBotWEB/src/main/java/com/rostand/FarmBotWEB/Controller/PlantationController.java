package com.rostand.FarmBotWEB.Controller;

import com.rostand.FarmBotWEB.DTO.CreatePlantationDTO;
import com.rostand.FarmBotWEB.Entity.Plantation;
import com.rostand.FarmBotWEB.Entity.Plante;
import com.rostand.FarmBotWEB.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEB.Repository.ChampRepository;
import com.rostand.FarmBotWEB.Repository.PlantationRepository;
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
    @Autowired
    PlantationRepository plantationRepository;

    @Autowired
    ChampRepository champRepository;

    /*@GetMapping(path = "/plantation/list")
    public List getPlantations() {
        return plantationRepository.findAll();
    }*/

    @GetMapping(path = "/champ/{champId}/plantation/list")
    public List<Plantation> getAllPlantationByChampId(@PathVariable (value = "champId") Long champId) {
        return plantationRepository.findByChampId(champId);
    }

   /* @PostMapping(path = "/plantation/create")
    @ResponseBody
    public void createPlantation(@RequestBody CreatePlantationDTO plantationDTO) {
        Plantation p = new Plantation();
        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());
        plantationRepository.save(p);
    }*/

    @PostMapping(path = "/champ/{champId}/plantation/create")
    public Plantation createPlantation(@PathVariable (value = "champId") Long champId, @RequestBody CreatePlantationDTO plantationDTO) {
        Plantation p = new Plantation();
        return champRepository.findById(champId).map(champ -> {
            p.setChamp(plantationDTO.getChamp());
            return plantationRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("ChampId " + champId + " not found"));
    }

    /*@PutMapping(path = "/plantation/update/{id}")
    public ResponseEntity<Plantation> updatePlantation(@PathVariable Long id, @RequestBody CreatePlantationDTO plantationDTO) throws NotFoundException {
        Optional<Plantation> optionalPlantation = plantationRepository.findById(id);
        Plantation p = optionalPlantation.get();
        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());
        final Plantation updatePlantation = plantationRepository.save(p);
        return ResponseEntity.ok(p);
    }*/

    @PutMapping(path = "/champ/{champId}/plantation/update/{plantationId}")
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

    /*@PostMapping(path = "/plantation/delete/{id}")
    @ResponseBody
    public void deletePlantation(@PathVariable Long id) {
        plantationRepository.deleteById(id);
    }*/

    @DeleteMapping(path = "/champ/{champId}/plantation/delete/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable (value = "plantationId") Long plantationId,
                                              @PathVariable (value = "champId") Long champId) {
        return plantationRepository.findByIdAndChampId(plantationId, champId).map(plantation -> {
            plantationRepository.delete(plantation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Plantation non trouvée avec l'id " + plantationId + "et champId" + champId));
    }
}
