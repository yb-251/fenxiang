package com.umeng.soexample.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.RecommendBannerData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.presenter.RecommendPresenter;
import com.umeng.soexample.ui.adapter.DiscussedAdapter;
import com.umeng.soexample.ui.adapter.HotUserAdapter;
import com.umeng.soexample.ui.adapter.RecommendAdapter;
import com.umeng.soexample.ui.recommendDetails.RecommendDetailsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter> implements IRecommend.View {

    private static final String TAG = "RecommendFragment";
    private DiscussedAdapter adapter;
    Banner banner;
    RecyclerView recycler_talk;
    RecyclerView recycler_recommend;
    RecyclerView recycler_hotUser;
    List<HotIssueData.DataBean> list;
    List<RecommendData.DataBean.PostDetailBean> recommendList;
    List<HotUserData.DataBean> dataBeans;
    List<Integer> bannerList;
    RecommendAdapter recommendAdapter;
    private HotUserAdapter hotUserAdapter;


    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        banner = getActivity().findViewById(R.id.banner);
        recycler_talk = getActivity().findViewById(R.id.recycler_talk);
        recycler_recommend = getActivity().findViewById(R.id.recycler_recommend);
        recycler_hotUser = getActivity().findViewById(R.id.recycler_hotUser);

        recycler_talk.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recycler_recommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_hotUser.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        recommendList = new ArrayList<>();
        recommendAdapter = new RecommendAdapter( recommendList,getActivity());

        recycler_recommend.setAdapter(recommendAdapter);

        dataBeans = new ArrayList<>();
        hotUserAdapter = new HotUserAdapter(dataBeans, getActivity());
        recycler_hotUser.setAdapter(hotUserAdapter);

        list = new ArrayList<>();
        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.aqiu1);
        bannerList.add(R.drawable.aqiu2);
        bannerList.add(R.drawable.aqiu3);
        bannerList.add(R.drawable.aqiu4);
        bannerList.add(R.drawable.aqiu5);

        //图片资源
        int[] imageResourceID = new int[]{R.drawable.aqiu1, R.drawable.aqiu2, R.drawable.aqiu3, R.drawable.aqiu4, R.drawable.aqiu5};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"图片1", "图片2", "图片3", "图片4", "图片5"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            banner.setBannerAnimation(Transformer.BackgroundToForeground);
            banner.setImages(imgeList);//设置图片资源
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            banner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            banner.start();//开始进行banner渲染

            adapter = new DiscussedAdapter(list, getActivity());

            recycler_talk.setAdapter(adapter);
            banner.setImages(bannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(R.drawable.aqiu1).into(imageView);
                    Glide.with(context).load(R.drawable.aqiu2).into(imageView);
                    Glide.with(context).load(R.drawable.aqiu3).into(imageView);
                    Glide.with(context).load(R.drawable.aqiu4).into(imageView);
                    Glide.with(context).load(R.drawable.aqiu5).into(imageView);
                }
            });
        }
        recommendAdapter.setOnItemClick(new RecommendAdapter.OnItemClick() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(getActivity(), RecommendDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getRecommend();
        presenter.getRecommendBannerData();
        presenter.getHotIssueData();
        presenter.getHotUser();
    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    public void getRecommendReturn(RecommendData result) {
        recommendList.clear();
        recommendList.add(result.getData().getPostDetail());
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendBannerDataReturn(RecommendBannerData bannerBean) {

    }

    @Override
    public void getHotIssueDataReturn(HotIssueData hotIssueData) {
        list.clear();
        list.addAll(hotIssueData.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getHotUserReturn(HotUserData hotUserData) {
        dataBeans.clear();
        dataBeans.addAll(hotUserData.getData());
        hotUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
}
