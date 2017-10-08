package com.vertiavo.zadanie2.util;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vertiavo on 04.10.17.
 */

public class TaskSingleton {

    private static TaskSingleton instance;
    private static List<Task> tasks = new LinkedList<>();

    private TaskSingleton() {}

    public static TaskSingleton getInstance() {
        if (instance == null) {
            instance = new TaskSingleton();
            mockTasks();
        }
        return instance;
    }

    private static void mockTasks() {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
