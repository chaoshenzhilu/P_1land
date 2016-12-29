package com.bwf.p_1land.ui.sale.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.p_1land.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class TextViewAdapter extends BaseAdapter {
    public TextViewAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
    }
    private Context context;
    private List<String> list;
    @Override
    public int getCount() {
        return list==null?null:list.size();
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
        view=View.inflate(context, R.layout.thumitem,null);
        TextView tv= (TextView) view.findViewById(R.id.thumbnail_itemtv);
        tv.setTextSize(22);
        tv.setText(list.get(i));
        return view;
    }
}
