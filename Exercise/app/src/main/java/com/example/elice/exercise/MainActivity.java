package com.example.elice.exercise;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elice.exercise.fragment.BlankFragment;
import com.example.elice.exercise.fragment.TimelineFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment[] fragments = new Fragment[2];
        fragments[0] = new TimelineFragment();
        fragments[1] = new BlankFragment();

        TabLayout layout = (TabLayout) findViewById(R.id.tabs);
        Myadpater adpater = new Myadpater(getSupportFragmentManager(), fragments);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_pager);

        layout.setupWithViewPager(viewPager);
        layout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(adpater);

    }

    public class Myadpater extends FragmentPagerAdapter {

        Fragment[] arrFragment;

        public Myadpater(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            arrFragment = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragment[position];
        }

        @Override
        public int getCount() {
            return arrFragment.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "TIMELINE";
                case 1:
                    return "BLANK";
                default:
                    return "";
            }
        }
    }
}
