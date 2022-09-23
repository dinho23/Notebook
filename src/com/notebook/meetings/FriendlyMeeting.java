package com.notebook.meetings;


public class FriendlyMeeting extends Meeting{

    private String dateName;

    public FriendlyMeeting() {
        super();
    }

    @Override
    public void printMeetingInfo(){
        System.out.println("You have a " + getMeetingName() + " meeting with " + getWith() + " on "
                + dateFormat.format(getDateOfMeeting()) + " which will take " + getDuration()/hour + " hour(s) and "
                + getDuration()%hour + " minute(s)." );
    }
    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }
}

