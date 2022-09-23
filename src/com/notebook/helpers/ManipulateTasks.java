package com.notebook.helpers;

import com.notebook.tasks.Chore;
import com.notebook.tasks.Homework;
import com.notebook.tasks.Project;
import com.notebook.tasks.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ManipulateTasks {

    private String filePath = "./repository/ReportDataTasks.csv";
    File file = new File(filePath);
    Scanner scanner = new Scanner(System.in);
    String name;
    private boolean trigger = true;
    private List<Task> taskLists = new ArrayList();

    public void showAddTask() {
        if (file.exists())
            while (trigger){
                readCsvTask();
                trigger = false;
            }
        System.out.println("Choose one:\n" +
                "1. Add task.\n" +
                "2. Generate tasks report.\n" +
                "3. Generate tasks report by ascending date.\n" +
                "4. Generate tasks report by descending date.\n" +
                "5. Show tasks report in console.\n" +
                "6. Return to main menu.\n" +
                "7. Exit.\n");
        String option = scanner.next();
        while (true) {
            switch (option) {
                case "1":
                    addTask();
                    break;
                case "2":
                    buildReport();
                    showAddTask();
                    break;
                case "3":
                    Collections.sort(taskLists, Comparator.comparing(Task::getDate));
                    buildReport();
                    showAddTask();
                case "4":
                    Collections.sort(taskLists, Comparator.comparing(Task::getDate).reversed());
                    buildReport();
                    showAddTask();
                case "5":
                    showTasks();
                    showAddTask();
                    break;
                case "6":
                    MainMenu.mainMenu();
                    break;
                case "7":
                    System.exit(0);
                default:
                    System.out.println("Wrong choice! Choose one of the options.");
                    break;
            }
        }
    }

    public void addTask() {
        List<String> menu = Arrays.asList("1. Chore", "2. Homework", "3. Project", "4. Back", "5. Exit");
        System.out.println("Introduce the type of task:\n" + menu);
        String option = scanner.next().toLowerCase();
        switch (option) {
            case "1":
                Chore chore = new Chore();
                makeTask(option);
                scanner.nextLine();
                System.out.println("What chore do you have to do:");
                name = scanner.nextLine();
                chore.setChoreName(name);
                showAddTask();
                break;
            case "2":
                Homework homework = new Homework();
                makeTask(option);
                scanner.nextLine();
                System.out.println("What is the subject of the homework:");
                name = scanner.nextLine();
                homework.setSubject(name);
                showAddTask();
                break;
            case "3":
                Project project = new Project();
                makeTask(option);
                scanner.nextLine();
                System.out.println("What project do you have to make:");
                name = scanner.nextLine();
                project.setProjectName(name);
                showAddTask();
                break;
            case "4":
                showAddTask();
                break;
            default:
                System.exit(0);
        }
    }

    public void makeTask(String option) {
        Task task = new Task();
        if (option.matches("1")) option = "chore";
        if (option.matches("2")) option = "homework";
        if (option.matches("3")) option = "project";
        System.out.println("Introduce the name of the " + option + ":\n");
        scanner.nextLine();
        String input = scanner.nextLine();
        task.setTaskName(input);
        System.out.println("When to schedule the task? (dd.MM.yyyy hh.mm):\n");
        input = scanner.nextLine();
        Date date = SetDate.parseDate(input);
        task.setDateOfTask(date);
        System.out.println("How much time will it take?\n");
        int duration = scanner.nextInt();
        task.setDuration(duration);
        this.taskLists.add(task);
    }

    public void initReportHeader() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.append("TYPE_OF_TASK");
            bufferedWriter.append(",");
            bufferedWriter.append("DATE");
            bufferedWriter.append(",");
            bufferedWriter.append("DURATION");
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeDataToReport(Task task) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(task.getTaskName());
            bufferedWriter.append(",");
            bufferedWriter.append(task.getDateOfTask());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(task.getDuration()));
            bufferedWriter.append("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readDataFromCsv() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                String[] recordData = record.split(",");
                System.out.println(recordData[0] + " " + recordData[1] + " " + recordData[2]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showTasks() {
        readDataFromCsv();
    }

    public void buildReport() {
        initReportHeader();
        taskLists.forEach(task -> writeDataToReport(task));
        System.out.println("Report was generated successfully!");
    }

    public void readCsvTask(){ taskLists = readTasksFromCSV(filePath); }

    private static List<Task> readTasksFromCSV(String fileName) {
        List<Task> tasks = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Task task = createTask(attributes);
                tasks.add(task);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return tasks;
    }

    private static Task createTask(String[] metadata) {
        String taskName = metadata[0];
        Date date = SetDate.parseDateCsv(metadata[1]);
        int duration = Integer.parseInt(metadata[2]);
        Date dateOfTask = date;
        return new Task(taskName,duration,dateOfTask);
    }
}
