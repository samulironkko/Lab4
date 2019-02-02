package com.example.lab4;

public class PausePart extends WorkoutPartBase {

    public String time;

    @Override
    public void setTime(String data) {
        time = data;
    }

    @Override
    public String getTime() {
        return time;
    }
}
