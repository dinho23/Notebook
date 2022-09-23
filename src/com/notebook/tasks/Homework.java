package com.notebook.tasks;

public class Homework extends Task{

    private String subject;

    public Homework() {
        super();
    }

    @Override
    public void printTaskInfo(){
        System.out.println("You have to do your " + getSubject() + " " + getTaskName() + " on "
                + dateFormat.format(getDate()) + " which will take " + getDuration()/hour + " hour(s) and "
                + getDuration()%hour + " minute(s)." );
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
