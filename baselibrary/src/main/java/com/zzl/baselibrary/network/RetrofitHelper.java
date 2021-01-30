package com.zzl.baselibrary.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zzl.baselibrary.constant.Constant.DEFAULT_TIMEOUT;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 */
public class RetrofitHelper {
    private static RetrofitHelper mRetrofitHelper;
    private Api mApi;
    private String mBaseUrl = "https://mam.tlkg.com.cn/";
    private Gson mGson = new GsonBuilder().setLenient().create(); //设置宽大处理畸形的json

    private RetrofitHelper() {
        super();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(client)
                .build();
        mApi = retrofit.create(Api.class);
    }
    public static RetrofitHelper getInstance(){
        if (mRetrofitHelper==null){
            synchronized (RetrofitHelper.class){
                if (mRetrofitHelper==null){
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }

    public void changeBaseUrl(String url) {
        mBaseUrl = url;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(client)
                .build();
        mApi = retrofit.create(Api.class);
        Logger.d("HitvRetrofit: "+mBaseUrl);
    }

    public Api getApi(){
        return mApi;
    }

}
