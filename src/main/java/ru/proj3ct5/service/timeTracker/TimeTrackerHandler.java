package ru.proj3ct5.service.timeTracker;

import lombok.extern.slf4j.Slf4j;
import ru.proj3ct5.network.Subscriber;
import ru.proj3ct5.settings.Handler;

import java.time.LocalTime;

@Slf4j
public class TimeTrackerHandler extends Handler {

    private WorkDay workDay;

    public TimeTrackerHandler(Subscriber subscriber) {
        super(subscriber);
    }

    public void close() {
        if (workDay != null && (workDay.getStatus() == 1 || workDay.getStatus() == 2)) {
            workDay.endWorkDay();
        }
        saveToDB();
        super.close();
    }

    protected void executeCommand() {

        if (workDay == null && !command.equals("newDay")) {
            workDay = new WorkDay();
        }

        switch (command) {
            case "STOP":
                close();
                log.info("Service TimeTracker stopped");
                break;
            case "newDay":
                if (workDay != null) {
                    workDay.endWorkDay();
                    saveToDB();
                }
                workDay = new WorkDay();
                break;
            case "startWork":
                workDay.startWorkDay();
                break;
            case "startDinner":
                workDay.startDinner();
                break;
            case "endWork":
                workDay.endWorkDay();
                break;
            case "endDinner":
                workDay.endDinner();
                break;
            case "saveDay":
                saveToDB();
                break;
            case "getTime":
                sendTime();
                break;
            case "getStatus":
                sendStatus();
                break;
            default:
                log.error("Unknown command: {}", command);
        }
        log.debug("Command executed: {}", command);
    }

    private void saveToDB() {
        //TODO
    }

    private void sendTime() {
        //TODO
        LocalTime todayWorkingTime = workDay.getTodayWorkingTime();
        System.out.println(todayWorkingTime);

    }

    private void sendStatus() {
        //TODO
        String statusString = WorkDay.deserializeStatus(workDay.getStatus());
        System.out.println(statusString);
    }

}
