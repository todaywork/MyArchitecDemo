package com.zzl.baselibrary;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by zhenglin.zhu on 2020/11/22.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return getResources().getBoolean(R.bool.isDebug);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
