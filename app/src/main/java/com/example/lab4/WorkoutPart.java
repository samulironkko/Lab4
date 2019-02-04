package com.example.lab4;

import android.widget.EditText;

public class WorkoutPart extends WorkoutPartBase {

    public String time;
    public String name;

    @Override
    public String getName() {
        return name;
    }


    public void setName() {
        this.name = "Workout";
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
