package ru.proj3ct5.service;

import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeProcessorTest {

    private static final Logger log = LoggerFactory.getLogger(TimeProcessorTest.class);
    LocalTime startWorkTime = LocalTime.of(15, 55);

    @BeforeAll
    public static void init() {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\" +
                "src\\main\\resources\\log4j_4test.properties");
    }

    @Test
    void testCalculate1() {
        String number = "1-1";
        LocalTime endWorkTime = LocalTime.of(16, 0);
        LocalTime expextedTime = LocalTime.of(0, 5);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.trace("Время, полученное в тесте № {} = {}",number ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testCalculate2(){
        String number = "1-2";
        LocalTime endWorkTime = LocalTime.of(16, 57);
        LocalTime expextedTime = LocalTime.of(1, 2);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.trace("Время, полученное в тесте № {} = {}",number ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testCalculate3(){
        String number = "1-3";
        LocalTime endWorkTime = LocalTime.of(16, 53);
        LocalTime expextedTime = LocalTime.of(0, 58);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.trace("Время, полученное в тесте № {} = {}",number,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testRebuildMinutes1(){
        String number = "2-1";
        LocalTime expextedTime = LocalTime.of(12, 54);
        LocalTime time = TimeProcessor.rebuildMinutes(12,54);
        log.trace("Время, полученное в тесте № {} = {}",number,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testRebuildMinutes2(){
        String number = "2-2";
        LocalTime expextedTime = LocalTime.of(13, 8);
        LocalTime time = TimeProcessor.rebuildMinutes(12,68);
        log.trace("Время, полученное в тесте № {} = {}",number,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testRebuildMinutes3(){
        String number = "2-3";
        LocalTime expextedTime = LocalTime.of(14, 10);
        LocalTime time = TimeProcessor.rebuildMinutes(12,130);
        log.trace("Время, полученное в тесте № {} = {}",number,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testRebuildMinutes4(){
        String number = "2-4";
        LocalTime expextedTime = LocalTime.of(12, 0);
        LocalTime time = TimeProcessor.rebuildMinutes(12,0);
        log.trace("Время, полученное в тесте № {} = {}",number,time);
        assertEquals(expextedTime, time);
    }


}