package com.example.lab4;

public class PausePart extends WorkoutPartBase {

    public String time;
    public String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName() {
        this.name = "Pause";
    }

    @Override
    public void setTime(String data) {
        time = data;
    }

    @Override
    public String getTime() {
        return time;
    }
}
