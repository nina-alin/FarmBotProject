package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreateReglagesDTO;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Entity.Reglages;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import com.rostand.FarmBotWEBv2.Repository.ReglagesRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ReglagesController {

    // repositories
    @Autowired
    ReglagesRepository reglagesRepository;

    @Autowired
    PlanteRepository planteRepository;

    // ----------------------- PARTIE GET / CREATE / UPDATE / DELETE REGLAGES --------------------------

    @GetMapping(path = "/reglages/list")
    public Object getReglages(@RequestParam(required = false) Long reglageId)
            throws ResourceNotFoundException {

        if(!ObjectUtils.isEmpty(reglageId)) {
            return reglagesRepository.findReglagesById(reglageId)
                    .orElseThrow(() -> new ResourceNotFoundException("Reglage non trouvé pour l'id " + reglageId));
        }

        return reglagesRepository.findAll();
    }

    @PutMapping(path = "/reglages/update/{reglagesId}")
    public ResponseEntity<Reglages> updateReglages(@PathVariable Long reglagesId,
                                                   @RequestBody CreateReglagesDTO reglagesDTO)
            throws NotFoundException {

        Optional<Plante> planteOpt1 = planteRepository.findById(reglagesDTO.getG1());
        planteOpt1.orElseThrow(() -> new ResourceNotFoundException("Plante " + reglagesDTO.getG1() + "not found"));

        Optional<Plante> planteOpt2 = planteRepository.findById(reglagesDTO.getG2());
        planteOpt2.orElseThrow(() -> new ResourceNotFoundException("Plante " + reglagesDTO.getG2() + "not found"));

        Optional<Plante> planteOpt3 = planteRepository.findById(reglagesDTO.getG3());
        planteOpt3.orElseThrow(() -> new ResourceNotFoundException("Plante " + reglagesDTO.getG3() + "not found"));

        Optional<Reglages> optionalReglages = reglagesRepository.findById(reglagesId);
        optionalReglages.orElseThrow(() -> new ResourceNotFoundException("Plantation " + reglagesId + "not found"));

        Reglages r = optionalReglages.get();

        r.setFrequence_arrosage(reglagesDTO.getFrequence_arrosage());
        r.setFrequence_scan(reglagesDTO.getFrequence_scan());
        r.setG1(planteOpt1.get());
        r.setG2(planteOpt2.get());
        r.setG3(planteOpt3.get());

        final Reglages updateReglages = reglagesRepository.save(r);
        return ResponseEntity.ok(updateReglages);
    }
}
