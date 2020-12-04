package com.umeng.soexample.ui.recommendDetails;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.tongpao.IRecommendDetails;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.presenter.RecommendDetailsPresenter;
import com.umeng.soexample.ui.TongPaoActivity;
import com.umeng.soexample.ui.adapter.RecommendAdapter;
import com.umeng.soexample.ui.adapter.RecommendPersonalAdapter;
import com.umeng.soexample.ui.fragment.AllFragment;
import com.umeng.soexample.ui.fragment.PersonalMeansFragment;
import com.umeng.soexample.ui.fragment.RecommendFragment;
import com.umeng.soexample.ui.fragment.SquareFragment;
import com.umeng.soexample.ui.fragment.TrendsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class RecommendPersonalActivity extends BaseActivity<RecommendDetailsPresenter> implements IRecommendDetails.View {

    private ImageView img_header;
    private AppBarLayout abl;
    private RecyclerView recyclerView;
    private List<RecommendData.DataBean.PostDetailBean> list;
    private RecommendPersonalAdapter adapter;
    private Toolbar toolbar;
    private CoordinatorLayout cdl;
    private ImageView img_tt;
    private TabLayout tab_personal;
    private ViewPager personal_vp;
    String[] tabs = {"资料", "动态", "活动", "社团", "文章"};
    List<Fragment> fragments;


    @Override
    protected int getLayout() {
        return R.layout.activity_recommend_personal;
    }

    @Override
    protected void initView() {
        recyclerView =findViewById(R.id.recyclerView);
        cdl = findViewById(R.id.cdl);
        img_tt = findViewById(R.id.img_tt);
        toolbar = findViewById(R.id.toolbar);
        tab_personal = findViewById(R.id.tab_personal);
        personal_vp = findViewById(R.id.personal_vp);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("  ");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        fragments = new ArrayList<>();

            fragments.add(new PersonalMeansFragment());
            fragments.add(new TrendsFragment());
            fragments.add(new Fragment());
            fragments.add(new Fragment());
            fragments.add(new Fragment());



        //tablayout横向滚动
        tab_personal.setTabMode(TabLayout.MODE_SCROLLABLE);

        //初始化ViewPager
        personal_vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tab_personal.setupWithViewPager(personal_vp);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);
        list = new ArrayList<>();
        adapter = new RecommendPersonalAdapter(list, this);
        recyclerView.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();// finish your activity
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected RecommendDetailsPresenter createPresenter() {
        return new RecommendDetailsPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.getRecommendDetails();
    }


    @Override
    public void getRecommendDetailsReturn(RecommendData result) {
        list.clear();
        list.add(result.getData().getPostDetail());
        Glide.with(this).load(result.getData().getPostDetail().getHeadUrl()).apply(bitmapTransform(new BlurTransformation(40))).into(img_tt);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }

}
