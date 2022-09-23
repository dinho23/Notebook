package com.notebook.meetings;


public class RomanticMeeting extends Meeting{

    private String dateName;
    private boolean gift = false;

    public RomanticMeeting() {
        super();
    }

    @Override
    public void printMeetingInfo(){
        System.out.println("You have a " + getMeetingName() + " meeting with " + getWith() + " on " + dateFormat.format(getDateOfMeeting())
                + " which will take " + getDuration()/hour + " hour(s) and " + getDuration()%hour + " minute(s)." );
    }

    public boolean isGift() { return gift; }

    public void setGift(boolean gift) { this.gift = gift; }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }
}

