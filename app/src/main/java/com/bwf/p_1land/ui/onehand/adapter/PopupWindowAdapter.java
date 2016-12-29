package com.bwf.p_1land.ui.onehand.adapter;

import android.content.Context;
import android.graphics.Color;
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
 * Created by Administrator on 2016/12/1.
 */

public class PopupWindowAdapter extends BaseAdapter {
    private List<OnlineResult> resultList;
    private Context context;
    private boolean isMore;
    private boolean isSconde;
    public PopupWindowAdapter(Context context, boolean isSconde){
        this.context=context;
        this.isSconde = isSconde;
    }
    public PopupWindowAdapter(Context context, boolean isSconde, boolean isMore){
        this.context=context;
        this.isSconde = isSconde;
        this.isMore=isMore;
    }
    public void setResultList(List<OnlineResult> resultList) {
        this.resultList = resultList;
    }

    public List<OnlineResult> getResultList() {
        return resultList;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    @Override
    public int getCount() {
        return resultList==null?0:resultList.size();
    }

    @Override
    public Object getItem(int i) {
        return resultList==null?null:resultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            view=View.inflate(context, R.layout.popupitem,null);
            holder=new ViewHolder();
            holder.rl= (RelativeLayout) view.findViewById(R.id.online_itemrl);
            holder.tv= (TextView) view.findViewById(R.id.online_itemtv);
            holder.iv= (ImageView) view.findViewById(R.id.online_itemiv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        OnlineResult result=resultList.get(i);
        holder.tv.setText(result.name);
        if(isSconde){
            holder.tv.setText(result.name);
            holder.rl.setBackgroundColor(Color.parseColor("#EEEEEE"));
            if(result.isSelect){//选择
                holder.tv.setTextColor(Color.parseColor("#4a2450"));
                if(isMore){
                    holder.iv.setVisibility(View.VISIBLE);
                }
            }else{
                holder.tv.setTextColor(Color.BLACK);
                if(isMore){
                    holder.iv.setVisibility(View.GONE);
                }
            }
        }else {
            if(result.isSelect){
                holder.rl.setBackgroundColor(Color.parseColor("#EEEEEE"));
                holder.tv.setTextColor(Color.parseColor("#4a2450"));
            }else {
                holder.rl.setBackgroundColor(Color.WHITE);
                holder.tv.setTextColor(Color.BLACK);
            }

        }
        return view;
    }
    public class ViewHolder{
        RelativeLayout rl;
        TextView tv;
        ImageView iv;
    }
}
