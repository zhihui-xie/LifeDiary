package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
        testMethod();
        Day day = new Day(1, "Day", "06.06.2016", "Important day");
        day.save();
        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DayActivity.this, "Don't forget it!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testMethod(){
        for (int i = 0; i < 5; ++i){
            Day day = new Day(0, "Birthday", "06.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 5; ++i){
            Day day = new Day(1, "Anniversary", "05.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 5; ++i){
            Day day = new Day(2, "Festival", "04.06.2016", "Important day");
            day.save();
        }
        for (int i = 0; i < 5; ++i){
            Day day = new Day(3, "Others", "01.06.2016", "Important day");
            day.save();
        }
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.birthday,
                R.drawable.anniversary,
                R.drawable.festival,
                R.drawable.others,
                };

//        String[] names = getResources().getStringArray(R.array.array_names);
        String[] names = {"Day One", "Day Two", "Day Three", "Day Four"};
        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
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
