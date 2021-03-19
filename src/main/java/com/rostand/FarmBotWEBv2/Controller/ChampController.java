package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreateChampDTO;
import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ChampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ChampController {

    // repository
    @Autowired
    ChampRepository champRepository;

    // ------------------------- PARTIE GET / CREATE / UPDATE / DELETE CHAMP ----------------------------

    // GET : pour avoir la liste des champs ou un champ par id
    @GetMapping(path = "/champ/list")
    public Object getChamps(@RequestParam(required = false) Long champId)
            throws ResourceNotFoundException {

        if(!ObjectUtils.isEmpty(champId)) {
            return champRepository.findChampById(champId)
                    .orElseThrow(() -> new ResourceNotFoundException("Champ non trouvé pour l'id " + champId));
        }

        return champRepository.findAll();
    }

    // POST : pour créer un nouveau champ
    @PostMapping(path = "/champ/create")
    @ResponseBody
    public void createChamp(@RequestBody CreateChampDTO champDTO) {
        Champ c = new Champ();
        c.setNom(champDTO.getNom());

        champRepository.save(c);
    }

    // UPDATE
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

    // DELETE
    @PostMapping(path = "/champ/delete/{id}")
    @ResponseBody
    public void deleteChamp(@PathVariable Long id) {
        champRepository.deleteById(id);
    }
}
