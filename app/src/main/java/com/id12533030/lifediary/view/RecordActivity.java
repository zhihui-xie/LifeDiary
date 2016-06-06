package com.id12533030.lifediary.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.util.Constants;
import com.id12533030.lifediary.util.ImageTool;
import com.id12533030.lifediary.util.MainMenu;

/**
 * Created by LENOVO on 2016/6/7.
 */
public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private FloatingActionButton mDiaryFab;
    private FloatingActionButton mDayFab;
    private FloatingActionButton mPlanFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_function_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        init();
        setListener();
    }

    private void init() {
        mDiaryFab = (FloatingActionButton) findViewById(R.id.record_function_main_diary_fab);
        mDayFab = (FloatingActionButton) findViewById(R.id.record_function_main_day_fab);
        mPlanFab = (FloatingActionButton) findViewById(R.id.record_function_main_plan_fab);
    }

    private void setListener() {
        mDiaryFab.setOnClickListener(this);
        mDayFab.setOnClickListener(this);
        mPlanFab.setOnClickListener(this);
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
            case R.id.record_function_main_diary_fab:
                intent = new Intent(RecordActivity.this, AddHomePage.class);
                startActivityForResult(intent, Constants.REQUEST_ADD_HOMEPAGE);
                break;
            case R.id.record_function_main_day_fab:
                intent = new Intent(RecordActivity.this, AddHomePage.class);
                startActivityForResult(intent, Constants.REQUEST_ADD_HOMEPAGE);
                break;
            case R.id.record_function_main_plan_fab:
                intent = new Intent(RecordActivity.this, AddHomePage.class);
                startActivityForResult(intent, Constants.REQUEST_ADD_HOMEPAGE);
                break;
            default:
                break;
        }
    }
}
