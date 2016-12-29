package com.bwf.p_1land.ui.sale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class ThumAdapter extends RecyclerView.Adapter<ThumAdapter.ViewHolder>{
    public ThumAdapter(Context context, ImageLoader loader, List<String> list){
        this.context=context;
        this.loader=loader;
        this.list=list;
    }
    private Context context;
    private ImageLoader loader;
    private List<String> list;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.thumitem2,null);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.imageView= (ImageView) view.findViewById(R.id.img_photo );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageResource(R.mipmap.defult_twopic);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.back(list.get(position));
            }
        });
        if(list.get(position)!= null){
            loader.displayImg(list.get(position),holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0: list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    private AdapterCallBack callBack;
    public interface AdapterCallBack{
        void back(String uri);
    }
    public void setCallBack(AdapterCallBack callBack) {
        this.callBack = callBack;
    }
}
