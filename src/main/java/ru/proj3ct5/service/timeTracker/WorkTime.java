package ru.proj3ct5.service.timeTracker;

import lombok.Getter;


import ru.proj3ct5.service.secondary.TimeProcessor;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkTime {


    private static final Logger log = LoggerFactory.getLogger(WorkTime.class);

    @Getter
    private LocalTime workTime;
    @Getter
    private int status;

    private LocalTime startWorkTime;
    private LocalTime endWorkTime;

    public WorkTime() {
        this.workTime = LocalTime.of(0, 0);
        this.startWorkTime = LocalTime.of(0, 0);
        this.endWorkTime = LocalTime.of(0, 0);
        this.status = 0;
    }

    public void startWorkTime() {
        log.info("Starting work time");
        startWorkTime = LocalTime.now();
        status = 1;
    }

    public void endWorkTime() {
        log.info("Ending work time");
        endWorkTime = LocalTime.now();
        status = 0;
        workTime = getWorkTime();
    }

    public LocalTime getWorkTime() {
        log.info("Getting work time");
        LocalTime time;
        switch (status) {
            case 1:
                time = TimeProcessor.calculate(startWorkTime, LocalTime.now());
                log.debug("Returning work time ({}) at now, status = {}", time, status);
                break;
            case 0:
                time = TimeProcessor.calculate(startWorkTime, endWorkTime);
                log.debug("Returning work time ({}) because work is ended, status = {}", time, status);
                break;
            default:
                throw new IllegalStateException("Unexpected status: " + status);
        }
        return time;
    }

}


