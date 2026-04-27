package com.example.kalendar_viewer;

public class DayModel {
    public String date;
    public int day;
    public int events;

    public DayModel(String date, int day, int events) {
        this.date = date;
        this.day = day;
        this.events = events;
    }
}
