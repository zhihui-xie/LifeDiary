package com.id12533030.lifediary.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.id12533030.lifediary.R;
import com.id12533030.lifediary.adapter.DiaryListAdapter;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.MainMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LENOVO on 2016/5/31.
 * This activity is used to show the list of diaries based on the calender. When user first come to
 * this activity, it will list all the diaries which users stored before. Then user can select the
 * the date on the calender to show relevant diaries.
 */
public class DiaryActivity extends AppCompatActivity implements CompactCalendarView.CompactCalendarViewListener, DiaryListAdapter.MyItemClickListener {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    //The calender is based on the external library
    private CompactCalendarView mCompactCalendarView;
    private TextView mYearTextView;
    private TextView mMonTextView;
    private static final String TAG = "DiaryActivity";
    private static final String DAY_CLICK = "Day was clicked: ";
    private RecyclerView mRecyclerView;
    private DiaryListAdapter mDairyAdapter;
    private Date mDate;
    private ArrayList<Diary> mAllDiaryList;

    private final static int REQUEST_CODE = 1;

    /**
     * Override the onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        mAllDiaryList = (ArrayList<Diary>) Diary.listAll(Diary.class);
        init();
        setAdapter();
        setListener();
    }

    /**
     * Override the onStart method and set the year and month of the calendar
     */
    @Override
    protected void onStart() {
        super.onStart();
        Calendar today = Calendar.getInstance();
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(today.getTimeInMillis())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(today.getTimeInMillis())));
    }

    /**
     * Initial all the widgets
     */
    private void init() {
        mCompactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setUseThreeLetterAbbreviation(true);
        mYearTextView = (TextView) findViewById(R.id.diary_main_year_textview);
        mMonTextView = (TextView) findViewById(R.id.diary_main_month_textview);
        mRecyclerView = (RecyclerView) findViewById(R.id.diary_main_recyclerView);
    }

    /**
     * Set the listener of calendar and adapter
     */
    private void setListener() {
        mCompactCalendarView.setListener(this);
        mDairyAdapter.setOnItemClickListener(this);
    }

    /**
     * Set the adapter and pass the list of diary
     */
    private void setAdapter() {
        mDairyAdapter = new DiaryListAdapter(this, mAllDiaryList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDairyAdapter);
    }

    /**
     * Override the onCreateOptionsMenu method
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mMainMenu.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * Override the onOptionsItemSelected method
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mMainMenu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    /**
     * Implement the click function of calendar
     *
     * @param dateClicked
     */
    @Override
    public void onDayClick(Date dateClicked) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(dateClicked.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(dateClicked.getTime())));
//        Toast.makeText(getBaseContext(), "Day was clicked: " + dateClicked, Toast.LENGTH_LONG).show();
        mDate = dateClicked;
        Log.d(TAG, DAY_CLICK + dateClicked);
        showDiaryBasedOnDate(dateClicked);
    }

    /**
     * Implement the scroll of calender to change the month and update the month TextView
     *
     * @param firstDayOfNewMonth
     */
    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(firstDayOfNewMonth.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(firstDayOfNewMonth.getTime())));
        showDiaryBasedOnDate(firstDayOfNewMonth);
    }

    /**
     * Show the list of diaries which are written in the selected date
     *
     * @param date
     */
    private void showDiaryBasedOnDate(Date date) {
        ArrayList<Diary> selectedDiary = new ArrayList<>();
        for (int i = 0; i < mAllDiaryList.size(); ++i) {
            Diary diary = mAllDiaryList.get(i);
            if (DateProcess.getDateAsString(diary.getDate()).equals(new SimpleDateFormat("dd-MM-yyyy").format(date))) {
                selectedDiary.add(diary);
            }
        }
        mRecyclerView.setAdapter(new DiaryListAdapter(this, selectedDiary));
    }

    /**
     * Implement the onItemClick. When the users click the item of diary, it will go to another
     * activity to show the details of this diary
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        Diary diary = mAllDiaryList.get(position);
        Long id = diary.getId();
        Intent intent = new Intent(DiaryActivity.this, DiaryDetail.class);
        intent.putExtra(Constants.EXTRA_DIARY, id);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Implement the long click. When users long click the item, it will ask users whether to delete
     * this diary
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemLongClick(View view, final int position) {
        Diary diary = mAllDiaryList.get(position);
        final Long id = diary.getId();
        //Show the dialog with two buttons including confirm and cancel.
        AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this);
        builder.setMessage("Do you want to delete it?");
        builder.setPositiveButton(Constants.CONFIRM, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Diary delDiary = Diary.findById(Diary.class, id);
                delDiary.delete();
                mDairyAdapter.deleteItem(position);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(Constants.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
