package ru.proj3ct5.service;

import ru.proj3ct5.tracker.WorkDay;

import java.util.Scanner;

public class GUI {

    Scanner scanner;

    public GUI() {
        this.scanner = new Scanner(System.in);
    }

    public void startSimpleGUI() {
        WorkDay wd = new WorkDay();
        int status = wd.getStatus();
        String msg = "0 - Выход\n1 - Начать РД\n2 - Закончить РД\n3 - Начать обед\n4 - закончить обед\n5 - Время";
        printStatus(status);
        int userInput = this.getUserInput(msg);

        while (userInput != 0) {
            switch (userInput) {
                case 1: wd.startWorkDay();
                    break;
                case 2: wd.endWorkDay();
                    break;
                case 3: wd.startDinner();
                    break;
                case 4: wd.endDinner();
                    break;
                case 5:
                    System.out.println("-----------------------------");
                    System.out.println("Сегодня отработанно: " + wd.getTodayWorkingTime());
                    break;
                default:
                    System.err.println("Invalid input, try again");
                    break;
            }
            status = wd.getStatus();
            printStatus(status);
            userInput = this.getUserInput(msg);

        }
    }

    private int getUserInput(String msg) {
        System.out.println(msg);
        System.out.println("-----------------------------");
        int output = 0;
        try {
            output = scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
        return output;
    }

    private void printStatus(int status) {
        String output = "Ваш статус: ";
        switch (status) {
            case 0: output += "Работа не начата"; break;
            case 1: output += "Работа начата"; break;
            case 2: output += "Обед"; break;
            case 3: output += "Работа завершена"; break;
            default:
                System.err.println("Invalid input, try again");
                return;
        }
        System.out.println("-----------------------------");
        System.out.println(output);
        System.out.println("-----------------------------");

    }

}
