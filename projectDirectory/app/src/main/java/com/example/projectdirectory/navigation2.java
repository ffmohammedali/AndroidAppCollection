package com.example.projectdirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.example.projectdirectory.fragments.Fragment01;
import com.example.projectdirectory.fragments.Item2;
import com.example.projectdirectory.fragments.item03;
import com.google.android.material.tabs.TabLayout;

public class navigation2 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private myPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation2);
        tabLayout = findViewById(R.id.tabLayout1);
        tabLayout.addTab(tabLayout.newTab().setText("hello 1").setIcon(R.drawable.ic_search_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("hello 2").setIcon(R.drawable.ic_tag_faces_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("hello 3").setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPage1);
        myPagerAdapter = new myPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myPagerAdapter);

        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });


    }
}

class myPagerAdapter extends FragmentPagerAdapter {
    private int numOftabItem;

    public myPagerAdapter(FragmentManager fragmentManager, int numOftabItem) {
        super(fragmentManager);
        this.numOftabItem = numOftabItem;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment01 fragment01 = new Fragment01();
                return fragment01;
            case 1:
                Item2 fragment02 = new Item2();
                return fragment02;
            case 2:
                 item03 fragment03 = new item03();
                return fragment03;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOftabItem;
    }
}

