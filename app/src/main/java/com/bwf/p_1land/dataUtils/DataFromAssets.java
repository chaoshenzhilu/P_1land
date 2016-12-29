package com.bwf.p_1land.dataUtils;

import android.content.Context;
import android.content.res.AssetManager;

import com.bwf.p_1land.MyApplication;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResultData;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/30.
 */

public class DataFromAssets {
    public static void getGsonData(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager manager = context.getAssets();
                try {
                    InputStream in = manager.open("study_type.txt");
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    String result = new String(bytes, "utf-8");

                    OnlineResultData onlineResultData = new Gson().fromJson(result, OnlineResultData.class);
//                    LogUtils.e("msg",onlineResultData.toString());
                    MyApplication.getApplication().setOnlineResultData(onlineResultData);
//                    LogUtils.e("msg",onlineResultData.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
