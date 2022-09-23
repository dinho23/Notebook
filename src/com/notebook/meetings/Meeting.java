package com.notebook.meetings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {

    private String meetingName;
    private String with;
    private Date dateOfMeeting;
    private int duration;
    public List <Meeting> meetings = new ArrayList<>();
    protected final int hour = 60;
    SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MMM.yy hh':'mm a");

    public Meeting(){
        this.meetingName="blank";
        this.with="blank";
        this.dateOfMeeting=new Date();
        this.duration=0;
    }

    public Meeting(String meetingName,String with, Date dateOfMeeting, int duration){
        this.meetingName = meetingName;
        this.with = with;
        this.dateOfMeeting = dateOfMeeting;
        this.duration = duration;
    }

    public void printMeetingInfo(){
        System.out.println("You have a " + getMeetingName() + " meeting with " + getWith() + " on " + dateFormat.format(getDateOfMeeting())
                + " which will take " + getDuration()/hour + " hour(s) and " + getDuration()%hour + " minute(s)." );
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getWith() {
        return with;
    }

    public void setWith(String with) {
        this.with = with;
    }

    public String getDateOfMeeting() { return dateFormat.format(dateOfMeeting); }

    public Date getDate() { return dateOfMeeting; }

    public void setDateOfMeeting(Date dateOfMeeting) {
        this.dateOfMeeting = dateOfMeeting;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
