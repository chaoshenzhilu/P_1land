package com.bwf.p_1land.ui.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.ShowArrBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */

public class GuWenAdapter extends BaseAdapter {
    public GuWenAdapter(List<ShowArrBean> list, Context context,ImageLoader loader) {
        this.list = list;
        this.context = context;
        this.loader=loader;
    }
    private ImageLoader loader;

    private List<ShowArrBean> list;
    private Context context;

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gunwenitem, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        ShowArrBean bean=list.get(i);
        holder.gunwenName.setText(bean.createName);
        holder.guwenAge.setText("从业"+bean.inductionDate);
        holder.guwenCount.setText(bean.totalShowing);
        loader.displayImg(bean.photo,holder.guwenPhoto);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.guwen_photo)
        ImageView guwenPhoto;
        @Bind(R.id.gunwen_name)
        TextView gunwenName;
        @Bind(R.id.guwen_age)
        TextView guwenAge;
        @Bind(R.id.guwen_count)
        TextView guwenCount;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
