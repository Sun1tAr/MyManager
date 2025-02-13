package ru.proj3ct5.settings;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class Configurator {

    @Getter
    private int port;
    @Getter
    private String ip;

    private final File configFile = new File("C:\\0_DATA\\CodeProjects\\Java\\MyManager\\src\\main\\resources\\config.properties");

    public Configurator() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(configFile));
        } catch (IOException e) {
            log.error("Failed to load config file: {}", e.getMessage());
        }
        port = Integer.parseInt(prop.getProperty("PORT"));
        ip = prop.getProperty("IP");
        log.info("Configuration was loaded successfully");
    }

}

