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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.id12533030.lifediary.adapter.DiaryListAdapter;
import com.id12533030.lifediary.model.Diary;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.DateProcess;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class DiaryActivity extends AppCompatActivity implements CompactCalendarView.CompactCalendarViewListener, DiaryListAdapter.MyItemClickListener {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private CompactCalendarView mCompactCalendarView;

    private TextView mYearTextView;
    private TextView mMonTextView;
    private static final String TAG = "DiaryActivity";
    private RecyclerView mRecyclerView;
    private DiaryListAdapter mDairyAdapter;
    private Date mDate;
    private ArrayList<Diary> mAllDiaryList;

    private final static int REQUEST_CODE = 1;

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

    @Override
    protected void onStart() {
        super.onStart();
        Calendar today = Calendar.getInstance();
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(today.getTimeInMillis())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(today.getTimeInMillis())));

    }
    private void init() {
        mCompactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setUseThreeLetterAbbreviation(true);
        mYearTextView = (TextView) findViewById(R.id.diary_main_year_textview);
        mMonTextView = (TextView) findViewById(R.id.diary_main_month_textview);
        mRecyclerView = (RecyclerView) findViewById(R.id.diary_main_recyclerView);
    }

    private void setListener() {
        mCompactCalendarView.setListener(this);
        mDairyAdapter.setOnItemClickListener(this);
    }

    private void setAdapter() {
        mDairyAdapter = new DiaryListAdapter(this, mAllDiaryList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDairyAdapter);
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
    public void onDayClick(Date dateClicked) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(dateClicked.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(dateClicked.getTime())));
//        Toast.makeText(getBaseContext(), "Day was clicked: " + dateClicked, Toast.LENGTH_LONG).show();
        mDate = dateClicked;
        Log.d(TAG, "Day was clicked: " + dateClicked);

        showDiaryBasedOnDate(dateClicked);
    }

    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(firstDayOfNewMonth.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(firstDayOfNewMonth.getTime())));
        showDiaryBasedOnDate(firstDayOfNewMonth);
    }

    private void showDiaryBasedOnDate(Date date){
        ArrayList<Diary> selectedDiary = new ArrayList<>();
        for (int i = 0; i < mAllDiaryList.size(); ++i){
            Diary diary = mAllDiaryList.get(i);
            if (DateProcess.getDateAsString(diary.getDate()).equals(new SimpleDateFormat("dd-MM-yyyy").format(date))){
                selectedDiary.add(diary);
            }
        }
        mRecyclerView.setAdapter(new DiaryListAdapter(this, selectedDiary));
    }

    @Override
    public void onItemClick(View view, int position) {
        Diary diary =  mAllDiaryList.get(position);
        Long id = diary.getId();
        Intent intent = new Intent(DiaryActivity.this, DiaryDetail.class);
        intent.putExtra(Constants.EXTRA_DIARY, id);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemLongClick(View view, final int position) {
        Diary diary =  mAllDiaryList.get(position);
        final Long id = diary.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this);
        builder.setMessage("Do you want to delete it?");
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Diary delDiary = Diary.findById(Diary.class, id);
                delDiary.delete();
                mDairyAdapter.refresh(position);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
