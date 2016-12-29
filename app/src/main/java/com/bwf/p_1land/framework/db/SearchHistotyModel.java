package com.bwf.p_1land.framework.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.bwf.p_1land.framework.base.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SearchHistotyModel extends BaseModel {
    public static final String TATBLENAME="search_history";
    private static Map<String,String> map;
    static {
        map.put(_ID,"integer primary key autoincrement");
        map.put("content","text not null");
        map.put("time","text not null");
    }

    @Override
    protected  String getTableName() {
        return TATBLENAME;
    }

    @Override
    protected Map<String, String> getMap() {
        return map;
    }
    //插入一天历史记录
    public void insertHistory(History history){
        if(history==null){
            return;
        }
        ContentValues values=new ContentValues();
        values.put("content",history.content);
        values.put("time",history.time);
        if(getCount()==6){
            String sql="select from "+TATBLENAME+" order by time asc";
            String id="";
            Cursor cursor=select(sql);
            while(cursor.moveToNext()){
                id=cursor.getString(cursor.getColumnIndex(_ID));
            }
            delete(TATBLENAME,_ID+"=?",new String[]{id});
            insert(TATBLENAME,values);
        }
    }
    //清空历史记录
    public void clearAll(){
        clearTable(TATBLENAME);
    }
    //查找所有历史记录
    public List<History> getAllhistory(){
        List<History> list=new ArrayList<History>();
        String sql="select from "+TATBLENAME+" order by time desc";
        Cursor cursor=select(sql);
        while(cursor.moveToNext()){
            History history=new History();
            history.content=cursor.getString(cursor.getColumnIndex("content"));
            history.time=cursor.getString(cursor.getColumnIndex("time"));
            list.add(history);
        }
        return list;
    }
    //查找是否存在该历史记录
    public boolean isExitContent(String content){
        String sql="select from "+TATBLENAME+" where content=? '"+content+"'";
        Cursor cursor=select(sql);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }
}
