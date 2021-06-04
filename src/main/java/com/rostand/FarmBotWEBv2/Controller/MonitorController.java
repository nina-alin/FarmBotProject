package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.DTO.CreatePlantationDTO;
import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Entity.Plantation;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import com.rostand.FarmBotWEBv2.Exception.BadRequestException;
import com.rostand.FarmBotWEBv2.Exception.ResourceNotFoundException;
import com.rostand.FarmBotWEBv2.Repository.ChampRepository;
import com.rostand.FarmBotWEBv2.Repository.MonitorRepository;
import com.rostand.FarmBotWEBv2.Repository.PlantationRepository;
import com.rostand.FarmBotWEBv2.Repository.PlanteRepository;
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.rostand.FarmBotWEBv2.constants.Constants.*;
import static java.lang.Thread.sleep;

@CrossOrigin(origins = "*")
@RestController
public class MonitorController {

    @Autowired
    ChampRepository champRepository;

    @Autowired
    PlantationRepository plantationRepository;


    // -------------------------------- PARTIE AXE X ---------------------------------

    @GetMapping(value = "/monitor/axeX/getPositionAxeX")
    public double getPositionAxeX () {
        SerialFarmBot farmbot=null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        return farmbot.GetX();
    }

    @PostMapping(value = "/monitor/axeX/deplacePositionAxeX/up")
    public void deplacePositionAxeXUp () {
        SerialFarmBot farmbot=null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(farmbot.GetX() + 100, farmbot.GetY()+0, farmbot.GetZ()+0);
    }

    @PostMapping(value = "/monitor/axeX/deplacePositionAxeX/down")
    public void deplacePositionAxeXDown () {
        SerialFarmBot farmbot=null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(farmbot.GetX()-100, farmbot.GetY()+0, farmbot.GetZ()+0);
    }

    // -------------------------------- PARTIE AXE Y ---------------------------------

    @GetMapping(value = "/monitor/axeY/getPositionAxeY")
    public double getPositionAxeY () {
        SerialFarmBot farmbot=null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        return farmbot.GetY();
    }

    @PostMapping(value = "/monitor/axeY/deplacePositionAxeY/up")
    public void deplacePositionAxeYUp () {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(farmbot.GetX()+0, farmbot.GetY()+50, farmbot.GetZ()+0);
    }

    @PostMapping(value = "/monitor/axeY/deplacePositionAxeY/down")
    public void deplacePositionAxeYDown () {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(farmbot.GetX()+0, farmbot.GetY()-50, farmbot.GetZ());
    }

    // -------------------------------- PARTIE AXE Z ---------------------------------

    @GetMapping(value = "/monitor/axeZ/getPositionAxeZ")
    public double getPositionAxeZ () {
        SerialFarmBot farmbot=null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        return farmbot.GetZ();
    }

    @PostMapping(value = "/monitor/axeZ/deplacePositionAxeZ/up")
    public void deplacePositionAxeZUp () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(farmbot.GetX()+0, farmbot.GetY()+0, farmbot.GetZ()+10);
    }

    @PostMapping(value = "/monitor/axeZ/deplacePositionAxeZ/down")
    public void deplacePositionAxeZDown () {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        posZ -= 10;
        farmbot.gotoXYZ(farmbot.GetX()+0, farmbot.GetY()+0, farmbot.GetZ()-10);
    }

    // -------------------------------- RETOUR A ZERO ---------------------------------

    @PostMapping(value = "/monitor/RAZ")
    public void RAZ() throws SerialPortException {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.razXYZ();
    }

    // --------------------------------- PARTIE ACTIONNEURS ----------------------------------

    @PostMapping(value = "/monitor/actionneurs/desherbeur/prendre")
    public void desherbeurOn () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Prendreoutil(desherbeur);
    }


    @PostMapping(value = "/monitor/actionneurs/desherbeur/deposer")
    public void desherbeurOff () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Deposeoutil(desherbeur);
    }


    @PostMapping(value = "/monitor/actionneurs/arroseur/prendre")
    public void arroseurOn () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Prendreoutil(arroseur);
    }

    @PostMapping(value = "/monitor/actionneurs/arroseur/deposer")
    public void arroseurOff () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Deposeoutil(arroseur);
    }

    @PostMapping(value = "/monitor/actionneurs/semeur/prendre")
    public void semeurOn () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Prendreoutil(semeur);
    }


    @PostMapping(value = "/monitor/actionneurs/semeur/deposer")
    public void semeurOff () {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Deposeoutil(semeur);
    }

    @PostMapping(value = "/monitor/actionneurs/lumiere/on")
    public void lumiereOn () throws SerialPortException {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P7 V1 M0 Q0");
    }

    @PostMapping(value = "/monitor/actionneurs/lumiere/off")
    public void lumiereOff () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P7 V0 M0 Q0");
    }

    @PostMapping(value = "/monitor/actionneurs/arrosage/on")
    public void arrosageOn () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }
        farmbot.envoyerOrdre("F41 P8 V1 M0 Q0");
    }

    @PostMapping(value = "/monitor/actionneurs/arrosage/off")
    public void arrosageOff() throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P8 V0 M0 Q0");
    }

    @PostMapping(value = "/monitor/actionneurs/air/off")
    public void airOff() throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P9 V0 M0 Q0");
    }

    @PostMapping(value = "/monitor/actionneurs/air/on")
    public void airOn() throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P9 V1 M0 Q0");
    }

    // --------------------------------------- CAMERA ---------------------------------------------

    @PostMapping(value = "/monitor/camera/on")
    public void cameraOn () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    @PostMapping(value = "/monitor/camera/off")
    public void cameraOff () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    @PostMapping(value = "/monitor/scanMauvaisesHerbes")
    public void scanMauvaisesHerbes () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    @PostMapping(value = "/monitor/prendreGraine")
    public void prendreGraines () throws SerialPortException {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.prendreGraine();
    }

    @PostMapping(value = "/monitor/arroser")
    public void arroser () throws SerialPortException, InterruptedException {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.arrosage();
    }

    @PostMapping(value = "/monitor/arroser/champ/{champId}/plantation/{plantationId}")
    public void arroserCase (@PathVariable(value = "champId") Long champId,
                             @PathVariable(value = "plantationId") Long plantationId) throws SerialPortException, InterruptedException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        Optional<Champ> champOpt = champRepository.findById(champId);
        champOpt.orElseThrow(() -> new ResourceNotFoundException("Champ " + champId + "not found"));

        Optional<Plantation> plantationOptional = plantationRepository.findById(plantationId);
        plantationOptional.orElseThrow(() -> new ResourceNotFoundException("Plantation" + plantationId + "not found"));

        Plantation p = plantationOptional.get();

        if (!p.getChamp().getId().equals(champId)){
            throw new BadRequestException("Le champ de la plantation(" + p.getChamp().getNom() + ") ne correspond pas au champ en paramètres (" +champOpt.get().getNom()+ ")");
        }

        farmbot.Prendreoutil(arroseur);
        farmbot.gotoXYZ(PositionsX[p.getX()]-1, PositionsX[p.getY()]-1, -350);
        farmbot.envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        farmbot.envoyerOrdre("F41 P8 V0 M0 Q0");
        farmbot.Deposeoutil(arroseur);
        farmbot.razXYZ();
    }

    @PostMapping(value = "/monitor/scanner")
    public void scanner () throws SerialPortException {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.scanMauvaisesHerbes();
    }
}
