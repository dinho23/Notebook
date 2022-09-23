package com.notebook.meetings;


public class BusinessMeeting extends Meeting{
    private String businessName;
    private String importance;

    public BusinessMeeting() {
        super();
    }

    @Override
    public void printMeetingInfo(){
        System.out.println("You have a " + getMeetingName() + " meeting of " + importance + "/10" + " with "
                + getWith() + " on " + dateFormat.format(getDateOfMeeting()) + " which will take " + getDuration()/hour
                + " hour(s) and " + getDuration()%hour + " minute(s)." );
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getImportance() { return importance; }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
