package com.id12533030.lifediary.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Day;
import com.id12533030.lifediary.util.MainMenu;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LENOVO on 2016/5/31.
 * This activity is used to show the list of Day with animation based on external library.
 */
public class DayActivity extends EuclidActivity {
    private MainMenu mMainMenu;

    /**
     * Override the onCreate method
     *
     * @param savedInstanceState
     */
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

    /**
     * Get Adapter and set the list of Day
     *
     * @return
     */
    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        final ArrayList<Day> dayList = (ArrayList<Day>) Day.listAll(Day.class);
        final int len = dayList.size();
        for (int i = len - 1; i >= 0; --i) {
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

    /**
     * Choose the image to different type of Day
     *
     * @param num
     * @return
     */
    private int chooseImage(int num) {
        switch (num) {
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
}
