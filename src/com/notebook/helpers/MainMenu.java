package com.notebook.helpers;

import java.util.Scanner;

public class MainMenu {
    public static void mainMenu() {
        ManipulateMeetings meetingManipulator = new ManipulateMeetings();
        ManipulateTasks taskManipulator = new ManipulateTasks();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("Hello! Select one of following options:");

        System.out.println("1 - Meetings\n" +
                "2 - Tasks\n" +
                "3 - Exit\n");

        while (true) {
            String option = scanner.next();
            switch (option) {
                case "1":
                    meetingManipulator.showAddMeeting();
                    break;
                case "2":
                    taskManipulator.showAddTask();
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nWrong choice! Choose one of the available options.");
            }
        }
    }
}