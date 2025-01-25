package ru.proj3ct5.service;

import lombok.Getter;
import lombok.Setter;

public class Configurator {
    Object dataBase;
    private int activate;

    @Setter
    @Getter
    private String linkToDataBase;

    public int activate() {
        activate = 1;
        return activate;
    }

}

