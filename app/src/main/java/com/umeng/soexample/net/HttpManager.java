package com.umeng.soexample.net;

import com.umeng.soexample.api.TongPaoApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instance;

    public  static HttpManager getInstance(){
        if(instance == null){
            synchronized(HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private TongPaoApi tongpaoApi;  //同袍

    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
        }
    }

    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization","APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * TongpaoApi
     * @return
     */
    public TongPaoApi getTongPaoApi(){
        if(tongpaoApi ==  null){
            tongpaoApi = getRetrofit(TongPaoApi.BASE_URL).create(TongPaoApi.class);
        }
        return tongpaoApi;
    }

}
