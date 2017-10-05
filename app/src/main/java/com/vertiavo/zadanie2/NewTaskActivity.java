package com.vertiavo.zadanie2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NewTaskActivity extends AppCompatActivity {

    public static final String TASK_CREATED = "com.vertiavo.zadanie2.NewTaskActivity.TASK_CREATED";

    private List<Task> tasks = TaskSingleton.getInstance().getTasks();

    private EditText title;
    private EditText subtitle;
    private DatePicker datePicker;
    private TextView dateView;
    private TimePicker timePicker;
    private int year, month, day;
    private int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        title = (EditText) findViewById(R.id.insert_title);
        title.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        subtitle = (EditText) findViewById(R.id.insert_subtitle);
        dateView = (TextView) findViewById(R.id.insert_date_view);
        timePicker = (TimePicker) findViewById(R.id.insert_time);

        Calendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month+1, day);

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void showDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        dateView.setText(new StringBuilder()
                .append(day)
                .append("/")
                .append(month)
                .append("/")
                .append(year));
    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            showDate(year, month+1, day);
        }
    };

    public void setTime() {
        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
    }

    public void submitTask(View view) {
        setTime();
        Calendar calendar = new GregorianCalendar(year, month, day, hour, minute);

        if (validateTitle() && validateDate(calendar)) {
            tasks.add(
                    new Task(
                            title.getText().toString(),
                            subtitle.getText().toString(),
                            calendar));

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(TASK_CREATED, true);
            startActivity(intent);
        }
    }

    private boolean validateTitle() {
        if (title.getText().toString().length() == 0) {
            title.setError("Title is required!");
            return false;
        } else return true;
    }

    private boolean validateDate(Calendar calendar) {
        Calendar current = new GregorianCalendar();
        if (current.compareTo(calendar) > 0) {
            CharSequence deleted = "You can't select date in the past.";
            Toast.makeText(this, deleted, Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }
}
