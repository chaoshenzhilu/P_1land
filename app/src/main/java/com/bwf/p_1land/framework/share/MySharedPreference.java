package com.bwf.p_1land.framework.share;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/11/28.
 */

public class MySharedPreference {
    private SharedPreferences sp;
    private static final String SHARE_NAME="landZ";
    public MySharedPreference(Context context){
        sp=context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
    }
    public void putString(String key,String value){

        sp.edit().putString(key,value).commit();
    }
    public String getString(String key){

        return sp.getString(key,"");
    }
    public String getString(String key,String defaultvalue){
        return sp.getString(key,defaultvalue);
    }
    public void putBoolean(String key,boolean value){
        sp.edit().putBoolean(key,value).commit();
    }
    public boolean getBoolean(String key){
        return sp.getBoolean(key,true);
    }
    public boolean getBoolean(String key,boolean value){
       return sp.getBoolean(key,value);
    }

}
