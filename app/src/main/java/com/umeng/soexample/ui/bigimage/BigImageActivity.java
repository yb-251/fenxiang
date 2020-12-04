package com.umeng.soexample.ui.bigimage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;

public class BigImageActivity extends BaseActivity {

    List<String> urls; //当前需要查看的所有图片的路径
    int currentPos; //当前操作的图片的位置
    TextView txtDown;
    ViewPager viewPager;


    @Override
    protected int getLayout() {
        return R.layout.activity_bigimage;

    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
    }

    class MVP extends PagerAdapter {
        Context mContext;
        List<String> mItems;

        public MVP(Context mContext, List<String> mItems) {
            this.mContext = mContext;
            this.mItems = mItems;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            PhotoView imageView = new PhotoView(mContext);
            container.addView(imageView);
            Glide.with(mContext).load(mItems.get(position)).into(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //data存放图片数据和当前操作下标
        if (intent != null && intent.hasExtra("data")) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                urls = bundle.getStringArrayList("urls");
                currentPos = bundle.getInt("postion");
                ImagePreview
                        .getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                        .setContext(this)
                        // 设置从第几张开始看（索引从0开始）
                        .setIndex(currentPos)
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List
                        //.setImageInfoList(imageInfoList)
                        // 2：直接传url List
                        .setImageList(urls)
                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage(String image)
                        // 开启预览
                        .start();
            }

        }
    }





    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
}
