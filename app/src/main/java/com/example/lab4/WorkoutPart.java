package com.example.lab4;

import android.widget.EditText;

public class WorkoutPart extends WorkoutPartBase {

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
