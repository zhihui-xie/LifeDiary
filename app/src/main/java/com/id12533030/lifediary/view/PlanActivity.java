package com.id12533030.lifediary.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.id12533030.lifediary.model.Plan;
import com.id12533030.lifediary.util.MainMenu;
import com.id12533030.lifediary.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by LENOVO on 2016/5/31.
 */
public class PlanActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private BlurView mBlurView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        init();

        if(Plan.listAll(Plan.class).size() == 0){
            testMethod();
        }

        ButterKnife.bind(this);
        setupBlurView();
        setupViewPager();
    }

    private void testMethod(){
        for (int i = 0; i < 10; ++i){
            Plan plan = new Plan(0, "Urgent", "07.06.2016","Sydney", "Urgent plan");
            plan.save();
        }
        for (int i = 0; i < 10; ++i){
            Plan plan = new Plan(1, "Normal", "08.06.2016","Melbourne", "Normal plan");
            plan.save();
        }
    }

    private void init() {
        mViewPager = (ViewPager) findViewById((R.id.viewPager));
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mBlurView = (BlurView) findViewById(R.id.blurView);
    }

    private void setupViewPager() {
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupBlurView() {
        final int radius = 16;

        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        mBlurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this, true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (Page.values()[position]) {
                case FIRST:
                    return PlanListFragment.newInstance(0);
                case SECOND:
                    return PlanListFragment.newInstance(1);
                case THIRD:
                    return new PlanStatisticsFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Page.values()[position].getTitle();
        }

        @Override
        public int getCount() {
            return Page.values().length;
        }
    }

    enum Page {
        FIRST("Urgent"),
        SECOND("Normal"),
        THIRD("Statistics");

        private String title;

        Page(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
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
