package com.umeng.soexample.ui.fragment;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.SquareData;
import com.umeng.soexample.presenter.SquarePresenter;
import com.umeng.soexample.ui.TongPaoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends BaseFragment<SquarePresenter> implements ISquare.View, View.OnClickListener {


    private TextView tv_all;
    private TextView tv_nearby;
    private TextView tv_ghora;
    private ViewPager square_vp;
    List<Fragment> fragments;

    public SquareFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_square;
    }

    @Override
    protected void initView() {
        tv_all = getActivity().findViewById(R.id.tv_all);
        tv_ghora = getActivity().findViewById(R.id.tv_ghora);
        tv_nearby = getActivity().findViewById(R.id.tv_nearby);
        square_vp = getActivity().findViewById(R.id.square_vp);

        tv_all.setBackgroundResource(R.drawable.selector_tv);
        tv_all.setTextColor(Color.WHITE);
        fragments = new ArrayList<>();

        tv_all.setOnClickListener(this);
        tv_ghora.setOnClickListener(this);
        tv_nearby.setOnClickListener(this);
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
            return fragments.get( position );
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void initData() {
        presenter.getSquareData();
    }

    @Override
    protected SquarePresenter createPresenter() {
        return new SquarePresenter(this);
    }

    @Override
    public void getSquareReturn(SquareData result) {

    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_all:
                fragments.add(new RecommendFragment());
                square_vp.setAdapter(new MyAdapter(getFragmentManager()));
                tv_all.setBackgroundResource(R.drawable.selector_tv);
                tv_all.setTextColor(Color.WHITE);
                tv_nearby.setBackgroundResource(R.drawable.selector_btn);
                tv_nearby.setTextColor(Color.BLACK);
                tv_ghora.setBackgroundResource(R.drawable.selector_btn);
                tv_ghora.setTextColor(Color.BLACK);
                break;
            case R.id.tv_nearby:
                tv_nearby.setBackgroundResource(R.drawable.selector_tv);
                tv_nearby.setTextColor(Color.WHITE);
                tv_all.setBackgroundResource(R.drawable.selector_btn);
                tv_all.setTextColor(Color.BLACK);
                tv_ghora.setBackgroundResource(R.drawable.selector_btn);
                tv_ghora.setTextColor(Color.BLACK);
                break;
            case R.id.tv_ghora:
                tv_ghora.setBackgroundResource(R.drawable.selector_tv);
                tv_ghora.setTextColor(Color.WHITE);
                tv_all.setBackgroundResource(R.drawable.selector_btn);
                tv_all.setTextColor(Color.BLACK);
                tv_nearby.setBackgroundResource(R.drawable.selector_btn);
                tv_nearby.setTextColor(Color.BLACK);
                break;
        }
    }
}
