package com.umeng.soexample.interfaces.tongpao;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.RecommendData;

public interface IRecommendDetails {

    interface View extends IBaseView{
        void getRecommendDetailsReturn(RecommendData result);
    }

    interface Presenter extends IBasePresenter<View> {

        void getRecommendDetails();

    }

    interface Model extends IModel {
        void loadRecommendDetailsData(CallBack callBack);
    }
}
