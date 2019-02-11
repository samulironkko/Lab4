package com.example.lab4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int ADD_NEW_PART_REQ_ID = 311;

    ArrayList<WorkoutPartBase> workouts;
    ListView listView = null;
    FileOutputStream outputStream;
    FileInputStream inputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        findViewById(R.id.start_workout_button).setOnClickListener(this);
        workouts = new ArrayList<WorkoutPartBase>();

        try {
            inputStream = openFileInput("workoutFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            workouts = (ArrayList)objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();

        }catch (IOException ioe){
            ioe.printStackTrace();
            return;
        }catch (ClassNotFoundException c){
            c.printStackTrace();
            return;
        }

    }

    @Override
    protected void onStop() {
        try {
            outputStream = openFileOutput("workoutFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(workouts);
            objectOutputStream.close();
            outputStream.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        super.onStop();
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
        if (item.getItemId() == R.id.new_button) {
            Intent intent = new Intent(this, NewPartActivity.class);
            startActivityForResult(intent, ADD_NEW_PART_REQ_ID);
        }
        else if (item.getItemId() == R.id.clear_item) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Clear workouts");
            builder.setMessage("Do you really want to clear workouts?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    workouts.clear();
                    onResume();
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
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
