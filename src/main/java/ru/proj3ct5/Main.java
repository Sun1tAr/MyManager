package ru.proj3ct5;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.PropertyConfigurator;
import ru.proj3ct5.network.Subscriber;
import ru.proj3ct5.service.GUI;
import ru.proj3ct5.settings.Handler;


@Log4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\src\\main\\resources\\log4j.properties");

        Subscriber subscriber = new Subscriber();

        Handler h = new TimeTrackerHandler(subscriber);
        h.process();

        GUI gui = new GUI();
        gui.startRemoteGUI();




        System.out.println("salkjdevfnbal");
    }




}