package com.bwf.p_1land.ui.research.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReSearchPopAdapter extends BaseAdapter {
    public ReSearchPopAdapter(Context context){
        this.context=context;
    }
    private Context context;
    private List<OnlineResult> list;

    public void setList(List<OnlineResult> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list==null?null:list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.popupitem,null);
        TextView tv= (TextView) view.findViewById(R.id.online_itemtv);
        ImageView iv= (ImageView) view.findViewById(R.id.online_itemiv);
        RelativeLayout rl= (RelativeLayout) view.findViewById(R.id.online_itemrl);
        OnlineResult result=list.get(i);
        tv.setTextSize(16);
        tv.setText(result.name);
        if(result.isSelect){
            iv.setVisibility(View.VISIBLE);
            rl.setBackgroundColor(Color.WHITE);
        }else {
            iv.setVisibility(View.GONE);
            rl.setBackgroundColor(Color.parseColor("#4a2450"));
        }
        return view;
    }
}
