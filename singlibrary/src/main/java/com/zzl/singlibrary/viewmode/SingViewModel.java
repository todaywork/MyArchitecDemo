package com.zzl.singlibrary.viewmode;

import android.app.Application;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zzl.baselibrary.AbsViewData;
import com.zzl.baselibrary.network.RetrofitHelper;
import com.zzl.baselibrary.viewmodel.AbsViewMode;
import com.zzl.singlibrary.mode.SingMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by zhenglin.zhu on 2020/11/19.
 * 处理数据，且用mutable对数据封装。只专注于处理数据
 */
public class SingViewModel extends AbsViewMode<SingViewModel.SingViewData> {

    public SingViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 请求获取数据，拿到数据并通知更新
     */
    public void requestData(int parentId,int sort,int start,int length,int isPage) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", parentId);
        map.put("sort", sort);
        map.put("start", start);
        map.put("length", length);
        map.put("isPage", isPage);
        map.put("channel", "TLKG81");
        map.put("deviceOSType", "android");
        map.put("platform", "106");
        Call<ResponseBody> call = RetrofitHelper.getInstance().getApi().listData(map);
        call.enqueue(mCallback);
    }


    public static class SingViewData extends AbsViewData {
        public MutableLiveData<SingMode> mLiveData = new MutableLiveData<SingMode>();
    }

    protected SingViewData createViewData() {
        return new SingViewData();
    }

    @Override
    protected void parseBody(ResponseBody bytes) {
        try {
            String string = bytes.string();
            Gson gson = new Gson();
            SingMode singMode = gson.fromJson(string, SingMode.class);
            getData().mLiveData.postValue(singMode);
            Logger.d("parseBody=="+singMode.c.list+" string="+string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
