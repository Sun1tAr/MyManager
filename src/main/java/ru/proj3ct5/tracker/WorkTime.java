package ru.proj3ct5.tracker;

import lombok.Getter;


import org.apache.log4j.PropertyConfigurator;
import ru.proj3ct5.service.TimeProcessor;

import java.time.LocalTime;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkTime {


    private static final Logger log = LoggerFactory.getLogger(WorkTime.class);

    @Getter
    private int statusWork;
    @Getter
    private LocalTime workTime;

    private LocalTime startWorkTime;
    private LocalTime endWorkTime;
    private final Scanner sc;

    final int[] STATUS_LIST = new int[]{0, 1};

    public WorkTime() {
        PropertyConfigurator.configure("C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\src\\main\\resources\\log4j.properties");
        log.info("WorkTime started");
        sc = new Scanner(System.in);
        startUserControl();
    }

    public void startUserControl() {
        log.info("Starting user`s control and GUI");
        int i = userInput("Выберите действие:\n1 - Начать работу\n0 - Закончить работу");
        switch (i) {
            case 1:
                startWork();
                break;
            case 0:
                endWork();
                break;
            default:
                System.out.println("Invalid input, please try again");
                startUserControl();
                break;
        }
    }

    public void startWork() {
        log.info("Starting work");
        if (statusWork == 0) {
            setStatusWork(1);
            startWorkTime = LocalTime.now();
            startUserControl();
        } else {
            restartWork();
        }
    }

    private void setStatusWork(int status) {
        this.statusWork = status;
    }

    public void endWork() {
        if (statusWork == 1) {
            endWorkTime = LocalTime.now();
            setStatusWork(0);
        }
        workTime = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.debug("Start time of work: {}", startWorkTime);
        log.debug("End time of work: {}", endWorkTime);


        sc.close();
    }

    public void restartWork() {
        log.info("Restarting work?");
        log.debug("Старое время начала: {}", startWorkTime);
        LocalTime time = TimeProcessor.calculate(startWorkTime, LocalTime.now());
        log.debug("Отработано время: {}", time);
        String msg = "Изменить начальное время работы на текущее?\n" +
                "Отработанное время: " + time + "\n1 - Да\n0 - Нет";
        int i = userInput(msg);
        switch (i) {
            case 1:
                log.info("Restarting work!");
                startWorkTime = LocalTime.now();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid input, please try again");
                break;
        }
        startUserControl();
    }

    public int userInput(String textToUser) {
        System.out.println(textToUser);
        int userInput;
        try {
            userInput = sc.nextInt();
        } catch (Exception e) {
            log.error("User input is incorrect");
            System.out.println("Invalid input, please try again");
            return userInput(textToUser);
        }
        return userInput;
    }


}
