package com.vertiavo.zadanie2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView dateView;
    private TimePicker timePicker;
    private TextView time;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        EditText title = (EditText) findViewById(R.id.insert_title);
        EditText subtitle = (EditText) findViewById(R.id.insert_subtitle);
        dateView = (TextView) findViewById(R.id.insert_date_view);
        timePicker = (TimePicker) findViewById(R.id.insert_time);
        time = (TextView) findViewById(R.id.insert_time_current);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month+1, day);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        showTime(hour, minute);
    }

    private void showDate(int year, int month, int day) {
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

    private void showTime(int hour, int minute) {
        String format;
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(minute)
                .append(" ").append(format));
    }

    public void setTime(View view) {
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        showTime(hour, minute);
    }
}
