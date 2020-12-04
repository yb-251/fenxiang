package com.umeng.soexample.module.tongPao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IRecommendDetails;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class RecommendDetailsModel extends BaseModel implements IRecommendDetails.Model {


    @Override
    public void loadRecommendDetailsData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi().getRecommend()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendData>(callBack) {
                            @Override
                            public void onNext(RecommendData recommendData) {
                                callBack.success(recommendData);
                            }
                        })
        );
    }

}
