package com.rostand.FarmBotWEBv2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class MonitorController {

    // -------------------------------- PARTIE AXE X ---------------------------------

    @GetMapping(value = "/monitor/axeX/getPositionAxeX")
    public int getPositionAxeX() {
        // code Alexis
        int p=0;
        return p;
    }

    @PostMapping(value = "/monitor/axeX/deplacePositionAxeX")
    public void deplacePositionAxeX() {
        // code Alexis
    }

    // -------------------------------- PARTIE AXE Y ---------------------------------

    @GetMapping(value = "/monitor/axeY/getPositionAxeY")
    public int getPositionAxeY() {
        // code Alexis
        int p=0;
        return p;
    }

    @PostMapping(value = "/monitor/axeY/deplacePositionAxeY")
    public void deplacePositionAxeY() {
        // code Alexis
    }

    // -------------------------------- PARTIE AXE Z ---------------------------------

    @GetMapping(value = "/monitor/axeZ/getPositionAxeZ")
    public int getPositionAxeZ() {
        // code Alexis
        int p=0;
        return p;
    }

    @PostMapping(value = "/monitor/axeZ/deplacePositionAxeZ")
    public void deplacePositionAxeZ() {
        // code Alexis
    }

    // --------------------------------- PARTIE ACTIONNEURS ----------------------------------

    @PostMapping(value = "/monitor/actionneurs/pompeAEau")
    public void pompeAEau() {
        // code Alexis
    }

    @PostMapping(value = "/monitor/actionneurs/pompeAAir")
    public void pompeAAir() {
        // code Alexis
    }

    @PostMapping(value = "/monitor/actionneurs/lumiere")
    public void lumiere() {
        // code Alexis
    }

    // --------------------------------------- CAMERA ---------------------------------------------

    // ---------------------------------------- OUTILS ---------------------------------------------


}
