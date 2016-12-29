package com.bwf.p_1land.framework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bwf.p_1land.MyApplication;
import com.bwf.p_1land.framework.base.BaseModel;
import com.bwf.p_1land.framework.tools.Constants;

import static android.R.attr.version;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SqlHelper extends SQLiteOpenHelper {
    private static SqlHelper helper;
    private SqlHelper(Context context) {
        super(context, Constants.DB_NAME, null, version);
    }
    public static SqlHelper getIstance(){
        if(helper==null){
            helper=new SqlHelper(MyApplication.getApplication().getApplicationContext());
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for(int i=0;i<Constants.TABLES.length;i++){
            BaseModel baseModel=Constants.TABLES[i];
            sqLiteDatabase.execSQL(baseModel.getCreateTable());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
