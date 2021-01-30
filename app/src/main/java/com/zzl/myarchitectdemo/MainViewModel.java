package com.zzl.myarchitectdemo;

import android.app.Application;

import com.zzl.baselibrary.AbsViewData;
import com.zzl.baselibrary.viewmodel.AbsViewMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 */
public class MainViewModel extends AbsViewMode<MainViewModel.MainViewData> {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected MainViewData createViewData() {
        return null;
    }

    @Override
    protected void parseBody(ResponseBody bytes) {

    }

    public static class MainViewData extends AbsViewData{
        MutableLiveData mLiveData = new MutableLiveData<List<MainMode>>();
    }
}
