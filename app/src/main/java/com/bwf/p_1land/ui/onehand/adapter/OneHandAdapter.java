package com.bwf.p_1land.ui.onehand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.onehand.OneResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class OneHandAdapter extends BaseAdapter {
    public OneHandAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader();
    }

    private List<OneResult> list;
    private LayoutInflater inflater;
    private ImageLoader loader;

    public void setList(List<OneResult> list) {
        this.list = list;
    }

    public void addList(List<OneResult> list2) {
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
            view = inflater.inflate(R.layout.onehand_item, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.onehand_itemtv1);
            holder.tv2 = (TextView) view.findViewById(R.id.onehand_itemtv2);
            holder.tv3 = (TextView) view.findViewById(R.id.onehand_itemtv3);
            holder.tv4 = (TextView) view.findViewById(R.id.onehand_itemtv4);
            holder.iv = (ImageView) view.findViewById(R.id.onehand_itemiv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
            OneResult oneResult = (OneResult) list.get(i);
            holder.tv1.setText(oneResult.resblockOneName);
            holder.tv2.setText(oneResult.resblockType);
            holder.tv3.setText(oneResult.apartmentBegin+"-"+oneResult.apartmentEnd + "居 "
                    + oneResult.unitprBegin + "-" + oneResult.unitprEnd + "万/㎡ "+oneResult.areaBegin+"-"+oneResult.areaEnd+"㎡");
            holder.tv4.setText(getint(oneResult.totalprBegin)+"-"+getint(oneResult.totalprEnd));
            holder.iv.setImageResource(R.mipmap.defult_onepic);
            loader.displayImg(oneResult.imgUrl, holder.iv);
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
