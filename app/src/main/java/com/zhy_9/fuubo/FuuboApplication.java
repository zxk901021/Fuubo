package com.zhy_9.fuubo;

import android.app.Application;

/**
 * Created by ZHY_9 on 2015/5/15.
 */
public class FuuboApplication extends Application{

    private int resId;

    public FuuboApplication(int resId) {
        this.resId = resId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setActivityTheme(resId);
    }

    public void setActivityTheme(int resId){
        setTheme(resId);
    }
}
