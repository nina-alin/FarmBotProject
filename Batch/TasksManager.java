package com.rostand.farmbotapi.batch;

import com.rostand.farmbotapi.Entity.HistoriqueAction;
import com.rostand.farmbotapi.Repository.HistoriqueActionRepository;
import com.rostand.farmbotapi.Repository.ReglagesRepository;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TasksManager {

    @Autowired
    ReglagesRepository reglagesRepository;

    @Autowired
    HistoriqueActionRepository historiqueActionRepository;

   // private final SerialFarmBot serial = SerialFarmBot.getInstance("/dev/ttyACM0");

    // ??
    public TasksManager() throws SerialPortException, InterruptedException {
    }

    @Scheduled(fixedRate = 1000)
    public void checkWhatINeedToDo() throws SerialPortException {
      //  Reglages reglages = reglagesRepository.getOne(1L);


       /* Timestamp lastAction = historiqueActionRepository.getDateActionArrosage(HistoriqueAction.eTypeAction.ARROSAGE);

        LocalDateTime dtLastAction = lastAction.toLocalDateTime();
        LocalDateTime dtNow = LocalDateTime.now();

        long minutes =  dtLastAction.until(dtNow, ChronoUnit.MINUTES);*/
/*
        if (minutes > reglages.getFrequence_arrosage())
        {
            // code Alexis arrosage
        }

        if (minutes > reglages.getFrequence_scan())
        {
            // code Alexis scan mauvaises herbes
        }*/
    }

    // meme chose pour le scan des mauvaises herbes

   /* @Scheduled(cron = "/5 * * * *")
    public void arrosage() throws SerialPortException {
        System.out.println("Arrosage");
        serial.envoyerOrdre("T02");
    } */
}
