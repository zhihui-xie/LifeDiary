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

import com.id12533030.lifediary.R;
import com.id12533030.lifediary.model.Plan;
import com.id12533030.lifediary.util.MainMenu;

import butterknife.ButterKnife;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by LENOVO on 2016/5/31.
 * This activity is used to show the list of Plan. In this activity, there viewPager, tabLayout and
 * blurView which is based on external library to make the tab translucent. What's more, it show the
 * number of urgent plans and normal plans.
 */
public class PlanActivity extends AppCompatActivity {
    private MainMenu mMainMenu;
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private BlurView mBlurView;

    /**
     * Override the onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_main);
        mFragmentManager = getSupportFragmentManager();
        mMainMenu = new MainMenu(this, mFragmentManager, true, true);
        mMainMenu.initSystemBar(this);
        init();

        if (Plan.listAll(Plan.class).size() == 0) {
            testMethod();
        }

        ButterKnife.bind(this);
        setupBlurView();
        setupViewPager();
    }

    /**
     * Test the app
     */
    private void testMethod() {
        for (int i = 0; i < 10; ++i) {
            Plan plan = new Plan(0, "Urgent", "07.06.2016", "Sydney", "Urgent plan");
            plan.save();
        }
        for (int i = 0; i < 10; ++i) {
            Plan plan = new Plan(1, "Normal", "08.06.2016", "Melbourne", "Normal plan");
            plan.save();
        }
    }

    /**
     * Initial all the widgets
     */
    private void init() {
        mViewPager = (ViewPager) findViewById((R.id.viewPager));
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mBlurView = (BlurView) findViewById(R.id.blurView);
    }

    /**
     * Setup the viewPager
     */
    private void setupViewPager() {
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Setup BlurView
     */
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

    /**
     * Defined the ViewPagerAdapter class which extends FragmentPagerAdapter. It has three pages and
     * can use tabs to switch them.
     */
    static class ViewPagerAdapter extends FragmentPagerAdapter {

        /**
         * Constructor of ViewPagerAdapter
         *
         * @param fragmentManager
         */
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * Get the item and set the relevant fragment
         *
         * @param position
         * @return
         */
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

        /**
         * Override the getPageTitle method
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return Page.values()[position].getTitle();
        }

        /**
         * Get the number of the pages
         *
         * @return
         */
        @Override
        public int getCount() {
            return Page.values().length;
        }
    }

    /**
     * Initial the Page
     */
    enum Page {
        FIRST("Urgent"),
        SECOND("Normal"),
        THIRD("Statistics");
        private String title;

        /**
         * Constructor
         *
         * @param title
         */
        Page(String title) {
            this.title = title;
        }

        /**
         * Get the title of the page
         *
         * @return
         */
        public String getTitle() {
            return title;
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
