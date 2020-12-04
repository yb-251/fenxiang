package com.umeng.soexample.interfaces.tongpao;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.TrandsData;

public interface IPersonalMeans {
    interface View extends IBaseView {
        void getPersonalMeansReturn(PersonalMeansData result);
        void getTrendsReturn(TrandsData result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getPersonalMeansData();
        void getTrendsData();

    }

    interface Model extends IModel {
        void loadPersonalMeansData(CallBack callBack);
        void loadTrendsData(CallBack callBack);
    }
}
