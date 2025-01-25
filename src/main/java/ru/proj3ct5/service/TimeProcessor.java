package ru.proj3ct5.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class TimeProcessor {

    public static LocalTime calculate(LocalTime startWorkTime, LocalTime endWorkTime) {

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

    public static LocalTime calculateMoreTimes(ArrayList<LocalTime> times) {
        return calculate(times.get(0), times.get(1));
    }

}
