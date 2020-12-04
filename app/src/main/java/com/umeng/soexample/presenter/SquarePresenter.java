package com.umeng.soexample.presenter;

import android.view.View;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.SquareData;
import com.umeng.soexample.module.tongPao.SquareModel;

public class SquarePresenter extends BasePresenter<ISquare.View> implements ISquare.Presenter{

    ISquare.View view;
    ISquare.Model model;

    public SquarePresenter(ISquare.View view) {
        this.view = view;
        this.model = new SquareModel();
    }

    @Override
    public void getSquareData() {
        model.getSquareData(new CallBack() {
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
                    view.getSquareReturn((SquareData) o);
                }
            }
        });
    }
}
