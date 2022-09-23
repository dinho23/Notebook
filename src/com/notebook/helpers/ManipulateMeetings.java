package com.notebook.helpers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.notebook.meetings.FriendlyMeeting;
import com.notebook.meetings.Meeting;
import com.notebook.meetings.BusinessMeeting;
import com.notebook.meetings.RomanticMeeting;

public class ManipulateMeetings{

    private String filePath = "./repository/ReportDataMeetings.csv";
    Scanner scanner = new Scanner(System.in);
    public List<Meeting> meetingLists =new LinkedList<>();
    private boolean trigger = true;
    File file = new File(filePath);

    public void showAddMeeting() {
        if (file.exists())
            while (trigger){
                readCsvMeeting();
                trigger = false;
            }
        System.out.println("Choose one:\n" +
                        "1. Add meeting.\n" +
                        "2. Generate meetings report.\n" +
                        "3. Generate meetings report by ascending date.\n" +
                        "4. Generate meetings report by descending date.\n" +
                        "5. Show meetings report in console.\n" +
                        "6. Return to main menu.\n" +
                        "7. Exit.\n");
        String option = scanner.next();
        while (true){
            switch (option){
                case "1":
                    addMeeting();
                    break;
                case "2":
                    buildReport();
                    showAddMeeting();
                    break;
                case "3":
                    Collections.sort(meetingLists, Comparator.comparing(Meeting::getDate));
                    buildReport();
                    showAddMeeting();
                case "4":
                    Collections.sort(meetingLists, Comparator.comparing(Meeting::getDate).reversed());
                    buildReport();
                    showAddMeeting();
                case "5":
                    showMeetings();
                    showAddMeeting();
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

    public void addMeeting(){
        List<String> menu = Arrays.asList("1. Friendly", "2. Business", "3. Romantic", "4. Back", "5. Exit");
        System.out.println("Choose one:\n" + menu);
        String option = scanner.next();
        switch (option){
            case "1":
                FriendlyMeeting friendlyMeeting = new FriendlyMeeting();
                makeMeeting("friendly");
                showAddMeeting();
                break;
            case "2":
                byte importance = 0;
                BusinessMeeting meetingBusiness = new BusinessMeeting();
                makeMeeting("business");
                System.out.println("Rate the importance of the meeting from 1 to 10.\n");
                importance = scanner.nextByte();
                meetingBusiness.setImportance(String.valueOf(importance));
                showAddMeeting();
                break;
            case "3":
                RomanticMeeting meetingRomantic = new RomanticMeeting();
                makeMeeting("romantic");
                showAddMeeting();
                break;
            case "4":
                showAddMeeting();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public void makeMeeting(String option){
        Meeting meeting = new Meeting();
        meeting.setMeetingName(option);
        System.out.println("Introduce the name of the person you are meeting:\n");
        scanner.nextLine();
        String input = scanner.nextLine();
        meeting.setWith(input);
        System.out.println("Introduce the date and hour of the meeting (format: dd.MM.yyyy hh.mm):\n");
        input = scanner.nextLine();
        Date date = SetDate.parseDate(input);
        meeting.setDateOfMeeting(date);
        System.out.println("Introduce the duration of the meeting (minutes):\n");
        int duration = scanner.nextInt();
        meeting.setDuration(duration);
        meeting.meetings.add(meeting);
        meetingLists.add(meeting);
    }

    public void initReportHeader() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            bufferedWriter.append("TYPE_OF_MEETING");
            bufferedWriter.append(",");
            bufferedWriter.append("WITH");
            bufferedWriter.append(",");
            bufferedWriter.append("DATE");
            bufferedWriter.append(",");
            bufferedWriter.append("DURATION");
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeDataToReport(Meeting meeting){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath,true))){
            bufferedWriter.append(meeting.getMeetingName());
            bufferedWriter.append(",");
            bufferedWriter.append(meeting.getWith());
            bufferedWriter.append(",");
            bufferedWriter.append(meeting.getDateOfMeeting().toString());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(meeting.getDuration()));
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readDataFromCsv() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String record;
            while((record = bufferedReader.readLine()) != null) {
                String [] recordData = record.split(",");
                System.out.println(recordData[0] + " " + recordData[1] + " " + recordData[2] + " " + recordData[3]);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showMeetings() {
        readDataFromCsv();
    }

    public void buildReport(){
        initReportHeader();
        meetingLists.forEach(meeting -> writeDataToReport(meeting));
        System.out.println("Report was generated successfully!");
    }

    public void readCsvMeeting(){ meetingLists = readMeetingsFromCSV(filePath); }

    private static List<Meeting> readMeetingsFromCSV(String fileName) {
        List<Meeting> meetings =new LinkedList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Meeting meeting = createMeeting(attributes);
                meetings.add(meeting);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return meetings;
    }

    private static Meeting createMeeting(String[] metadata) {
        String meetingName = metadata[0];
        String with = metadata[1];
        Date date = SetDate.parseDateCsv(metadata[2]);
        int duration = Integer.parseInt(metadata[3]);
        Date dateOfMeeting = date;
        return new Meeting(meetingName,with,dateOfMeeting,duration);
    }
}
