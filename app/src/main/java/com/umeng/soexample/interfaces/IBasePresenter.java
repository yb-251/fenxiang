package com.umeng.soexample.interfaces;

public interface IBasePresenter<V extends IBaseView>{
    void attachView(V view);

    void unAttachView();
}
