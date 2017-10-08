package com.vertiavo.zadanie2.activity;

import android.app.AlertDialog;
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

import com.vertiavo.zadanie2.R;
import com.vertiavo.zadanie2.adapter.TaskListAdapter;
import com.vertiavo.zadanie2.util.Task;
import com.vertiavo.zadanie2.util.TaskSingleton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mainList;
    private List<Task> tasks = TaskSingleton.getInstance().getTasks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null & intent.getBooleanExtra(NewTaskActivity.TASK_CREATED, false)) {
                CharSequence created = "New task created";
//                Snackbar.make(this, created, Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, created, Toast.LENGTH_SHORT).show();

        }

        mainList = (ListView) findViewById(R.id.main_list);

        CharSequence deleted = "Task deleted";
        final Toast toast = Toast.makeText(this, deleted, Toast.LENGTH_SHORT);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
        return true;
    }

}
