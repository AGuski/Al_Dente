package com.alex.aldente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddTimer {

    VerticalViewPager mViewPager;
    PagerAdapter mPagerAdapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get List with Vertical Pages
        fragments = getFragments();

        mPagerAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        VerticalViewPager mViewPager = (VerticalViewPager)findViewById(R.id.vertical_pager);

        mViewPager.setAdapter(mPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<>();

        fList.add(TimerFragment.newInstance("8 min", 480, R.drawable.pasta));

        fList.add(TimerFragment.newInstance("3 min", 15, R.drawable.tea));

        //fList.add(AddTimerFragment.newInstance());

        return fList;

    }

    /* Method to add a Timer to the App */

    public void addTimerFragment(String name, int time, int image) {
        Log.d(name,time+"" );
        Fragment addFragment = fragments.get(fragments.size()-1);
        fragments.remove(fragments.size()-1);
        //fragments.add(TimerFragment.newInstance(name,time,image));
        //fragments.add(addFragment);
        mPagerAdapter.notifyDataSetChanged();

    }

}
