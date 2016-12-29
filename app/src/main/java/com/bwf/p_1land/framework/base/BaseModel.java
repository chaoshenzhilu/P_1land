package com.bwf.p_1land.framework.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.bwf.p_1land.framework.db.SqlHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class BaseModel implements BaseColumns{
    private SqlHelper helper;
    public BaseModel(){
        helper=SqlHelper.getIstance();
    }
    public String getCreateTable(){
        return creatTable(getTableName(),getMap());
    }
    public String creatTable(String tableName, Map<String,String> map){
        StringBuffer sb=new StringBuffer();
        sb.append("create table ").append(tableName).append("(");
        for (Map.Entry<String,String> entry:map.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
        }
        sb.substring(sb.length()-1,sb.length());
        sb.append(")");
        return sb.toString();
    }
    //添加一个
    public void insert(String tableName, ContentValues values){
        helper.getWritableDatabase().insert(tableName,null,values);
    }
    //添加多个
    public void insertMore(String tableName, List<ContentValues> list){
        helper.getWritableDatabase().beginTransaction();
        for(int i=0;i<list.size();i++) {
            insert(tableName,list.get(i));
        }
        helper.getWritableDatabase().endTransaction();
    }
    //删除指定数据
    public void delete(String tableName,String where,String[] wheres){
        helper.getWritableDatabase().delete(tableName, where, wheres);
    }
    //查询
//    public Cursor select(String tableName){
//        return helper.getWritableDatabase().query(tableName,null,null,null,null,null,null);
//    }
    public Cursor select(String sql){
        return helper.getWritableDatabase().rawQuery(sql,null);
    }
    // 更新数据
    public void upData(String tableName,ContentValues values,String where,String[] wheres){
        helper.getWritableDatabase().update(tableName,values,where,wheres);
    }
    //清空表
    public void clearTable(String tableName){
        helper.getWritableDatabase().execSQL("delete from"+tableName);
    }
    //获取数据库的行数（也就是历史记录的条数）
    public long getCount(){
        String sql="select * from"+getTableName();
        SQLiteStatement statement=helper.getWritableDatabase().compileStatement(sql);
        return statement.simpleQueryForLong();
    }

    protected abstract String getTableName();
    protected abstract Map<String,String> getMap();
}
