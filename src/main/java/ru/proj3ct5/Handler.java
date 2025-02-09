package ru.proj3ct5;

import lombok.extern.slf4j.Slf4j;
import ru.proj3ct5.network.Message;
import ru.proj3ct5.network.Subscriber;
import ru.proj3ct5.tracker.WorkDay;

import java.time.LocalTime;
import java.util.LinkedList;

@Slf4j
public class Handler {

    private WorkDay workDay;
    private boolean running = true;
    private Subscriber subscriber;
    private Thread t;

    public void process() {

        t = new Thread(() -> {
            log.info("Handler was start processing");
            LinkedList<String> messagesList;
            int oldMsgCount = 0;

            subscriber = new Subscriber();
            subscriber.start();

            while (running) {
                messagesList = subscriber.getMessages();
                log.debug("Received list of messages: {}", messagesList);
                if (messagesList.size() > oldMsgCount) {
                    String message = messagesList.getLast();
                    oldMsgCount = messagesList.size();
                    log.debug("Messages list updates to {} size", oldMsgCount);
                    log.debug("Last message received: {}", message);
                    Message deserialized = Message.deserialize(message);

                    String command = deserialized.getData();
                    log.debug("Deserialized command: {}", command);
                    executeCommand(command);
                } else {
                    log.debug("No new messages received");
                }
                if (!running) {
                    throw new RuntimeException();
//                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("Thread unsleeped: {}", e.getMessage());
                }
            }
        });
        t.start();

    }

    public void close() {
        subscriber.stop();
        running = false;
        if (workDay != null && (workDay.getStatus() == 1 || workDay.getStatus() == 2)) {
            workDay.endWorkDay();
        }
        saveToDB();
        log.info("Handler's thread was stopped");
    }

    private boolean executeCommand(String command) {

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
        return running;
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
