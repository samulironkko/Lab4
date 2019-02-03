package com.example.lab4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class RunProgramActivity extends AppCompatActivity {

    WorkoutPartBase current;
    TextView timeTextview;
    TextView nameTextview;
    ListIterator<WorkoutPartBase> iterator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program);
        ArrayList<WorkoutPartBase> workouts = (ArrayList<WorkoutPartBase>) getIntent().getSerializableExtra("WORKOUTS");
        nameTextview = (TextView)findViewById(R.id.workout_name);
        timeTextview = (TextView)findViewById(R.id.time_textview);
        iterator = workouts.listIterator();
        iterate();
    }

    private void iterate() {

        if (iterator.hasNext() == false) {
            finish();
        }

        while (iterator.hasNext()) {

            current = iterator.next();
            if (current instanceof WorkoutPart) {
                nameTextview.setText("Workout");
            } else {
                nameTextview.setText("Pause");
            }
            startTimer();
            iterator.remove();
            break;

        }
    }

    private void startTimer() {

        final String time = current.getTime();
        long duration = Integer.parseInt(time) * 1000;

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                timeTextview.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                iterate();
            }
        }.start();
    }
}
