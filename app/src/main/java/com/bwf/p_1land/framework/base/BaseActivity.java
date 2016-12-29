package com.bwf.p_1land.framework.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.tools.ActivityManager;
import com.bwf.p_1land.framework.util.LogUtils;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    public Dialog dialog=null;
    private ActivityManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        manager=ActivityManager.getInstance();
        manager.addActivity(this);
        beforInitView();
        initView();
        initViewData();
        afterInitView();
    }

    /**
     * 获得布局
     * @return
     */
    public abstract int getContentViewId();
    /**
     * 初始化View之前
     */
    public abstract void beforInitView();
    /**
     * 初始化View
     */
    public abstract void initView();
    /**
     * 初始化View数据
     */
    public abstract void initViewData();
    /**
     * 初始化View之后
     */
    public abstract void afterInitView();
    /**
     * findViewById不用强制转换
     */
    public <T extends View>T findViewByIdNoCast(int id){
            return (T)findViewById(id);
    }
    /**
     * 设置需要的监听
     */
    public void setOnclick(View...views){
        for(View v:views){
            v.setOnClickListener(this);
        }
    }
    public void showDialog(){
        if(dialog==null){
            dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        }
        View view=View.inflate(this, R.layout.dialog_progress,null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        LogUtils.e("显示dialog");
    }
    public void dismissDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
