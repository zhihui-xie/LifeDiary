package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;

import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;
import com.squareup.timessquare.CalendarPickerView;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class DiaryActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);

//        Calendar nextMonth = Calendar.getInstance();
//        nextMonth.add(Calendar.MONTH, 1);

        final CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date start = new Date(116,1,1);
        Date today = new Date();
        calendar.init(start, today);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Log.i("calendar: ", calendar.getSelectedDate().getMonth() + "." + calendar.getSelectedDate().getDate());
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mMainMenu.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mMainMenu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
