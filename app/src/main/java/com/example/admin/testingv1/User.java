package com.example.admin.testingv1;
import java.util.ArrayList;

public class User {
    private ArrayList<Event> events = new ArrayList <Event>();
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public User() {
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getUserId() {
        return userId;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void editEvent (Event event, Event editedEvent) {
        events.remove(event);
        events.add(editedEvent);

    }

}
