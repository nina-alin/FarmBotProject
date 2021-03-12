package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreateChampDTO;
import com.rostand.FarmBotWEBv2.DTO.CreatePlantationDTO;
import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ChampRepository;
import com.rostand.FarmBotWEBv2.Repository.PlantationRepository;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import javassist.NotFoundException;
import org.hibernate.cfg.PkDrivenByDefaultMapsIdSecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ChampController {

    // repositories
    @Autowired
    ChampRepository champRepository;

    @Autowired
    PlanteRepository planteRepository;

    @Autowired
    PlantationRepository plantationRepository;

    // ------------------------- PARTIE GET / CREATE / UPDATE / DELETE CHAMP ----------------------------

    @GetMapping(path = "/champ/list")
    public List getChamps() {
        return champRepository.findAll();
    }

    @PostMapping(path = "/champ/create")
    @ResponseBody
    public void createChamp(@RequestBody CreateChampDTO champDTO) {
        Champ c = new Champ();
        c.setNom(champDTO.getNom());

        champRepository.save(c);
    }

    @PutMapping(path = "/champ/update/{id}")
    public ResponseEntity<Champ> updateChamp(@PathVariable Long id,
                                             @RequestBody CreateChampDTO champDTO) {
        if(!champRepository.existsById(id)) {
            throw new ResourceNotFoundException("ChampId " + id + " not found");
        }

        Optional<Champ> optionalChamp = champRepository.findById(id);
        Champ c = optionalChamp.get();

        c.setNom(champDTO.getNom());

        final Champ updateChamp = champRepository.save(c);
        return ResponseEntity.ok(c);
    }

    @PostMapping(path = "/champ/delete/{id}")
    @ResponseBody
    public void deleteChamp(@PathVariable Long id) {
        champRepository.deleteById(id);
    }
}
