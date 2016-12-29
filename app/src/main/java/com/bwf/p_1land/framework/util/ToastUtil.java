package com.bwf.p_1land.framework.util;

import android.widget.Toast;

import com.bwf.p_1land.MyApplication;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ToastUtil {
    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showToast(String info) {
        Toast.makeText(MyApplication.getAppContext(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showToastLong(String info) {
        Toast.makeText(MyApplication.getAppContext(), info, Toast.LENGTH_LONG).show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showToast(int resId) {
        Toast.makeText(MyApplication.getAppContext(), MyApplication.getAppContext().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showToastLong(int resId) {
        Toast.makeText(MyApplication.getAppContext(), resId, Toast.LENGTH_LONG).show();
    }

}