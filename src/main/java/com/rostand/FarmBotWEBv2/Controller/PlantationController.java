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
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.rostand.FarmBotWEBv2.constants.Constants.*;
import static java.lang.Thread.sleep;

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
    public Iterable<Plantation> getPlantationsById(@PathVariable(value = "champId") Long champId,
                                                   @RequestParam(value = "x", required = false) Integer x,
                                                   @RequestParam(value = "y", required = false) Integer y)
            throws ResourceNotFoundException {
        if(!ObjectUtils.isEmpty(x)&&!ObjectUtils.isEmpty(y)) {
            return plantationRepository.findByChampIdAndXAndY(champId, x, y);
        }

        return plantationRepository.findByChampId(champId);
    }

    // POST : pour créer une nouvelle plantation selon le champId
    @PostMapping(path = "/champ/{champId}/plantation/create")
    public Plantation createPlantation(@PathVariable(value = "champId") Long champId,
                                       @Valid @RequestBody CreatePlantationDTO plantationDTO) {

        Optional<Champ> champOpt = champRepository.findById(champId);
        champOpt.orElseThrow(() -> new ResourceNotFoundException("Champ " + champId + "not found"));

        Plante selectedPlante = null;

        if (plantationDTO.getPlanteId()!=null) {
            Optional<Plante> planteOpt = planteRepository.findById(plantationDTO.getPlanteId());
            planteOpt.orElseThrow(() -> new ResourceNotFoundException("Plante " + plantationDTO.getPlanteId() + "not found"));
            selectedPlante = planteOpt.get();
        }

        // Vérifier qu'une plantation n'existe pas déjà à ces coordonnées.
        if (plantationRepository.checkAlreadyExist(champId, plantationDTO.getX(), plantationDTO.getY())){
            throw new BadRequestException("Une plantation existe déjà aux coordonnées X: " + plantationDTO.getX() + ", Y: "+ plantationDTO.getY());
        }

        Plantation p = new Plantation();

        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());
        p.setPlante(selectedPlante);
        p.setChamp(champOpt.get());

        plantationRepository.save(p);
        return p;
    }

    // UPDATE : selon champId et plantationId
    @PutMapping(path = "/champ/{champId}/plantation/update/{plantationId}")
    public Plantation updatePlantation(@PathVariable(value = "champId") Long champId,
                                       @PathVariable(value = "plantationId") Long plantationId,
                                       @RequestBody CreatePlantationDTO plantationDTO) throws InterruptedException, SerialPortException {

        Optional<Champ> champOpt = champRepository.findById(champId);
        champOpt.orElseThrow(() -> new ResourceNotFoundException("Champ " + champId + "not found"));

        Optional<Plantation> plantationOpt = plantationRepository.findById(plantationId);
        plantationOpt.orElseThrow(() -> new ResourceNotFoundException("Plantation " + plantationId + "not found"));

        Optional<Plante> planteOpt = planteRepository.findById(plantationDTO.getPlanteId());
        planteOpt.orElseThrow(() -> new ResourceNotFoundException("Plante " + plantationDTO.getPlanteId() + "not found"));

        Plantation p = plantationOpt.get();

        if (!p.getChamp().getId().equals(champId)){
            throw new BadRequestException("Le champ de la plantation(" + p.getChamp().getNom() + ") ne correspond pas au champ en paramètres (" +champOpt.get().getNom()+ ")");
        }

        p.setX(plantationDTO.getX());
        p.setY(plantationDTO.getY());
        p.setPlante(planteOpt.get());

        // on déplace le FarmBot à la position de la plantation qui a été plantée
        SerialFarmBot farmbot = null;
        int posZ = -450;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.prendreGraine();
        Thread.sleep(500);
        farmbot.gotoXYZ(PositionsX[p.getX()-1], PositionsY[p.getY()-1], posZ);
        Thread.sleep(500);
        farmbot.envoyerOrdre("F41 P9 V0 M0 Q0");
        farmbot.Deposeoutil(semeur);
        Thread.sleep(500);
        farmbot.razXYZ();

        plantationRepository.save(p);
        return p;
    }

    // DELETE : selon champId et plantationId
    @DeleteMapping(path = "/champ/{champId}/plantation/delete/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable(value = "plantationId") Long plantationId,
                                              @PathVariable(value = "champId") Long champId) {

        Optional<Plantation> plantationOptional = plantationRepository.findById(plantationId);
        plantationOptional.orElseThrow(() -> new ResourceNotFoundException("Plantation" + plantationId + "not found"));

        Plantation p = plantationOptional.get();

        p.setPlante(null);

        plantationRepository.save(p);
        return ResponseEntity.ok().build();
    }
}