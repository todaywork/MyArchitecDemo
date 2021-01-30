package com.zzl.playlibrary.viewmodel;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.zzl.baselibrary.AbsViewData;
import com.zzl.baselibrary.viewmodel.AbsViewMode;
import com.zzl.playlibrary.mode.LyricModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;

/**
 * Created by zhenglin.zhu on 2020/11/26.
 */
public class PlayViewModel extends AbsViewMode<PlayViewModel.PlayViewData> {
    private static final String TAG = PlayViewModel.class.getSimpleName();
    public PlayViewModel(@NonNull Application application) {
        super(application);
    }
    public void requestData(){
        StringBuffer buffer=null;
        try {
            InputStream inputStream = mApplication.getAssets().open("mylyric");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            buffer = new StringBuffer();
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "requestData buffer="+buffer);
        if (buffer!=null){
            Gson gson = new Gson();
            LyricModel lyricModel = gson.fromJson(buffer.toString(), LyricModel.class);
            getData().mLiveData.postValue(lyricModel);
        }
    }

    @Override
    protected PlayViewData createViewData() {
        return new PlayViewData();
    }


    //mutablelivedata 数据用类封装，目的是公用一个基类，方便抽取
    public static class PlayViewData extends AbsViewData{
        public MutableLiveData<LyricModel> mLiveData = new MutableLiveData();
    }

    @Override
    protected void parseBody(ResponseBody bytes) {

    }
}
