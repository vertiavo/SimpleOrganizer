package com.vertiavo.zadanie2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        EditText title = (EditText) findViewById(R.id.insert_title);
        EditText subtitle = (EditText) findViewById(R.id.insert_subtitle);
        dateView = (TextView) findViewById(R.id.insert_date_view);

        Calendar calendar = Calendar.getInstance();
        int year, month, day;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month+1, day);
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder()
                .append(day)
                .append("/")
                .append(month)
                .append("/")
                .append(year));
    }
}
