package com.id12533030.lifediary.util;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Setting;
import com.id12533030.lifediary.view.DayActivity;
import com.id12533030.lifediary.view.DiaryActivity;
import com.id12533030.lifediary.view.MainActivity;
import com.id12533030.lifediary.view.PlanActivity;
import com.id12533030.lifediary.view.RecordActivity;
import com.id12533030.lifediary.view.SettingActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 2016/5/30.
 * This class is used to defined the main menu based on external library
 */
public class MainMenu implements OnMenuItemClickListener {
    private AppCompatActivity mAppCompatActivity;
    private FragmentManager mFragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private static final String HELLO = "Hello ";
    private boolean mIsMenuClickable;

    /**
     * Constructor of MainMenu without passing FragmentManager
     *
     * @param appCompatActivity
     * @param checkBack
     * @param addMenu
     */
    public MainMenu(AppCompatActivity appCompatActivity, boolean checkBack, boolean addMenu) {
        mAppCompatActivity = appCompatActivity;
        initToolbar(checkBack);
        initMenuFragment(addMenu);
    }

    /**
     * Constructor of MainMenu with FragmentManager
     *
     * @param appCompatActivity
     * @param fm
     * @param checkBack
     * @param addMenu
     */
    public MainMenu(AppCompatActivity appCompatActivity, FragmentManager fm, boolean checkBack, boolean addMenu) {
        mAppCompatActivity = appCompatActivity;
        mFragmentManager = fm;
        initToolbar(checkBack);
        initMenuFragment(addMenu);
    }

    /**
     * Init the toolbar
     *
     * @param checkBack : whether to set the back arrow
     */
    private void initToolbar(boolean checkBack) {
        Toolbar mToolbar = (Toolbar) mAppCompatActivity.findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) mAppCompatActivity.findViewById(R.id.text_view_toolbar_title);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        mAppCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBarTextView.setText(mAppCompatActivity.getTitle().toString());
        //The back arrow can return back to last activity
        if (checkBack) {
            mAppCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
            mAppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * Init menu menu fragment
     *
     * @param isClickable : whether to set the menu clickable
     */
    private void initMenuFragment(boolean isClickable) {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) mAppCompatActivity.getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(true);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        //Record the state of menu
        mIsMenuClickable = isClickable;
        if (isClickable) {
            mMenuDialogFragment.setItemClickListener(this);
        }
    }

    /**
     * Set the menu objects first and then return the object
     *
     * @return
     */
    private static List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject();

        if (Setting.listAll(Setting.class).size() != 0) {
            Setting setting = Setting.findById(Setting.class, Constants.SETTING_INDEX);
            close.setTitle(HELLO + setting.getName());
        }

        close.setResource(R.drawable.ic_menu_back);
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
        MenuObject record = new MenuObject(Constants.RECORD_MENU);
        record.setResource(R.drawable.ic_record);
        MenuObject exit = new MenuObject(Constants.EXIT_MENU);
        exit.setResource(R.drawable.ic_exit);
        menuObjects.add(close);
        menuObjects.add(homePage);
        menuObjects.add(diary);
        menuObjects.add(day);
        menuObjects.add(plan);
        menuObjects.add(setting);
        menuObjects.add(record);
        menuObjects.add(exit);
        return menuObjects;
    }

    /**
     * Set the function of back arrow
     */
    private void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            mAppCompatActivity.finish();
        }
    }

    /**
     * Set the system status bar
     *
     * @param activity
     */
    public static void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.color.colorPrimary);

    }

    /**
     * This method is to set the attribute of menu
     *
     * @param activity
     * @param on
     */
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * Set two different menus. One is clickable and the other is unclickable
     *
     * @param menu
     */
    public void onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = mAppCompatActivity.getMenuInflater();
        if (mIsMenuClickable) {
            inflater.inflate(R.menu.clickable_menu, menu);
        } else {
            inflater.inflate(R.menu.unclickable_menu, menu);
        }

    }

    /**
     * Select the item in the menu
     *
     * @param item
     */
    public void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clickable_menu:
                if (mFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(mFragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
    }

    /**
     * Implement teh functions when click the items of menu
     *
     * @param clickedView
     * @param position
     */
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        //Toast.makeText(mAppCompatActivity.getBaseContext(), "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        Intent myIntent;
        switch (position) {
            case 1:
                myIntent = new Intent(mAppCompatActivity, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_HOMEPAGE);
                break;
            case 2:
                myIntent = new Intent(mAppCompatActivity, DiaryActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_DIARY);
                break;
            case 3:
                myIntent = new Intent(mAppCompatActivity, DayActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_DAY);
                break;
            case 4:
                myIntent = new Intent(mAppCompatActivity, PlanActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_PLAN);
                break;
            case 5:
                myIntent = new Intent(mAppCompatActivity, SettingActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_SETTING);
                break;
            case 6:
                myIntent = new Intent(mAppCompatActivity, RecordActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mMenuDialogFragment.startActivityForResult(myIntent, Constants.REQUEST_RECORD);
                break;
            default:
                //Exit the app
                System.exit(0);
        }
    }
}
