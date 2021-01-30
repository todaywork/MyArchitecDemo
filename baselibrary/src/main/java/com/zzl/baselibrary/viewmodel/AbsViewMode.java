package com.zzl.baselibrary.viewmodel;

import android.app.Application;

import com.orhanobut.logger.Logger;
import com.zzl.baselibrary.AbsViewData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhenglin.zhu on 2020/11/19.
 * 泛型：定义一个封装mutablelivedata的一个静态类作为泛型
 * 所有子viewmodel 都继承AbsViewMode 数据获取，以及用MultableLiveData对数据封装
 * 泛型由子类决定具体是什么类。所以拿到泛型
 *
 */
public abstract class AbsViewMode<VD extends AbsViewData> extends AndroidViewModel {
    protected VD viewData;
    protected Application mApplication;
    public AbsViewMode(@NonNull Application application) {
        super(application);
         viewData = createViewData();
        mApplication = application;
    }
    protected abstract VD createViewData();
    public VD getData(){
        return viewData;
    }
    public Callback mCallback = new Callback<ResponseBody>(){

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            ResponseBody body = response.body();
            if (body==null){
                Logger.d("onResponse: "+body);
                bodyIsNull();
                return;
            }
            try {
                Logger.d("onResponse: "+body);
                parseBody(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    };
    //子类去实现具体的解析逻辑
    protected abstract void parseBody(ResponseBody bytes);

    public void bodyIsNull(){}
}
