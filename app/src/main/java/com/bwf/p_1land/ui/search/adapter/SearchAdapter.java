package com.bwf.p_1land.ui.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.requestclass.researchclass.ResearchResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */

public class SearchAdapter extends BaseAdapter{
    public SearchAdapter(Context context){
        this.context=context;
    }
    private Context context;
    private List<ResearchResult> list;

    public void setList(List<ResearchResult> list) {
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
        view= LayoutInflater.from(context).inflate(R.layout.researchitem,null);
        TextView tv= (TextView) view.findViewById(R.id.research_itemtv);
        tv.setText(list.get(i).name);
        return view;
    }
}
