package com.example.lab4;

import android.os.Parcelable;

import java.io.Serializable;

public abstract class WorkoutPartBase implements Serializable {

    abstract public void setTime(String data);
    abstract public String getTime();
    abstract public void setName();
    abstract public String getName();

}
