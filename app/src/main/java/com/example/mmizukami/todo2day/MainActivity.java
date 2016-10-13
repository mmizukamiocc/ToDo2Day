package com.example.mmizukami.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper database;
    private List <Task> taskList;
    private TaskListAdapter taskListAdapter;

    private EditText taskEditText;
    private ListView taskListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FOR NOW, delete the old database, then create a new one
    this.deleteDatabase(DBHelper.DATABASE_TABLE);

        // Let's make a DBHelper reference:
        database = new DBHelper(this);

        // Let's add one dummy task
        //database.addTask(new Task("Dummy task",1));

        // Let's fill the list with Tasks from the database
        taskList = database.getAllTasks();

        // Let's create our custom task list adapter
        // (We want to associate the adapter with context, the layout, and the List)
        taskListAdapter = new TaskListAdapter(this, R.layout.task_item,taskList);

        // Connect the ListView with our layout
        taskListView = (ListView) findViewById(R.id.taskListView);

        // Associate the adapter with the list view
        taskListView.setAdapter(taskListAdapter);

        taskEditText = (EditText) findViewById(R.id.taskEditText);

    }

    public void addTask(View view)
    {
        String description = taskEditText.getText().toString();
        if(description.isEmpty())
        {
            Toast.makeText(this,"Task description cannot be empty.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Let's make a new Task
            Task newTask = new Task(description,0);
            // Let's add it to the list adapter
            taskListAdapter.add(newTask);
            // Let's add the Task to the database
            database.addTask(newTask);

            taskEditText.setText("");
        }
    }

    public void clearAllTasks(View view)
    {
        taskList.clear();
        database.deleteAllTasks();
        taskListAdapter.notifyDataSetChanged();
    }


    public void changeTaskStatus(View view)
    {
        if(view instanceof CheckBox)
        {
            CheckBox selectedCheckBox = (CheckBox) view;
            Task selectedTask = (Task)selectedCheckBox.getTag();
            selectedTask.setIsDone(selectedCheckBox.isChecked() ? 1:0);
            // update the selectedTask in the database
            database.updateTask(selectedTask);
        }
    }
}
