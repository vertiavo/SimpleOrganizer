package com.vertiavo.zadanie2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mainList;
    private List<Task> tasks = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = (ListView) findViewById(R.id.main_list);
        mockTasks();

        Context context = getApplicationContext();
        CharSequence deleted = "Deleted task";
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, deleted, duration);

        final TaskListAdapter taskListAdapter = new TaskListAdapter(this, tasks);
        mainList.setAdapter(taskListAdapter);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?")
                .setTitle("Warning");

        mainList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        tasks.remove(i);
                        toast.show();
                        taskListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }

    private void mockTasks() {
        tasks.add(
                new Task("Napisać program",
                        "Napisać program z Systemów Mobilnych",
                        new GregorianCalendar()));
        tasks.add(
                new Task("Wyprać ciuchy",
                        "Wyprać ubrania leżące w koszu na ubrania.",
                        new GregorianCalendar(2017, 10, 21)));
        tasks.add(
                new Task("Kupić bilet",
                        "Kupić bilet na pociąg Białystok-Warszawa",
                        new GregorianCalendar(2017, 11, 5)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        addNewTask();
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
        return true;
    }

    private void addNewTask() {
        TaskListAdapter taskListAdapter = (TaskListAdapter) mainList.getAdapter();

        tasks.add(
                new Task("Kupić bilet",
                        "Kupić bilet na pociąg Białystok-Warszawa",
                        new GregorianCalendar(2017, 11, 5)));
        taskListAdapter.notifyDataSetChanged();
    }

}
