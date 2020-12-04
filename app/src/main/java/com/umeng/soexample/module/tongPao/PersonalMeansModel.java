package com.umeng.soexample.module.tongPao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IPersonalMeans;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.TrandsData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class PersonalMeansModel extends BaseModel implements IPersonalMeans.Model{
    @Override
    public void loadPersonalMeansData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi()
                        .getPersonalMeansData()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<PersonalMeansData>(callBack) {
                            @Override
                            public void onNext(PersonalMeansData personalMeansData) {
                                callBack.success(personalMeansData);
                            }
                        })
        );
    }

    @Override
    public void loadTrendsData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi()
                        .getTrandsData()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TrandsData>(callBack) {
                            @Override
                            public void onNext(TrandsData personalMeansData) {
                                callBack.success(personalMeansData);
                            }
                        })
        );
    }
}
