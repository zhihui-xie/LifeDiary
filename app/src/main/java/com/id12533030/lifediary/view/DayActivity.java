package com.id12533030.lifediary.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.id12533030.lifediary.model.Day;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class DayActivity extends EuclidActivity {
    private MainMenu mMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainMenu.initSystemBar(this);


        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DayActivity.this, "Don't forget it!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testMethod(){
        for (int i = 0; i < 2; ++i){
            Day day = new Day(0, "Birthday", "06.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 2; ++i){
            Day day = new Day(1, "Anniversary", "05.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 2; ++i){
            Day day = new Day(2, "Festival", "04.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 2; ++i){
            Day day = new Day(3, "Others", "01.06.2016", "Important day");
            day.save();
        }
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();
        if(Day.listAll(Day.class).size() == 0){
            testMethod();
        }

        final ArrayList<Day> dayList = (ArrayList<Day>) Day.listAll(Day.class);
        final int len = dayList.size();
        for (int i = 0; i < len; ++i){
            Day day = dayList.get(i);
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, chooseImage(day.getType()));
            profileMap.put(EuclidListAdapter.KEY_NAME, day.getTitle());
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, day.getDate());
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, day.getText());
            profilesList.add(profileMap);

        }
        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }

    private int chooseImage(int num){
        switch (num){
            case 0:
                return R.drawable.birthday;
            case 1:
                return R.drawable.anniversary;
            case 2:
                return R.drawable.festival;
            default:
                return R.drawable.others;
        }
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
