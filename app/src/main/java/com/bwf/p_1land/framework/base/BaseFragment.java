package com.bwf.p_1land.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(getLayout(),null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        beforInit();
        init();
        dataInit();
        afterInit();
    }
    public abstract int getLayout();//返回layoutId
    public abstract void beforInit();
    public abstract void init();
    public abstract void dataInit();
    public abstract void afterInit();
    public <T extends View>T findViewByIdNoCast(int id){
        return view==null?null:(T) view.findViewById(id);
    }
    public void setOnclick(View...views){
        for (View view:views){
            view.setOnClickListener(this);
        }
    }
    public void setOnclick(int...ids){
        for (int id:ids){
            view.findViewById(id).setOnClickListener(this);
        }
    }
}
