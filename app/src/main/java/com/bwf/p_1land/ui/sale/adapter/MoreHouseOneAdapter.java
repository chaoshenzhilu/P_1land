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
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.morehouseoneclass.MoreResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MoreHouseOneAdapter extends BaseAdapter {
    public MoreHouseOneAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader();
    }

    private List<MoreResult> list;
    private LayoutInflater inflater;
    private ImageLoader loader;

    public void setList(List<MoreResult> list) {
        this.list = list;
    }

    public void addList(List<MoreResult> list2) {
        this.list.addAll(list2);
    }

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
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.sale_item2, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.sale_twotv1);
            holder.tv2 = (TextView) view.findViewById(R.id.sale_twotv2);
            holder.tv3 = (TextView) view.findViewById(R.id.sale_twotv3);
            holder.tv4 = (TextView) view.findViewById(R.id.sale_twotv4);
            holder.iv = (ImageView) view.findViewById(R.id.sale_twoiv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MoreResult result =list.get(i);
        holder.tv1.setText(result.resblockOneName);
        holder.tv2.setText(result.resblockType);
        holder.tv3.setText(result.bedroomAmount+"室"+result.parlorAmount + "厅 "
                +result.buildSize+"㎡ " +result.unitprBegin + "-" + result.unitprEnd + "万/㎡");
        holder.tv4.setText(getint(result.totalprBegin)+"-"+getint(result.totalprEnd)+"万");
        holder.iv.setImageResource(R.mipmap.defult_onepic);
        loader.displayImg(result.titlepicImg, holder.iv);
        LogUtils.e(result.toString());
        return view;
    }
    class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        ImageView iv;
    }
    public String getint(String totalPrices) {
        int x = (int) Math.round(Double.valueOf(totalPrices));
        return x + "";
    }
}
