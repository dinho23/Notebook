package com.notebook.tasks;

public class Project extends Task{

    private String projectName;

    public Project() {
        super();
    }

    @Override
    public void printTaskInfo(){
        System.out.println("You have to do your " + getProjectName() + " project for " + getTaskName() + " on "
                + dateFormat.format(getDate()) + " which will take " + getDuration()/hour + " hour(s) and "
                + getDuration()%hour + " minute(s)." );
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
