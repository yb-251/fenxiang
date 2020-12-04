package com.umeng.soexample.module.tongPao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.RecommendBannerData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class RecommendModel extends BaseModel implements IRecommend.Model {

    @Override
    public void loadRecommend(CallBack callBack) {
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

    @Override
    public void getRecommendBannerData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi()
                        .getRecommendBanner()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendBannerData>(callBack) {
                            @Override
                            public void onNext(RecommendBannerData bannerBean) {
                                callBack.success(bannerBean);
                            }
                        })
        );
    }

    @Override
    public void getHotIssueData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi()
                        .getHotIssueData()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<HotIssueData>(callBack) {
                            @Override
                            public void onNext(HotIssueData hotIssueData) {
                                callBack.success(hotIssueData);
                            }
                        })
        );
    }

    @Override
    public void getHotUser(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getTongPaoApi().getHotUserData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotUserData>(callBack) {
                    @Override
                    public void onNext(HotUserData hotUserData) {
                        callBack.success(hotUserData);
                    }
                })
        );
    }
}
