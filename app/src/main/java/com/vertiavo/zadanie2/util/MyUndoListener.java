package com.vertiavo.zadanie2.util;

import android.view.View;

import com.vertiavo.zadanie2.adapter.TaskListAdapter;

import java.util.List;

/**
 * Created by vertiavo on 08.10.17.
 */

public class MyUndoListener implements View.OnClickListener {

    private TaskListAdapter taskListAdapter;

    public MyUndoListener(TaskListAdapter taskListAdapter) {
        super();
        this.taskListAdapter = taskListAdapter;
    }

    @Override
    public void onClick(View view) {
        TaskSingleton taskSingleton = TaskSingleton.getInstance();
        List<Task> tasks = taskSingleton.getTasks();
        tasks.remove(tasks.size() - 1);
        taskListAdapter.notifyDataSetChanged();
    }
}
