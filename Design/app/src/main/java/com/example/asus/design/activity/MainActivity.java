package com.example.asus.design.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.asus.design.R;
import com.example.asus.design.fragment.FragmentFactory;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class MainActivity extends BaseActivity {

    ViewPager main_viewpager;
    public PageBottomTabLayout tab;
    NavigationController navigationController;
    Toolbar toolbar;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_viewpager= (ViewPager) findViewById(R.id.main_viewpager);
        tab= (PageBottomTabLayout) findViewById(R.id.tab);
        navigationController = tab.material()
                .addItem(R.drawable.bottom_index, "首页",getResources().getColor(R.color.colorAccent))
                .addItem(R.drawable.bottom_find, "发现",getResources().getColor(R.color.colorAccent))
                .addItem(R.drawable.bottom_mine, "我的",getResources().getColor(R.color.colorAccent))
                .build();
        navigationController.setupWithViewPager(main_viewpager);
        main_viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    class  MyAdapter extends FragmentStatePagerAdapter
    {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("njr","----------------------->position"+position);
            Fragment fragment = FragmentFactory.getFragment(position);
            return fragment;

        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
