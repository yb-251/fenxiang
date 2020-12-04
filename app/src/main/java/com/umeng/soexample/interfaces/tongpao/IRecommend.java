package com.umeng.soexample.interfaces.tongpao;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.RecommendBannerData;
import com.umeng.soexample.module.data.RecommendData;

public interface IRecommend {

    interface View extends IBaseView{

        void getRecommendReturn(RecommendData result);
        void getRecommendBannerDataReturn(RecommendBannerData bannerBean);
        void getHotIssueDataReturn(HotIssueData hotIssueData);
        void getHotUserReturn(HotUserData hotUserData);
    }

    interface Presenter extends IBasePresenter<View>{

        void getRecommend();
        void getRecommendBannerData();
        void getHotIssueData();
        void getHotUser();

    }

    interface Model extends IModel{
        void loadRecommend(CallBack callBack);
        void getRecommendBannerData(CallBack callBack);
        void getHotIssueData(CallBack callBack);
        void getHotUser(CallBack callBack);
    }

}
