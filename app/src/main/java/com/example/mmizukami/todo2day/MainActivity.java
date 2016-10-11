package com.example.mmizukami.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FOR NOW, delete the old database, then create a new one
    this.deleteDatabase(DBHelper.DATABASE_TABLE);

        // Let's make a DBHelper reference:
        DBHelper db = new DBHelper(this);


        // Let's make a new task and add to database

        db.addTask(new Task(1,"Study for CS273 Midterm",0));
        db.addTask(new Task(2,"Make a dinner",0));
        db.addTask(new Task(3,"Do landry",0));
        db.addTask(new Task(4,"Finish homework for cs200",0));
        db.addTask(new Task(5,"Play more league of legends",0));

        //Let's get all the tasks from database and print them with Log.i()
        ArrayList<Task> allTasks = db.getAllTasks();
        //Loop through each task, print to Log.i
        for(Task singleTask:allTasks)
            Log.i("DATABASE TASK", singleTask.toString());

    }
}
