package com.rostand.FarmBotWEBv2.Controller;

import com.rostand.FarmBotWEBv2.Repository.MonitorRepository;
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
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

            SerialFarmBot farmbot;
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

        @PostMapping(value = "/monitor/axeX/deplacePositionAxeX")
        public void deplacePositionAxeX () {
            // code Alexis
        }

        // -------------------------------- PARTIE AXE Y ---------------------------------

        @GetMapping(value = "/monitor/axeY/getPositionAxeY")
        public int getPositionAxeY () {
            // code Alexis
            int p = 0;
            return p;
        }

        @PostMapping(value = "/monitor/axeY/deplacePositionAxeY")
        public void deplacePositionAxeY () {
            // code Alexis
        }

        // -------------------------------- PARTIE AXE Z ---------------------------------

        @GetMapping(value = "/monitor/axeZ/getPositionAxeZ")
        public int getPositionAxeZ () {
            // code Alexis
            int p = 0;
            return p;
        }

        @PostMapping(value = "/monitor/axeZ/deplacePositionAxeZ")
        public void deplacePositionAxeZ () {
            // code Alexis
        }

        // --------------------------------- PARTIE ACTIONNEURS ----------------------------------

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

        // ---------------------------------------- OUTILS ---------------------------------------------

}
