package com.bwf.p_1land.framework.tools;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ActivityManager {
    private static Stack<Activity> stack;
    private static ActivityManager manager;
    private ActivityManager(){
        if(stack==null){
            stack=new Stack<Activity>();
        }
    }
    public static ActivityManager getInstance(){
        if(manager==null){
            manager=new ActivityManager();
        }
        return manager;
    }
    public void addActivity(Activity activity){//添加activity
        if(stack==null){
            stack=new Stack<Activity>();
        }
        stack.add(activity);
    }
    public void removeActicity(Activity activity){//移除activity
        if(activity!=null&&stack!=null){
            stack.remove(activity);
        }
    }
    public void killActicity(Activity activity){//关闭指定activity
        if(activity!=null&&stack!=null){
            stack.remove(activity);
        }
        activity.finish();
    }
    public void killAllActicity(){//关闭所有activity
        if(stack==null){
           return;
        }
        for(int i=0;i<stack.size();i++){
            if(stack.get(i)!=null){
                stack.get(i).finish();
            }
        }
        stack.clear();
    }
    public void killOtherActicity(Activity activity){//关闭其他所有activity
        if(stack==null){
            return;
        }
        for(int i=0;i<stack.size();i++){
            if(stack.get(i)!=activity){
                stack.get(i).finish();
            }
        }
        stack.clear();
        stack.add(activity);
    }
    public boolean isExit(String className){//判断是否存在
        Activity activity=null;
        activity=getActivityFromName(className);
        if(activity!=null&&!activity.isFinishing()){
            return true;
        }
        return false;
    }
    public Activity getActivityFromName(String className){//通过名字查找Activity
        Activity activity=null;
        for(int i=0;i<stack.size();i++){
            if(stack.get(i).getClass().getName().equals(className)){
                activity=stack.get(i);
            }
        }
        return activity;
    }
}
