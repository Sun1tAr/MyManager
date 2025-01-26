package ru.proj3ct5.service;

import ru.proj3ct5.tracker.WorkTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

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

    public static LocalTime calculateSomeTimes(ArrayList<WorkTime> times) {
        return null;
    }

    public static LocalTime calculateSomeTimes(ArrayList<WorkTime> times, WorkTime workTime) {
        return null;
    }

    public static LocalTime calculateDayTime(LocalTime workTime, LocalTime dinnerTime) {

        return null;
    }



}
