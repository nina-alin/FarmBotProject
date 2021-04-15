package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ActionController {

    // repository
    @Autowired
    ActionRepository actionRepository;

    // ------------------------- PARTIE GET ACTION ----------------------------

    // GET : pour avoir la liste des actions ou une action par id
    @GetMapping(path = "/action/list")
    public Object getAction(@RequestParam(required = false) Long actionId)
            throws ResourceNotFoundException {

        if(!ObjectUtils.isEmpty(actionId)) {
            return actionRepository.findActionById(actionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Action non trouvée pour l'id " + actionId));
        }

        return actionRepository.findAll();
    }
}
