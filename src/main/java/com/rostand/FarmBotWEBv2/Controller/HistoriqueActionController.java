package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreateHistoriqueActionDTO;
import com.rostand.FarmBotWEBv2.DTO.CreatePlanteDTO;
import com.rostand.FarmBotWEBv2.Entity.HistoriqueAction;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.HistoriqueActionRepository;
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
    @GetMapping(path = "/historiqueAction/list")
    public Object getHistoriqueAction(@RequestParam(required = false) Long historiqueActionId)
            throws ResourceNotFoundException {

        if(!ObjectUtils.isEmpty(historiqueActionId)) {
            return historiqueActionRepository.findHistoriqueActionById(historiqueActionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Action non trouvée pour l'id " + historiqueActionId));
        }

        return historiqueActionRepository.findAll();
    }

    @PostMapping(path = "/historiqueAction/create")
    @ResponseBody
    public void createHistoriqueAction(@RequestBody CreateHistoriqueActionDTO historiqueActionDTO) {
        HistoriqueAction h = new HistoriqueAction();

        h.setTypeAction(historiqueActionDTO.getTypeAction());

        historiqueActionRepository.save(h);
    }

}
