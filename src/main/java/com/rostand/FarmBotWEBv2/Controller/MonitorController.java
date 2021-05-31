package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.Repository.MonitorRepository;
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rostand.FarmBotWEBv2.constants.Constants.*;

@CrossOrigin(origins = "*")
@RestController
public class MonitorController {

    @Autowired
    MonitorRepository monitorRepository;

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


    @PostMapping(value = "/monitor/actionneurs/scanner/prendre")
    public void scannerOn () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Prendreoutil(scanner);
    }

    @PostMapping(value = "/monitor/actionneurs/scanner/deposer")
    public void scannerOff () {
        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.Deposeoutil(scanner);
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

    @PostMapping(value = "/monitor/actionneurs/arrosage")
    public void arrosage () throws SerialPortException {

        SerialFarmBot farmbot = null;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }
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
}
