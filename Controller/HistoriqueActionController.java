package com.rostand.farmbotapi.Controller;

import com.rostand.farmbotapi.DTO.CreateHistoriqueActionDTO;
import com.rostand.farmbotapi.Entity.HistoriqueAction;
import com.rostand.farmbotapi.Exception.ResourceNotFoundException;
import com.rostand.farmbotapi.Repository.HistoriqueActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class HistoriqueActionController {

    // repository
    @Autowired
    HistoriqueActionRepository historiqueActionRepository;

    // ------------------------- PARTIE GET / CREATE ACTION ----------------------------

    // GET : pour avoir la liste des actions ou une action par id
    @GetMapping(path = "/action/list")
    public Object getHistoriqueAction(@RequestParam(required = false) Long historiqueActionId)
            throws ResourceNotFoundException {

        if(!ObjectUtils.isEmpty(historiqueActionId)) {
            return historiqueActionRepository.findHistoriqueActionById(historiqueActionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Action non trouvée pour l'id " + historiqueActionId));
        }

        return historiqueActionRepository.findAll();
    }

    @PostMapping(path = "/action/create")
    @ResponseBody
    public void createHistoriqueAction(@RequestBody CreateHistoriqueActionDTO historiqueActionDTO) {
        HistoriqueAction h = new HistoriqueAction();

        h.setTypeAction(historiqueActionDTO.getTypeAction());

        historiqueActionRepository.save(h);
    }

}
