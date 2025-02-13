package ru.proj3ct5.timeTracker;

import lombok.SneakyThrows;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.proj3ct5.service.secondary.TimeProcessor;
import ru.proj3ct5.service.timeTracker.WorkTime;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkTimeTest {

    WorkTime workTime;
    private static final Logger log = LoggerFactory.getLogger(WorkTimeTest.class);


    @BeforeAll
    public static void init() {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\" +
                        "src\\main\\resources\\log4j_4test.properties");
    }

    @BeforeEach
    public void init2() {
        workTime = new WorkTime();
    }

    @Test
    @SneakyThrows
    public void testWorkTime1() {
        int sleepTime = 132000;
        log.trace("Тест при времени сна = {}", 2*sleepTime);

        int status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Начальный статус: {}", status);


        workTime.startWorkTime();
        LocalTime startTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Рабочий статус: {}", status);

        Thread.sleep(sleepTime);

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Длительный рабочий статус: {}", status);
        LocalTime time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());

        Thread.sleep(sleepTime);

        workTime.endWorkTime();
        LocalTime endTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Конечный статус: {}", status);
        time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());
    }

    @Test
    @SneakyThrows
    public void testWorkTime2() {
        int sleepTime = 13200;
        log.trace("Тест при времени сна = {}", 2*sleepTime);

        int status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Начальный статус: {}", status);


        workTime.startWorkTime();
        LocalTime startTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Рабочий статус: {}", status);

        Thread.sleep(sleepTime);

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Длительный рабочий статус: {}", status);
        LocalTime time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());

        Thread.sleep(sleepTime);

        workTime.endWorkTime();
        LocalTime endTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Конечный статус: {}", status);
        time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());
    }

    @Test
    @SneakyThrows
    public void testWorkTime3() {
        int sleepTime = 102000;
        log.trace("Тест при времени сна = {}", 2*sleepTime);

        int status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Начальный статус: {}", status);


        workTime.startWorkTime();
        LocalTime startTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Рабочий статус: {}", status);

        Thread.sleep(sleepTime);

        status = workTime.getStatus();
        assertEquals(1, status);
        log.trace("Длительный рабочий статус: {}", status);
        LocalTime time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());

        Thread.sleep(sleepTime);

        workTime.endWorkTime();
        LocalTime endTime = LocalTime.now();

        status = workTime.getStatus();
        assertEquals(0, status);
        log.trace("Конечный статус: {}", status);
        time = TimeProcessor.calculate(startTime, LocalTime.now());
        assertEquals(time, workTime.getWorkTime());
    }


}