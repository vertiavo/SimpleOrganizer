package com.vertiavo.zadanie2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vertiavo on 02.10.17.
 */

public class TaskListAdapter extends BaseAdapter {

    private List<Task> tasks;
    private final LayoutInflater inflater;

    public TaskListAdapter(Context context, List<Task> tasks) {
        super();
        this.tasks = tasks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.list_item, viewGroup, false);

        TextView taskTitleView = rowView.findViewById(R.id.list_item_title);
        TextView taskSubtitleView = rowView.findViewById(R.id.list_item_subtitle);
        TextView taskDetailView = rowView.findViewById(R.id.list_item_detail);

        Task task = (Task) getItem(i);

        taskTitleView.setText(task.getTitle());
        taskSubtitleView.setText(task.getDescription());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd MM yyyy");
        Calendar calendar = task.getDeadline();
        taskDetailView.setText(sdf.format(calendar.getTime()));

        return rowView;
    }
}
