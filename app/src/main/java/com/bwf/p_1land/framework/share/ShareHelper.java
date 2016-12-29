package com.bwf.p_1land.framework.share;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ShareHelper  extends MySharedPreference{
    private static ShareHelper helper;
    private ShareHelper(Context context){
        super(context);

    }
    public static ShareHelper getInstance(Context context){
        if(helper==null){
            helper=new ShareHelper(context);
        }
        return helper;
    }
    public void setFirst(String value){
        putString("isfirst",value);
    }
    public boolean getFirst() {
        return getBoolean("isfirst", true);
    }
}
