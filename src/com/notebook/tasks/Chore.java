package com.notebook.tasks;

public class Chore extends Task{

    private String choreName;

    public Chore() {
        super();
    }

    @Override
    public void printTaskInfo(){
        System.out.println("You have " + getChoreName() + " on " + dateFormat.format(getDate())
                + " which will take " + getDuration()/hour + " hour(s) and " + getDuration()%hour
                + " minute(s)." );
    }

    public String getChoreName() {
        return choreName;
    }

    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }
}
