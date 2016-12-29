package com.bwf.p_1land;

import android.app.Application;
import android.content.Context;

import com.bwf.p_1land.requestclass.assetsclass.OnlineResultData;

/**
 * Created by Administrator on 2016/11/28.
 */

public class MyApplication extends Application {
    private String str;
    private static MyApplication app;
    private OnlineResultData onlineResultData;
    @Override
    public void onCreate() {
        super.onCreate();
        str = "123";
        app = this;

    }

    public static MyApplication getApplication(){
        return app;
    }

    public static Context getAppContext(){
        return app.getApplicationContext();
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
    public void setOnlineResultData(OnlineResultData onlineResultData) {
        this.onlineResultData = onlineResultData;
    }

    public OnlineResultData getOnlineResultData() {
        return onlineResultData;
    }
}
