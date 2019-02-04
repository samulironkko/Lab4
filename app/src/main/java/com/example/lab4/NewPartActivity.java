package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewPartActivity extends AppCompatActivity {

    RadioButton workoutRadio;
    RadioButton pauseRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_part);
        workoutRadio = (RadioButton) findViewById(R.id.radio_workout);
        pauseRadio = (RadioButton) findViewById(R.id.radio_pause);
        final EditText editText = findViewById(R.id.edit_time);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (workoutRadio.isChecked()) {
                    WorkoutPart workoutPart = new WorkoutPart();
                    workoutPart.setTime(editText.getText().toString());
                    workoutPart.setName();
                    returnData(workoutPart);
                }
                else if (pauseRadio.isChecked()) {
                    PausePart pausePart = new PausePart();
                    pausePart.setTime(editText.getText().toString());
                    pausePart.setName();
                    returnData(pausePart);
                }

            }
        });
    }

    private void returnData(WorkoutPartBase data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("WORKOUTPART", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
