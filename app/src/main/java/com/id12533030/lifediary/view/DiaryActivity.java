package com.id12533030.lifediary.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;
import com.squareup.timessquare.CalendarPickerView;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class DiaryActivity extends AppCompatActivity implements View.OnClickListener, CompactCalendarView.CompactCalendarViewListener {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private CompactCalendarView mCompactCalendarView;
    private FloatingActionButton mAddFab;
    private TextView mYearTextView;
    private TextView mMonTextView;
    private static final String TAG = "DiaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        init();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar today = Calendar.getInstance();
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(today.getTimeInMillis())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(today.getTimeInMillis())));
    }
    private void init() {
        mAddFab = (FloatingActionButton) findViewById(R.id.diary_main_add_floatingactionbutton);
        mCompactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setUseThreeLetterAbbreviation(true);
        mYearTextView = (TextView) findViewById(R.id.diary_main_year_textview);
        mMonTextView = (TextView) findViewById(R.id.diary_main_month_textview);
    }

    private void setListener() {
        mAddFab.setOnClickListener(this);
        mCompactCalendarView.setListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.diary_main_add_floatingactionbutton:
                intent = new Intent(DiaryActivity.this, AddHomePage.class);
                startActivityForResult(intent, Constants.REQUEST_ADD_HOMEPAGE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDayClick(Date dateClicked) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(dateClicked.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(dateClicked.getTime())));
        Toast.makeText(getBaseContext(), "Day was clicked: " + dateClicked, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Day was clicked: " + dateClicked);
    }

    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(firstDayOfNewMonth.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(firstDayOfNewMonth.getTime())));
    }


}
