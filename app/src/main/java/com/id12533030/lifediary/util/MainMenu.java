package com.id12533030.lifediary.util;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.view.DayActivity;
import com.id12533030.lifediary.view.DiaryActivity;
import com.id12533030.lifediary.view.MainActivity;
import com.id12533030.lifediary.view.PlanActivity;
import com.id12533030.lifediary.view.SettingActivity;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/5/30.
 */
public class MainMenu implements OnMenuItemClickListener{
    private AppCompatActivity mAppCompatActivity;
    private FragmentManager mFragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    public MainMenu(AppCompatActivity appCompatActivity, FragmentManager fm, boolean checkBack) {
        mAppCompatActivity = appCompatActivity;
        mFragmentManager = fm;
        initToolbar(checkBack);
        initMenuFragment();
    }

    private void initToolbar(boolean checkBack) {
        Toolbar mToolbar = (Toolbar) mAppCompatActivity.findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) mAppCompatActivity.findViewById(R.id.text_view_toolbar_title);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        mAppCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBarTextView.setText(mAppCompatActivity.getTitle().toString());
        if (checkBack){
            mAppCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
            mAppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.btn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }


    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) mAppCompatActivity.getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(true);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);

    }

    private static List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);
        MenuObject homePage = new MenuObject(Constants.HOMEPAGE_MENU);
        homePage.setResource(R.drawable.ic_homepage);
        MenuObject diary = new MenuObject(Constants.DIARY_MENU);
        diary.setResource(R.drawable.ic_diary);
        MenuObject day = new MenuObject(Constants.DAY_MENU);
        day.setResource(R.drawable.ic_day);
        MenuObject plan = new MenuObject(Constants.PLAN_MENU);
        plan.setResource(R.drawable.ic_plan);
        MenuObject setting = new MenuObject(Constants.SETTING_MENU);
        setting.setResource(R.drawable.ic_setting);
        menuObjects.add(close);
        menuObjects.add(homePage);
        menuObjects.add(diary);
        menuObjects.add(day);
        menuObjects.add(plan);
        menuObjects.add(setting);
        return menuObjects;
    }


    private void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else{
            mAppCompatActivity.finish();
        }
    }


    public void onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = mAppCompatActivity.getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

    }


    public void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (mFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(mFragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
//        Toast.makeText(mAppCompatActivity.getBaseContext(), "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        Intent myIntent;
        switch (position){
            case 1:
                myIntent = new Intent(mAppCompatActivity, MainActivity.class);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_HOMEPAGE);
                break;
            case 2:
                myIntent = new Intent(mAppCompatActivity, DiaryActivity.class);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_DIARY);
                break;
            case 3:
                myIntent = new Intent(mAppCompatActivity, DayActivity.class);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_DAY);
                break;
            case 4:
                myIntent = new Intent(mAppCompatActivity, PlanActivity.class);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_PLAN);
                break;
            case 5:
                myIntent = new Intent(mAppCompatActivity, SettingActivity.class);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_SETTING);
                break;
            default:
                break;
        }
    }
}
