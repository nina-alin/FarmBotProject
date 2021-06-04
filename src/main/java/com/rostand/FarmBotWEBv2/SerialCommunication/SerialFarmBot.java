package com.rostand.FarmBotWEBv2.SerialCommunication;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

import static com.rostand.FarmBotWEBv2.constants.Constants.*;
import static java.lang.Thread.sleep;

// la classe principale qui permet de faire la communication en série
public class SerialFarmBot {

    private SerialPort serialPort;

    private List<String> currentBuffer = new ArrayList<>();

    // Singleton instance
    private static SerialFarmBot _instance = null;
    private boolean isStarted = false;
    private String statusCommand = "R00";
    private double X = 0;
    private double Y = 0;
    private double Z = 0;
    private double outilPosY = 300;    //test avec double remplace int 4 lignes
    private double outilEcartY = 100;
    private double outilPosZBas = -427;
    private double outilPosZHaut = -380;

    public double GetX() {
        return X;
    }
    public double GetY() {
        return Y;
    }
    public double GetZ() {
        return Z;
    }


    public class FarmbotReaderThread implements Runnable {

        @Override
        public void run() {
            while (serialPort != null && serialPort.isOpened()) {
                try {
                    String line = readStringFarmBot();
                    if (line == null) {
                        continue;
                    }
                    if (line.contains("R99")) {
                        isStarted = true;
                    }
                    if (line.contains("R00")) {
                        statusCommand = "R00";
                        System.out.println(" IDLE " +line);
                    }
                    if (line.contains("R01")) {
                        statusCommand = "R01";
                    }
                    if (line.contains("R02")) {
                        statusCommand = "R02";
                        try {
                            sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (line.contains("R03")) {
                        statusCommand = "R03";
                        try {
                            sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (line.contains("R04")) {
                        statusCommand = "R04";
                    }
                    if (line.contains("R82")) {
                        String[] tab = line.split(" "); //Split "R82 X-4.2 Y32.3 Z40.0
                        /*if (tab.length == 4)
                        {*/
                            String sX = tab[1].substring(1); //"X-4.2" -> "-4.2"
                            // sX=sX.replace(".",",");
                            X = Double.valueOf(sX);
                            String sY = tab[2].substring(1); //"X-4.2" -> "-4.2"
                            // sY=sY.replace(".",",");
                            Y = Double.valueOf(sY);
                            String sZ = tab[3].substring(1); //"X-4.2" -> "-4.2"
                            // sZ=sZ.replace(".",",");
                            Z = Double.valueOf(sZ);
                        //}
                    }
//                    System.out.print("Retrieved X:"+X+", Y:"+Y+", Z:"+Z);
//                    System.out.println((line));
                    //  currentBuffer.add(line);
                } catch (SerialPortException e) {
                    System.out.println("Impossible de lire les données du Farmbot");
                }
            }
            System.out.println("Fin du thread de lecture");
        }

    }

    public String readStringFarmBot() throws SerialPortException {
        String reponse = "";
        String s = "";
        while (s.compareTo("\n") != 0) {
            s = serialPort.readString(1);
            if (s != null) {
                reponse = reponse + s;
            }

        }
        return reponse;
    }

    public void envoyerOrdre(String ordre) throws SerialPortException {
        envoyerOrdre(ordre, 120000);
    }

    public void envoyerOrdre(String ordre, int timeOut) throws SerialPortException {
        String rep = "";

        statusCommand = "R00";
        System.out.print("debut envoi +");
        serialPort.writeString(ordre + "\r\n");

        long start = System.currentTimeMillis();

        long time = 0;

        while ((statusCommand != "R02") && (statusCommand != "R03") && (time < timeOut))// attendre fin de commande avec succès
        {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = System.currentTimeMillis() - start;

        }
        System.out.print("fin envoi -");
    }

    private SerialFarmBot(String port) throws SerialPortException, InterruptedException {
        serialPort = new SerialPort(port);
        serialPort.openPort();//Open serial port
        serialPort.setParams(115200, 8, 1, 0);//Set params.*/
        serialPort.writeString("\r\n");

        if (!serialPort.isOpened()) {
            System.out.println("Impossible d'ouvrir le port " + port);
            return;
        }

        // Je lance le thread de lecture
        Thread t = new Thread(new FarmbotReaderThread());
        t.start();
        // Il faut attendre le démarrage du farmbot (soit on attends 5s soit on attends la reception de la trame R99
        String firstMessage;
        do {
            System.out.println("********************isstarted*****************");
            sleep(500);
        } while (!isStarted);

        System.out.println("**********************Startup completed********************");

        envoyerOrdre("F22 P2 V1 Q0");
        envoyerOrdre("F22 P101 V1");
        envoyerOrdre("F22 P102 V1");
        envoyerOrdre("F22 P103 V1");
        envoyerOrdre("F22 P125 V1");
        envoyerOrdre("F22 P126 V1");
        envoyerOrdre("F22 P127 V1");
        envoyerOrdre("F22 P71 V250");
        envoyerOrdre("F22 P72 V300");
        envoyerOrdre("F22 P73 V300");
        envoyerOrdre("F22 P65 V200");
        envoyerOrdre("F22 P66 V200");
        envoyerOrdre("F22 P67 V200");
        System.out.println("**********************Init completed********************");


    }

    public static SerialFarmBot getInstance(String port) throws SerialPortException, InterruptedException {
        if (_instance == null) {

            _instance = new SerialFarmBot(port);

        }
        return _instance;
    }


    /*public void gotoXYZ(int x, int y, int z) {
        System.out.println("GOTO x:" + x + ",y:" + y + ",z:" + z);
        String sx = String.valueOf(x);
        String sy = String.valueOf(y);
        String sz = String.valueOf(z);
        try {
            envoyerOrdre("G0 X" + sx + " Y" + sy + " Z" + sz);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }*/

    public void gotoXYZ(double x, double y, double z) {
        System.out.println("GOTO x:" + x + ",y:" + y + ",z:" + z);
        String sx = String.valueOf(x);
        String sy = String.valueOf(y);
        String sz = String.valueOf(z);
        try {
            envoyerOrdre("G0 X" + sx + " Y" + sy + " Z" + sz);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    //modif a partir d'ici
    public void razX() throws InterruptedException {
        double previousX;
        int maxAttente = 60; // 60 secondes max d'attente
        try {
            envoyerOrdre("F11");  //RAZ X
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        do {
            previousX = X;
            sleep(1000);
            System.out.println("Position X précédente : " + previousX);
            System.out.println("Position X actuelle : " + X);
            maxAttente--;

        } while (X != previousX && maxAttente > 0);
        try {
            sleep((long) (X * 100)); // attente après un deplacement
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void razY() throws InterruptedException {
        double previousY;
        int maxAttente = 60; // 60 secondes max d'attente
        try {
            envoyerOrdre("F12");  //RAZ Y
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

        do {
            previousY = Y;
            sleep(1000);
            System.out.println("Position Y précédente : " + previousY);
            System.out.println("Position Y actuelle : " + Y);
            maxAttente--;

        } while (Y != previousY && maxAttente > 0);
        /*
        try {
            Thread.sleep((long) (Y * 100)); // attente après un deplacement
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void razZ() throws InterruptedException {
        double previousZ;
        int maxAttente = 90; // 90 secondes max d'attente
        try {
            envoyerOrdre("F13");  //RAZ Z
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        do {
            previousZ = Z;
            sleep(1000);
            System.out.println("Position Z précédente : " + previousZ);
            System.out.println("Position Z actuelle : " + Z);
            maxAttente--;

        } while (Z != previousZ && maxAttente > 0);
        /*
        // On fait une dernière attente pour s'assurer de l'arrêt du moteur
        Thread.sleep(2000);*/
        System.out.println("Position Z finale " + Z);
            /*
            try {
                Thread.sleep(Z*200); // attente après un deplacement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
    }

    public void razXYZ() {
        try {
            razZ();
            razY();
            razX();
            razZ();
            razY();
            razX();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void Prendreoutil(int numOutil)
    {

        double posY = outilPosY + outilEcartY * (numOutil -1);  //test double remplace int

        gotoXYZ(100, posY, outilPosZBas+50);
        gotoXYZ(18, posY, outilPosZBas);
        gotoXYZ(100, posY, outilPosZBas);
        gotoXYZ(100, posY, outilPosZHaut);



    /*public void Prendreoutil1(int numOutil)
        {

            int posY = outilPosY + outilEcartY * (numOutil -1);

            gotoXYZ(18, posY, outilPosZBas);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gotoXYZ(100, posY, outilPosZBas);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gotoXYZ(100, posY, outilPosZHaut);*/

    }

    public void Deposeoutil(int numOutil)
    {
        double posY = outilPosY + outilEcartY * (numOutil -1);   //test double remplace int
        gotoXYZ(100, posY, outilPosZHaut);
        gotoXYZ(100, posY, outilPosZBas+numOutil);
        gotoXYZ(18, posY, outilPosZBas+numOutil);
        gotoXYZ(18, posY, outilPosZHaut);
    }

    public void prendreGraine() throws SerialPortException {
        double posY = outilPosY + 100 ;

        gotoXYZ(18, posY, outilPosZBas);
        gotoXYZ(100, posY, outilPosZBas);
        gotoXYZ(100, posY, outilPosZHaut);
        gotoXYZ(100, 700, -380);
        gotoXYZ(100, 700, -320);
        gotoXYZ(0, 700, -320);
        gotoXYZ(0, 700, -380);
        envoyerOrdre("F41 P9 V1 M0 Q0");
        gotoXYZ(0, 700, -320);
        gotoXYZ(100, 700, -320);
    }

    // code Nina
    public void arrosage() throws SerialPortException, InterruptedException {

        double posZ=-350;
        gotoXYZ(PositionsX[0], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[1], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[2], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[3], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[4], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[5], PositionsY[0], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[5], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[4], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[3], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[2], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[1], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[0], PositionsY[1], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[0], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[1], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[2], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[3], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[4], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        gotoXYZ(PositionsX[5], PositionsY[2], posZ);
        envoyerOrdre("F41 P8 V1 M0 Q0");
        sleep(1000);
        envoyerOrdre("F41 P8 V0 M0 Q0");
        razXYZ();
    }

    public void scanMauvaisesHerbes() throws SerialPortException {
        double posZ=-350;
        // allumer la caméra
        gotoXYZ(PositionsX[0], PositionsY[0], posZ);
        gotoXYZ(PositionsX[1], PositionsY[0], posZ);
        gotoXYZ(PositionsX[2], PositionsY[0], posZ);
        gotoXYZ(PositionsX[3], PositionsY[0], posZ);
        gotoXYZ(PositionsX[4], PositionsY[0], posZ);
        gotoXYZ(PositionsX[5], PositionsY[0], posZ);
        gotoXYZ(PositionsX[5], PositionsY[1], posZ);
        gotoXYZ(PositionsX[4], PositionsY[1], posZ);
        gotoXYZ(PositionsX[3], PositionsY[1], posZ);
        gotoXYZ(PositionsX[2], PositionsY[1], posZ);
        gotoXYZ(PositionsX[1], PositionsY[1], posZ);
        gotoXYZ(PositionsX[0], PositionsY[1], posZ);
        gotoXYZ(PositionsX[0], PositionsY[2], posZ);
        gotoXYZ(PositionsX[1], PositionsY[2], posZ);
        gotoXYZ(PositionsX[2], PositionsY[2], posZ);
        gotoXYZ(PositionsX[3], PositionsY[2], posZ);
        gotoXYZ(PositionsX[4], PositionsY[2], posZ);
        gotoXYZ(PositionsX[5], PositionsY[2], posZ);
        // éteindre la caméra
        razXYZ();
    }
}
