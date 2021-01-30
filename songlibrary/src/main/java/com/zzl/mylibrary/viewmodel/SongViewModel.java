package com.zzl.mylibrary.viewmodel;

import android.app.Application;

import com.zzl.baselibrary.AbsViewData;
import com.zzl.baselibrary.viewmodel.AbsViewMode;
import com.zzl.mylibrary.mode.SongMode;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 *
 */
public class SongViewModel extends AbsViewMode<SongViewModel.SongViewData> {

    public SongViewModel(@NonNull Application application) {
        super(application);
    }
    public void requesData(){
        getData().mLiveData.postValue(new SongMode());
    }

    public static class SongViewData extends AbsViewData{
        public MutableLiveData<SongMode> mLiveData = new MutableLiveData();
    }

    @Override
    protected SongViewData createViewData() {
        return new SongViewData();
    }

    @Override
    protected void parseBody(ResponseBody bytes) {

    }
}
