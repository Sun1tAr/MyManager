package ru.proj3ct5;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.PropertyConfigurator;
import ru.proj3ct5.network.Message;
import ru.proj3ct5.network.Subscriber;
import ru.proj3ct5.service.Configurator;
import ru.proj3ct5.service.GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Log4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PropertyConfigurator.configure(
                "C:\\0_DATA\\CodeProjects\\Java\\WorkingTimeTracker\\src\\main\\resources\\log4j.properties");

        Handler h = new Handler();
        h.process();

        GUI gui = new GUI();
        gui.startRemoteGUI();

//        Thread.sleep(15000);



        System.out.println("salkjdevfnbal");
    }




}