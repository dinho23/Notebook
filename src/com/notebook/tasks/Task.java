package com.notebook.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String taskName;
    private int duration;
    private Date dateOfTask;
    protected final int hour = 60;
    SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MMM.yy hh':'mm a");

    public Task (){
        this.taskName = "blank";
        this.duration = 0;
        this.dateOfTask = new Date();
    }
    public Task(String taskName, int duration, Date dateOfTask){
        this.taskName = taskName;
        this.duration = duration;
        this.dateOfTask = dateOfTask;
    }

    public void printTaskInfo(){
        System.out.println("You have " + getTaskName() + " on " + dateFormat.format(getDate())
                + " which will take " + getDuration()/hour + " hour(s) and " + getDuration()%hour + " minute(s)." );
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDateOfTask() { return dateFormat.format(dateOfTask); }

    public Date getDate() { return dateOfTask; }

    public void setDateOfTask(Date dateOfTask) {
        this.dateOfTask = dateOfTask;
    }
}
