package com.rostand.FarmBotWEBv2.batch;

import com.rostand.FarmBotWEBv2.DTO.CreateReglagesDTO;
import com.rostand.FarmBotWEBv2.Entity.HistoriqueAction;
import com.rostand.FarmBotWEBv2.Entity.Reglages;
import com.rostand.FarmBotWEBv2.Repository.HistoriqueActionRepository;
import com.rostand.FarmBotWEBv2.Repository.ReglagesRepository;
import com.rostand.FarmBotWEBv2.SerialCommunication.SerialFarmBot;
import jssc.SerialPortException;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@ConditionalOnExpression("${enableSchedule:true}")
public class TasksManager {

    @Autowired
    ReglagesRepository reglagesRepository;

    @Autowired
    HistoriqueActionRepository historiqueActionRepository;

   private final SerialFarmBot serial = SerialFarmBot.getInstance("/dev/ttyACM0");

    // ??
    public TasksManager() throws SerialPortException, InterruptedException {
    }

    @Scheduled(fixedRate = 1000)
    public void checkWhatINeedToDo() throws SerialPortException, InterruptedException {
        SerialFarmBot farmbot = null;
        Reglages reglages = reglagesRepository.getOne(65L);


        Timestamp lastAction = historiqueActionRepository.getDateActionArrosage(HistoriqueAction.eTypeAction.ARROSAGE);

        long minutes = -1L;
        if (lastAction != null){
            LocalDateTime dtLastAction = lastAction.toLocalDateTime();
            LocalDateTime dtNow = LocalDateTime.now();
            minutes =  dtLastAction.until(dtNow, ChronoUnit.MINUTES);
        }

        if (minutes > reglages.getFrequence_arrosage() || minutes == -1L)
        {
            farmbot.arrosage();
        }

        if (minutes > reglages.getFrequence_scan() || minutes == -1L)
        {
            farmbot.scanMauvaisesHerbes();
        }
    }

    // meme chose pour le scan des mauvaises herbes

    @Scheduled(cron = "*/5 * * * * *")
    public void arrosage() throws SerialPortException {
        System.out.println("Arrosage");
        serial.envoyerOrdre("T02");
    }
}
