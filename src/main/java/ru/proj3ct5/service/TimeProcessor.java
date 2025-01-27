package ru.proj3ct5.service;

import ru.proj3ct5.tracker.WorkTime;

import java.time.LocalTime;
import java.util.ArrayList;

public class TimeProcessor {

    public static LocalTime calculate(LocalTime startWorkTime, LocalTime endWorkTime) throws IllegalArgumentException {

        if (startWorkTime == null || endWorkTime == null) {
            throw new IllegalArgumentException("startWorkTime and endWorkTime cannot be null");
        }

        int workHours = endWorkTime.getHour() - startWorkTime.getHour();
        int workMinutes = endWorkTime.getMinute() - startWorkTime.getMinute();

        if (workMinutes < 0) {
            workMinutes += 60;
            if (workHours > 0) {
                workHours -= 1;
            }
        }
        return LocalTime.of(workHours, workMinutes, 0);
    }

    static LocalTime rebuildMinutes(int hours, int minutes) {
        if (minutes >= 60) {
            int addH = minutes / 60;
            hours += addH;
            minutes = minutes - addH * 60;
        }
        return LocalTime.of(hours, minutes);
    }


    public static LocalTime calculateSomeTimes(ArrayList<WorkTime> times) {
        int hours = 0;
        int minutes = 0;
        for (WorkTime time : times) {
            hours += time.getWorkTime().getHour();
            minutes += time.getWorkTime().getMinute();
        }
        return rebuildMinutes(hours, minutes);
    }


    public static LocalTime calculateSomeTimes(ArrayList<WorkTime> times, WorkTime workTime) {
        LocalTime time = calculateSomeTimes(times);
        int hours = workTime.getWorkTime().getHour() + time.getHour();
        int minutes = workTime.getWorkTime().getMinute() + time.getMinute();
        return rebuildMinutes(hours, minutes);
    }


    public static LocalTime calculateDayTime(LocalTime workTime, LocalTime dinnerTime) {
        return calculate(dinnerTime, workTime);
    }



}
