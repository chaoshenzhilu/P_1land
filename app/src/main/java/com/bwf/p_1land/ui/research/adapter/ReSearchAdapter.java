package com.bwf.p_1land.ui.research.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.searchclass.SearchChild;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ReSearchAdapter extends BaseAdapter {
    public ReSearchAdapter(Context context) {
        this.context = context;
        loader=new ImageLoader();
    }
    private ImageLoader loader;
    private Context context;
    private List<SearchChild> list;

    public void setList(List<SearchChild> list) {
        this.list = list;
    }

    public void addList(List<SearchChild> moreList) {
        this.list.addAll(moreList);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.search_item, null);
            holder.tv1= (TextView) view.findViewById(R.id.seacher_itemtv1);
            holder.iv= (ImageView) view.findViewById(R.id.seacher_itemiv);
            holder.tv2 = (TextView) view.findViewById(R.id.search_itemtv2);
            holder.tv3 = (TextView) view.findViewById(R.id.search_itemtv3);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SearchChild child =list.get(i);
        if(i==0){
            holder.tv1.setText(child.title);
            holder.iv.setImageResource(R.mipmap.defult_onepic);
            loader.displayImg(child.showImgPath,holder.iv);
            holder.tv1.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.VISIBLE);
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);
        }else {
            holder.tv2.setText(child.title);
            holder.tv3.setText(child.describe);
            holder.tv2.setVisibility(View.VISIBLE);
            holder.tv3.setVisibility(View.VISIBLE);
            holder.tv1.setVisibility(View.GONE);
            holder.iv.setVisibility(View.GONE);
        }
        return view;
    }

    public class ViewHolder {
        TextView tv1, tv2,tv3;
        ImageView iv;
    }
}
