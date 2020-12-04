package com.umeng.soexample.module.tongPao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.SquareData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class SquareModel extends BaseModel implements ISquare.Model {
    @Override
    public void getSquareData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi()
                .getSquareData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SquareData>(callBack) {
                    @Override
                    public void onNext(SquareData squareData) {
                        callBack.success(squareData);
                    }
                })
        );
    }
}
