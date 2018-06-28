package com.example.admin.testingv1;

public class Event {
    private String title = "My Event";;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private String remarks = "";
    private String eventId;

    public Event(String title, String startTime, String endTime, String startDate, String endDate, String remarks, String eventId) {
        if(!title.equals("")){
            this.title = title;
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        if(!remarks.equals("")){
            this.remarks = remarks;
        }
        this.eventId = eventId;
    }

    public Event() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEventId() {
        return eventId;
    }

}
