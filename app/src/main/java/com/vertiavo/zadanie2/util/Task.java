package com.vertiavo.zadanie2.util;

import android.app.PendingIntent;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by vertiavo on 02.10.17.
 */

public class Task {

    private String title;
    private String description;
    private Calendar deadline;
    private Timestamp createTime;
    private PendingIntent pendingIntent;

    public Task(String title, String description, Calendar deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    public Task(String title, String description, Calendar deadline, PendingIntent pendingIntent) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.pendingIntent = pendingIntent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }
}
