package com.umeng.soexample.presenter;

import android.view.View;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IPersonalMeans;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.TrandsData;
import com.umeng.soexample.module.tongPao.PersonalMeansModel;

public class PersonalMeansPresenter extends BasePresenter<IPersonalMeans.View> implements IPersonalMeans.Presenter{

    IPersonalMeans.View view;
    IPersonalMeans.Model model;

    public PersonalMeansPresenter(IPersonalMeans.View view) {
        this.view = view;
        this.model = new PersonalMeansModel();
    }

    @Override
    public void getPersonalMeansData() {
        model.loadPersonalMeansData(new CallBack() {
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
                    view.getPersonalMeansReturn((PersonalMeansData) o);
                }
            }
        });
    }

    @Override
    public void getTrendsData() {
        model.loadTrendsData(new CallBack() {
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
                    view.getTrendsReturn((TrandsData) o);
                }
            }
        });
    }
}
