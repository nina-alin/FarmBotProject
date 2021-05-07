package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.Repository.MonitorRepository;
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MonitorController {

    @Autowired
    MonitorRepository monitorRepository;

    // -------------------------------- PARTIE AXE X ---------------------------------

    @GetMapping(value = "/monitor/axeX/getPositionAxeX")
    public int getPositionAxeX () {
        SerialFarmBot farmbot=null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        // code Alexis
        int p = 0;
        return p;
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

        posX += 100;
        farmbot.gotoXYZ(posX, posY, posZ);
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

        posX -= 100;
        farmbot.gotoXYZ(posX, posY, posZ);
    }

    // -------------------------------- PARTIE AXE Y ---------------------------------

    @GetMapping(value = "/monitor/axeY/getPositionAxeY")
    public int getPositionAxeY () {
            // code Alexis
            int p = 0;
            return p;
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

        posY += 50;
        farmbot.gotoXYZ(posX, posY, posZ);
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

        posY -= 50;
        farmbot.gotoXYZ(posX, posY, posZ);
    }

        // -------------------------------- PARTIE AXE Z ---------------------------------

    @GetMapping(value = "/monitor/axeZ/getPositionAxeZ")
    public int getPositionAxeZ () {
        // code Alexis
        int p = 0;
        return p;
    }

    @PostMapping(value = "/monitor/axeZ/deplacePositionAxeZ/up")
    public void deplacePositionAxeZUp () {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        posZ += 10;
        farmbot.gotoXYZ(posX, posY, posZ);
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
        farmbot.gotoXYZ(posX, posY, posZ);
    }

    // ------------------------------------- PARTIE DEPLACEMENT CASES ------------------------------

    @PostMapping(value = "/monitor/deplacerCase/11")
    public void deplacerCase11() {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.gotoXYZ(300, 1050, posZ);
    }

    // --------------------------------- PARTIE ACTIONNEURS ----------------------------------

    @Scheduled(fixedRate = 10000)
    @PostMapping(value = "/monitor/actionneurs/pompeAEau")
    public void pompeAEau () {
        // code Alexis
    }

    @PostMapping(value = "/monitor/actionneurs/pompeAAir")
    public void pompeAAir () {
        // code Alexis
    }

    @PostMapping(value = "/monitor/actionneurs/lumiere/on")
    public void lumiereOn () throws SerialPortException {
        SerialFarmBot farmbot = null;
        int posX = 0, posY = 0, posZ = 0;
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
        int posX = 0, posY = 0, posZ = 0;
        String shell = System.getenv("SHELL");

        try {
            farmbot = SerialFarmBot.getInstance("/dev/ttyACM0");
        } catch (Exception er) {
            er.printStackTrace();
        }

        farmbot.envoyerOrdre("F41 P7 V0 M0 Q0");
    }

    // --------------------------------------- CAMERA ---------------------------------------------

    @Scheduled(fixedRate = 100000)
    @PostMapping(value = "/monitor/camera/scanMauvaisesHerbes")
    public void scanMauvaisesHerbes () {
        // code Alexis
    }

    // ---------------------------------------- OUTILS ---------------------------------------------

}
