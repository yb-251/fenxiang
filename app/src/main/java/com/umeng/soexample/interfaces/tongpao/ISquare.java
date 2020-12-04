package com.umeng.soexample.interfaces.tongpao;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.module.data.SquareData;

public interface ISquare {

    interface View extends IBaseView{
        void getSquareReturn(SquareData result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getSquareData();
    }

    interface Model extends IModel{
        void getSquareData(CallBack callBack);
    }

}
