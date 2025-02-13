package ru.proj3ct5.timeTracker;

import lombok.SneakyThrows;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.proj3ct5.service.secondary.TimeProcessor;
import ru.proj3ct5.service.timeTracker.WorkDay;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkDayTest {

    private static final Logger log = LoggerFactory.getLogger(WorkDayTest.class);

    WorkDay workDay;
    @BeforeAll
    public static void init() {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\" +
                        "src\\main\\resources\\log4j_4test.properties");
    }

    @BeforeEach
    public void init2() {
        workDay = new WorkDay();
    }

    @SneakyThrows
    @Test
    public void workDayTestNormalDay() {
        int workTime = 360000;
        int dinnerTime = 180000;
//        workTime = 0;
//        dinnerTime = 0;
        log.trace("Тест при времени работы = {} и времени обеда = {}",
                2*workTime/60000, 2*dinnerTime/60000);

        LocalTime time0 = LocalTime.now();

        int status = workDay.getStatus();
        assertEquals(0, status);
        log.trace("Начальный статус: {}", status);

        workDay.startWorkDay();
        status = workDay.getStatus();
        assertEquals(1, status);
        log.trace("Статус при начале работы: {}", status);

        Thread.sleep(workTime);

        status = workDay.getStatus();
        assertEquals(1, status);
        log.trace("Статус при работе: {}", status);
        LocalTime timeOfWork = LocalTime.now();
        LocalTime dayWorkTime = workDay.getTodayWorkingTime();
        log.trace("Время работы при работе 1: {}", dayWorkTime);
        assertEquals(TimeProcessor.calculate(time0, timeOfWork),dayWorkTime);

        workDay.startDinner();
        status = workDay.getStatus();
        assertEquals(2, status);
        log.trace("Статус при начале обеда: {}", status);
        LocalTime dinnerStartTime = LocalTime.now();

        Thread.sleep(dinnerTime);

        status = workDay.getStatus();
        assertEquals(2, status);
        log.trace("Статус при обеде: {}", status);
        LocalTime dayDinnerTime = LocalTime.now();
        dayWorkTime = workDay.getTodayWorkingTime();
        log.trace("Время работы при обеде 1: {}", dayWorkTime);
        assertEquals(TimeProcessor.calculateDayTime(
                        TimeProcessor.calculate(time0, dayDinnerTime),
                        TimeProcessor.calculate(dinnerStartTime, dayDinnerTime))
                        ,dayWorkTime);

        Thread.sleep(dinnerTime);
        workDay.endDinner();

        status = workDay.getStatus();
        assertEquals(1, status);
        log.trace("Статус при конце обеда: {}", status);
        dayDinnerTime = LocalTime.now();
        dayWorkTime = workDay.getTodayWorkingTime();
        log.trace("Время работы при обеде 2: {}", dayWorkTime);
        assertEquals(TimeProcessor.calculateDayTime(
                        TimeProcessor.calculate(time0, dayDinnerTime),
                        TimeProcessor.calculate(dinnerStartTime, dayDinnerTime))
                ,dayWorkTime);

        Thread.sleep(workTime);
        workDay.endWorkDay();

        status = workDay.getStatus();
        assertEquals(3, status);
        log.trace("Статус при конце работы: {}", status);
        timeOfWork = LocalTime.now();
        dayWorkTime = workDay.getTodayWorkingTime();
        log.trace("Время работы при работе 2: {}", dayWorkTime);
        assertEquals(TimeProcessor.calculate(time0, timeOfWork),dayWorkTime);

    }

}