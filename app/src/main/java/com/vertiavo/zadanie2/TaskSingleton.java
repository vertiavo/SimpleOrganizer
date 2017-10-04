package com.vertiavo.zadanie2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vertiavo on 04.10.17.
 */

public class TaskSingleton {

    private static TaskSingleton instance;
    private List<Task> tasks = new LinkedList<>();

    private TaskSingleton() {}

    public static TaskSingleton getInstance() {
        if (instance == null) instance = new TaskSingleton();
        return instance;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
