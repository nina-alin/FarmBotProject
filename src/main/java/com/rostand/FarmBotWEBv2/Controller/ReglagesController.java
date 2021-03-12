package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreateReglagesDTO;
import com.rostand.FarmBotWEBv2.Entity.Reglages;
import com.rostand.FarmBotWEBv2.Repository.ReglagesRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ReglagesController {

    // repository
    @Autowired
    ReglagesRepository reglagesRepo;

    // ----------------------- PARTIE GET / CREATE / UPDATE / DELETE REGLAGES --------------------------

    @GetMapping(path = "/reglages/list")
    public List getReglages() {
        return reglagesRepo.findAll();
    }

    @PostMapping(path = "/reglages/create")
    @ResponseBody
    public void createReglages(@RequestBody CreateReglagesDTO reglagesDTO) {
        Reglages r = new Reglages();

        r.setFrequence_scan(reglagesDTO.getFrequence_scan());
        r.setG1(reglagesDTO.getG1());
        r.setG2(reglagesDTO.getG2());
        r.setG3(reglagesDTO.getG3());
        r.setPostRefOutilX(reglagesDTO.getPostRefOutilX());
        r.setPostRefOutilY(reglagesDTO.getPostRefOutilY());
        r.setPostRefOutilZ(reglagesDTO.getPostRefOutilZ());

        reglagesRepo.save(r);
    }

    @PutMapping(path = "/reglages/update/{id}")
    public ResponseEntity<Reglages> updateReglages(@PathVariable Long id,
                                                   @RequestBody CreateReglagesDTO reglagesDTO) throws NotFoundException {
        Optional<Reglages> optionalReglages = reglagesRepo.findById(id);
        Reglages r = optionalReglages.get();

        r.setFrequence_scan(reglagesDTO.getFrequence_scan());
        r.setG1(reglagesDTO.getG1());
        r.setG2(reglagesDTO.getG2());
        r.setG3(reglagesDTO.getG3());
        r.setPostRefOutilX(reglagesDTO.getPostRefOutilX());
        r.setPostRefOutilY(reglagesDTO.getPostRefOutilY());
        r.setPostRefOutilZ(r.getPostRefOutilZ());

        final Reglages updateReglages = reglagesRepo.save(r);
        return ResponseEntity.ok(updateReglages);
    }

    @PostMapping(path = "/reglages/delete/{id}")
    @ResponseBody
    public void deleteReglages(@PathVariable Long id) {
        reglagesRepo.deleteById(id);
    }
}
