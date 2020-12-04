package com.umeng.soexample.presenter;

import android.view.View;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.interfaces.tongpao.IRecommendDetails;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.module.tongPao.RecommendDetailsModel;

public class RecommendDetailsPresenter extends BasePresenter<IRecommendDetails.View> implements IRecommendDetails.Presenter {

    IRecommendDetails.View view;
    IRecommendDetails.Model model;

    public RecommendDetailsPresenter(IRecommendDetails.View view) {
        this.view = view;
        this.model = new RecommendDetailsModel();
    }

    @Override
    public void getRecommendDetails() {
        this.model.loadRecommendDetailsData(new CallBack() {
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
                    view.getRecommendDetailsReturn((RecommendData) o);
                }
            }
        });
    }



}
