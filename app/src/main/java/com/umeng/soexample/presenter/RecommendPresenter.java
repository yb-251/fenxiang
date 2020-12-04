package com.umeng.soexample.presenter;


import android.view.View;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.RecommendBannerData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.module.tongPao.RecommendModel;

public class RecommendPresenter extends BasePresenter<IRecommend.View> implements IRecommend.Presenter {

    IRecommend.View view;
    IRecommend.Model model;

    public RecommendPresenter(IRecommend.View view) {
        this.view = view;
        this.model = new RecommendModel();
    }

    @Override
    public void getRecommend() {
        this.model.loadRecommend(new CallBack() {
            @Override
            public void fail(String msg) {
                if (view != null) {
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (view != null) {
                    view.getRecommendReturn((RecommendData) o);
                }
            }
        });
    }

    @Override
    public void getRecommendBannerData() {
        this.model.getRecommendBannerData(new CallBack() {
            @Override
            public void fail(String msg) {
                if (view != null) {
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (view != null) {
                    view.loading(View.GONE);
                    view.getRecommendBannerDataReturn((RecommendBannerData) o);
                }
            }
        });
    }

    @Override
    public void getHotIssueData() {
        model.getHotIssueData(new CallBack() {
            @Override
            public void fail(String msg) {
                if (view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (view != null){
                    view.loading(View.GONE);
                    view.getHotIssueDataReturn((HotIssueData) o);
                }
            }
        });
    }

    @Override
    public void getHotUser() {
        this.model.getHotUser(new CallBack() {
            @Override
            public void fail(String msg) {
                if (view != null) {
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (view != null){
                    view.getHotUserReturn((HotUserData) o);
                }
            }
        });
    }

}
