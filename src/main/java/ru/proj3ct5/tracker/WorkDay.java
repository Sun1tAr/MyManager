package ru.proj3ct5.tracker;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.proj3ct5.service.TimeProcessor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkDay {
    private static final Logger log = LoggerFactory.getLogger(WorkDay.class);


    private LocalTime todayWorkingTime;
    private LocalTime todayWorkingTimeLocal;
    private LocalTime todayDinnerTimeLocal;

    private final ArrayList<WorkTime> worksList;
    private WorkTime currentWork;

    private final ArrayList<WorkTime> dinnersList;
    private WorkTime currentDinner;


    @Getter
    private LocalDate today;
    @Getter
    private int status;  //0 - не начат РД, 1 - начать РД, 2 - обед, 3 - завершен РД

    public WorkDay() {
        todayWorkingTime = LocalTime.of(0, 0);
        worksList = new ArrayList<>();
        dinnersList = new ArrayList<>();
        today = LocalDate.now();
        status = 0;
    }

    public void startWorkDay() {
        if ((status == 0 || status == 3) && createNewWorkingTime(currentWork, worksList, 1)) {
            currentWork.startWorkTime();
            status = 1;
            log.info("Work day started!");
        } else {
            System.err.println("Рабочий период уже начат!");
        }
    }

    public void endWorkDay() {
        if (status == 1) {
            if (currentWork.getStatus() == 1) {
                currentWork.endWorkTime();
            }
            try {
                addCurrentWorkToList(currentWork, worksList);
            } catch (Exception e) {
                log.error("Current work time wasn`t add to list");
                return;
            }
            todayWorkingTimeLocal = calculateTime(worksList);
            status = 3;
            log.info("Work day ended!");
            calculateTime();
        } else if (status == 2) {
            endDinner();
            endWorkDay();
        } else {
            System.err.println("Рабочий период уже завершен!");
        }
        currentWork = null;
    }

    public void startDinner() {
        if ((status == 1) && createNewWorkingTime(currentDinner, dinnersList, 0)) {
            currentDinner.startWorkTime();
            status = 2;
            log.info("Dinner started!");
        } else {
            System.err.println("Для начала обеда - начните рабочий день!");
            log.warn("For starting dinner need a start work day! Status = {}", status);
        }
    }

    public void endDinner() {

        if (status == 2 && currentDinner.getStatus() == 1) {
            currentDinner.endWorkTime();
            try {
                addCurrentWorkToList(currentDinner, dinnersList);
            } catch (Exception e) {
                log.error("Current dinner wasn`t add to list");
                return;
            }
            todayDinnerTimeLocal = calculateTime(dinnersList);
            status = 1;
            log.info("Dinner ended!");
            currentDinner = null;
        } else {
            System.err.println("Обед уже завершен!");
        }
    }

    public LocalTime getTodayWorkingTime() {
        calculateTime();
        return todayWorkingTime;
    }

    private void calculateTime() {
        if (status == 1) {
            todayWorkingTimeLocal = TimeProcessor.calculateSomeTimes(worksList, currentWork);
            todayDinnerTimeLocal = TimeProcessor.calculateSomeTimes(dinnersList);
        } else if (status == 2) {
            if (currentWork.getStatus() == 1) {
                todayWorkingTimeLocal = TimeProcessor.calculateSomeTimes(worksList, currentWork);
                todayDinnerTimeLocal = TimeProcessor.calculateSomeTimes(dinnersList, currentDinner);
            } else {
                todayWorkingTimeLocal = TimeProcessor.calculateSomeTimes(worksList);
                todayDinnerTimeLocal = TimeProcessor.calculateSomeTimes(dinnersList, currentDinner);
            }
        } else {
            todayWorkingTimeLocal = TimeProcessor.calculateSomeTimes(worksList);
            todayDinnerTimeLocal = TimeProcessor.calculateSomeTimes(dinnersList);
        }

        todayWorkingTime = TimeProcessor.calculateDayTime(todayWorkingTimeLocal, todayDinnerTimeLocal);
        log.debug("todayWorkingTimeLocal calculated = {}, with day status = {}", todayWorkingTimeLocal, status);
        log.debug("todayDinnerTimeLocal calculated = {}, with day status = {}", todayDinnerTimeLocal, status);
        log.debug("todayWorkingTime calculated = {}, with day status = {}", todayWorkingTime, status);
        log.info("Time was calculated = {}, with day status = {}", todayWorkingTime, status);
    }

    private boolean createNewWorkingTime(WorkTime currentWork, List<WorkTime> worksList, int mode) {
        if (currentWork != null) {
            try {
                addCurrentWorkToList(currentWork, worksList);
            } catch (Exception e) {
                log.error("New working time wasn`t created - old work hasn`t add to list = {}", currentWork.getStatus());
                return false;
            }
        }
        if (mode == 1) {
            this.currentWork = new WorkTime();
        } else {
            this.currentDinner = new WorkTime();
        }
        log.info("New working time created");
        return true;
    }

    private void addCurrentWorkToList(WorkTime currentWork, List<WorkTime> worksList) throws Exception {
        if (currentWork.getStatus() == 0) {
            worksList.add(currentWork);
            log.info("Old working time added to list");
        } else {
            log.error("New working time wasn`t add to list - old work is ACTIVE and has status = {}", currentWork.getStatus());
            throw new Exception("Work has status - ACTIVE!");
        }
    }

    private LocalTime calculateTime(ArrayList<WorkTime> worksList) {
        return TimeProcessor.calculateSomeTimes(worksList);
    }


}
