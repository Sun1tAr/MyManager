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
        PropertyConfigurator.configure("C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\src\\main\\resources\\log4j.properties");
    }

    @Test
    void testOfTimeCalculatingOne(){
        LocalTime endWorkTime = LocalTime.of(16, 0);
        LocalTime expextedTime = LocalTime.of(0, 5);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",1 ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testOfTimeCalculatingTwo(){
        LocalTime endWorkTime = LocalTime.of(16, 57);
        LocalTime expextedTime = LocalTime.of(1, 2);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",2 ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testOfTimeCalculatingThree(){
        LocalTime endWorkTime = LocalTime.of(16, 53);
        LocalTime expextedTime = LocalTime.of(0, 58);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",3 ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testOfTimeCalculatingFour(){
        LocalTime endWorkTime = LocalTime.of(19, 55);
        LocalTime expextedTime = LocalTime.of(4, 0);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",4 ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testOfTimeCalculatingFive(){
        LocalTime endWorkTime = LocalTime.of(19, 50);
        LocalTime expextedTime = LocalTime.of(3, 55);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",5 ,time);
        assertEquals(expextedTime, time);
    }
    @Test
    void testOfTimeCalculatingSix(){
        LocalTime endWorkTime = LocalTime.of(19, 58);
        LocalTime expextedTime = LocalTime.of(4, 3);
        LocalTime time = TimeProcessor.calculate(startWorkTime, endWorkTime);
        log.info("Время, полученное в тесте № {} = {}",6 ,time);
        assertEquals(expextedTime, time);
    }

}