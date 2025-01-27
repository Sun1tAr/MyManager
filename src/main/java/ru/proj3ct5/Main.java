package ru.proj3ct5;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import ru.proj3ct5.service.GUI;
import ru.proj3ct5.service.TimeProcessor;
import ru.proj3ct5.tracker.WorkDay;
import ru.proj3ct5.tracker.WorkTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

@Log4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\src\\main\\resources\\log4j.properties");

        WorkDay wd = new WorkDay();
        GUI gui = new GUI();

        gui.startSimpleGUI();

    }
}