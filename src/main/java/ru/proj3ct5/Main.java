package ru.proj3ct5;

import ru.proj3ct5.tracker.WorkTime;

import java.time.LocalTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {


        WorkTime wt = new WorkTime();
        System.out.println(wt.getWorkTime());
    }
}