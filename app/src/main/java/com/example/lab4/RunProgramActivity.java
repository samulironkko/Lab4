package com.example.lab4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Locale;

public class RunProgramActivity extends AppCompatActivity {

    WorkoutPartBase current;
    TextView timeTextview;
    TextView nameTextview;
    ListIterator<WorkoutPartBase> iterator;
    TextToSpeech textToSpeech;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_program);
        ArrayList<WorkoutPartBase> workouts = (ArrayList<WorkoutPartBase>) getIntent().getSerializableExtra("WORKOUTS");
        nameTextview = (TextView)findViewById(R.id.workout_name);
        timeTextview = (TextView)findViewById(R.id.time_textview);
        iterator = workouts.listIterator();
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                    iterate();
                }
            }
        });

    }

    private void iterate() {

        if (iterator.hasNext()) {

            current = iterator.next();
            name = current.getName();
            nameTextview.setText(name);

            startTimer();
            iterator.remove();
        }
        else {
            textToSpeech.shutdown();
            finish();
        }
    }

    private void startTimer() {

        final String time = current.getTime();
        long duration = Integer.parseInt(time) * 1000;
        textToSpeech.speak(name, TextToSpeech.QUEUE_FLUSH, null);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }
}
