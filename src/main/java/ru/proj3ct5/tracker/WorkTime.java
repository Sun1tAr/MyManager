package ru.proj3ct5.tracker;

import lombok.Getter;


import ru.proj3ct5.service.TimeProcessor;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkTime {


    private static final Logger log = LoggerFactory.getLogger(WorkTime.class);

    //    @Getter
//    private int statusWork;
//    @Getter
//    private LocalTime workTime;
//
//    private LocalTime startWorkTime;
//    private LocalTime endWorkTime;
//    private final Scanner sc;
//
//    final int[] STATUS_LIST = new int[]{0, 1};
//
//    public WorkTime() {
//        log.info("WorkTime started");
//        sc = new Scanner(System.in);
//        startUserControl();
//    }
//
//    public WorkTime(int userInput) {
//        log.info("WorkTime started");
//        sc = new Scanner(System.in);
//        switch (userInput) {
//            case 1:
//                startWork();
//                break;
//            case 0:
//                endWork();
//                break;
//            default:
//                System.out.println("Invalid input, please try again");
//                startUserControl();
//                break;
//        }
//    }
//
//
//    public void startUserControl() {
//        log.info("Starting user`s control and GUI");
//        int i = userInput("Выберите действие:\n1 - Начать работу\n0 - Закончить работу");
//        switch (i) {
//            case 1:
//                startWork();
//                break;
//            case 0:
//                endWork();
//                break;
//            default:
//                System.out.println("Invalid input, please try again");
//                startUserControl();
//                break;
//        }
//    }
//
//    TODO
//    public void continueUserControl(int mode) {  //1 - external, 0 - internal
//
//    }
//
//    public void startWork() {
//        log.info("Starting work");
//        if (statusWork == 0) {
//            setStatusWork(1);
//            startWorkTime = LocalTime.now();
//            startUserControl();
//        } else {
//            restartWork();
//        }
//    }
//
//    private void setStatusWork(int status) {
//        this.statusWork = status;
//    }
//
//    public void endWork() {
//        if (statusWork == 1) {
//            endWorkTime = LocalTime.now();
//            setStatusWork(0);
//        } else {
//            System.out.println("Invalid input, please try again");
//            startUserControl();
//        }
//
//        log.debug("Start time of work: {}", startWorkTime);
//        log.debug("End time of work: {}", endWorkTime);
//        sc.close();
//
//    }
//
//    public void restartWork() {
//        log.info("Restarting work?");
//        log.debug("Старое время начала: {}", startWorkTime);
//        LocalTime time = calculate(startWorkTime, LocalTime.now());
//        log.debug("Отработано время: {}", time);
//        String msg = "Изменить начальное время работы на текущее?\n" +
//                "Отработанное время: " + time + "\n1 - Да\n0 - Нет";
//        int i = userInput(msg);
//        switch (i) {
//            case 1:
//                log.info("Restarting work!");
//                startWorkTime = LocalTime.now();
//                break;
//            case 0:
//                break;
//            default:
//                System.out.println("Invalid input, please try again");
//                break;
//        }
//        startUserControl();
//    }
//
//    public int userInput(String textToUser) {
//        System.out.println(textToUser);
//        int userInput;
//        try {
//            userInput = sc.nextInt();
//        } catch (Exception e) {
//            log.error("User input is incorrect");
//            System.out.println("Invalid input, please try again");
//            return userInput(textToUser);
//        }
//        return userInput;
//    }

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
        time = LocalTime.of(0, 0);
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


