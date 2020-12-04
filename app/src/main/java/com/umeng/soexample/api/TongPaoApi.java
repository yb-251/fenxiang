package com.umeng.soexample.api;

import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.RecommendBannerData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.module.data.SquareData;
import com.umeng.soexample.module.data.TrandsData;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TongPaoApi {

    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("home/recommend.json")
    Flowable<RecommendData> getRecommend();

    @GET("home/banner.json")
    Flowable<RecommendBannerData> getRecommendBanner();

    @GET("home/topic_discussed.json")
    Flowable<HotIssueData> getHotIssueData();

    @GET("home/hot_user.json")
    Flowable<HotUserData> getHotUserData();

    @GET("home/square.json")
    Flowable<SquareData> getSquareData();

    @GET("home/personal.json")
    Flowable<PersonalMeansData> getPersonalMeansData();

    @GET("home/personal_activity.json")
    Flowable<TrandsData> getTrandsData();
}
