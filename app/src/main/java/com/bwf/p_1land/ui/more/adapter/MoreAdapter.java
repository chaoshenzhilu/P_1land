package com.bwf.p_1land.ui.more.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bwf.p_1land.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */

public class MoreAdapter extends BaseAdapter {
    public MoreAdapter(Context context, boolean isSeconde) {
        inflater=LayoutInflater.from(context);
        this.isSeconde=isSeconde;
    }
    private boolean isSeconde;
    private List<String> list;
    private LayoutInflater inflater;
    private int isSelect,firstcheck;
    private int isChangeRl;
    private boolean isMore;

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsChangeRl(int isChangeRl) {
        this.isChangeRl = isChangeRl;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public void setFirstcheck(int firstcheck) {
        this.firstcheck = firstcheck;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.moreitem,null);
            holder.rl= (RelativeLayout) view.findViewById(R.id.moreitem_rl);
            holder.cv= (CheckedTextView) view.findViewById(R.id.moreitem_tv);
            holder.iv= (ImageView) view.findViewById(R.id.moreitem_iv);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        if(!isSeconde){
            if(isChangeRl==i){
                holder.rl.setBackgroundColor(Color.parseColor("#EEEEEE"));
                holder.cv.setChecked(true);
            }else {
                holder.rl.setBackgroundColor(Color.WHITE);
                holder.cv.setChecked(false);
            }
        }

        holder.cv.setText(list.get(i));
        if(isMore){
            holder.cv.setChecked(true);
            holder.iv.setVisibility(View.VISIBLE);
        }
        if(isSeconde){
            if(firstcheck==2){
                if(isSelect==0){
                    if(isSelect==i){
                        holder.iv.setVisibility(View.VISIBLE);
                        holder.cv.setChecked(true);
                    }else {
                        holder.iv.setVisibility(View.GONE);
                        holder.cv.setChecked(false);
                    }
                }else {
                    if(isSelect==i){
                        if (holder.cv.isChecked()){
                            holder.cv.setChecked(false);
                            holder.iv.setVisibility(View.GONE);
                        }else {
                            holder.cv.setChecked(true);
                            holder.iv.setVisibility(View.VISIBLE);
                        }

                    }
                    if(i==0){
                        holder.cv.setChecked(false);
                        holder.iv.setVisibility(View.GONE);
                    }
                }
            }else {
                if(i==0) {
                    holder.iv.setVisibility(View.VISIBLE);
                    holder.cv.setChecked(true);
                }
                if(isSelect==i){
                    holder.iv.setVisibility(View.VISIBLE);
                    holder.cv.setChecked(true);
                }else {
                    holder.iv.setVisibility(View.GONE);
                    holder.cv.setChecked(false);
                }
            }
        }
        return view;
    }
    class ViewHolder{
        RelativeLayout rl;
        CheckedTextView cv;
        ImageView iv;
    }
}
