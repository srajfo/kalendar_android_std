package com.example.kalendar_viewer;

public class EventItem {
    public String title;
    public String description;
    public String start;
    public String end;
    public String created_by;

    public EventItem(String title, String description, String start, String end, String created_by) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.created_by = created_by;
    }
}
