package com.example.lab4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int ADD_NEW_PART_REQ_ID = 311;

    ArrayList<WorkoutPartBase> workouts = new ArrayList<>();
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        findViewById(R.id.start_workout_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent startIntent = new Intent(this, RunProgramActivity.class);
        startIntent.putExtra("WORKOUTS", workouts);
        startActivity(startIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        Intent intent = new Intent(this, NewPartActivity.class);
        startActivityForResult(intent, ADD_NEW_PART_REQ_ID);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        WorkoutArrayAdapter adapter = new WorkoutArrayAdapter(this, workouts);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_PART_REQ_ID && resultCode == RESULT_OK) {
            WorkoutPartBase workoutPartBase = (WorkoutPartBase) data.getSerializableExtra("WORKOUTPART");
            workouts.add(workoutPartBase);
        }
    }
}
