package com.example.myapplication;

public class ExampleItem {
    private String Name;
    private String ECTS;
    private String Schedule;

    //Constructor
    public ExampleItem(String name, String ECTS, String schedule) {
        this.Name = name;
        this.ECTS = ECTS;
        this.Schedule = schedule;
    }


    //Getters_Setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getECTS() {
        return ECTS;
    }

    public void setECTS(String ECTS) {
        this.ECTS = ECTS;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String proffesor) {
        Schedule = proffesor;
    }
}

