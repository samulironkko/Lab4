package com.example.lab4;

import java.io.Serializable;

public abstract class WorkoutPartBase implements Serializable {

    abstract public void setTime(String data);
    abstract public String getTime();

}
