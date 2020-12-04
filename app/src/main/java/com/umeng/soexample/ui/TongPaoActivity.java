package com.umeng.soexample.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.ui.fragment.RecommendFragment;
import com.umeng.soexample.ui.fragment.SquareFragment;

import java.util.ArrayList;
import java.util.List;


public class TongPaoActivity extends AppCompatActivity {


    String[] tabs = {"推荐", "广场", "视频", "摄影", "知识文章"};
    List<Fragment> fragments;
    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_pao);
        initView();

    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new SquareFragment());

        //tablayout横向滚动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化ViewPager
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(vp);

    }

    /**
     * viewpager的适配器
     */
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //写出getPagertitle方法
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
