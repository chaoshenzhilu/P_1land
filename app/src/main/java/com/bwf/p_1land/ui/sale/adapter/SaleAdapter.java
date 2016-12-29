package com.bwf.p_1land.ui.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.saleclass.HouseArrBean;
import com.bwf.p_1land.requestclass.saleclass.HouseOneArrBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class SaleAdapter extends BaseAdapter {
    public SaleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader();
        this.context=context;
    }
    private Context context;
    private List<Object> list;
    private LayoutInflater inflater;
    private int type_one = 0, type_two = 1;
    private ImageLoader loader;
    private boolean isShowYuan=false;
    private int temp=0;

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
    public void addList(List<Object> list2){
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

    public void setShowYuan(boolean showYuan) {
        isShowYuan = showYuan;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof HouseArrBean) {
            return type_one;
        } else {
            return type_two;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        final int type = getItemViewType(i);
        if (view == null) {
            if (type == type_one) {
                view = inflater.inflate(R.layout.sale_item1, null);
                holder = new ViewHolder();
                holder.tv1 = (TextView) view.findViewById(R.id.sale_onetv1);
                holder.tv2 = (TextView) view.findViewById(R.id.sale_onetv2);
                holder.tv3 = (TextView) view.findViewById(R.id.sale_onetv3);
                holder.tv4 = (TextView) view.findViewById(R.id.sale_onetv4);
                holder.tv5 = (TextView) view.findViewById(R.id.sale_onetv5);
                holder.tv6 = (TextView) view.findViewById(R.id.sale_onetv6);
                holder.tv7 = (TextView) view.findViewById(R.id.sale_onetv7);
                holder.iv = (ImageView) view.findViewById(R.id.sale_oneiv);
                holder.iv1 = (ImageView) view.findViewById(R.id.sale_yuan1);
                holder.rlone= (RelativeLayout) view.findViewById(R.id.sale_yuanrl);
                view.setTag(holder);
            } else {
                view = inflater.inflate(R.layout.sale_item2, null);
                holder2 = new ViewHolder2();
                holder2.tv1 = (TextView) view.findViewById(R.id.sale_twotv1);
                holder2.tv2 = (TextView) view.findViewById(R.id.sale_twotv2);
                holder2.tv3 = (TextView) view.findViewById(R.id.sale_twotv3);
                holder2.tv4 = (TextView) view.findViewById(R.id.sale_twotv4);
                holder2.iv = (ImageView) view.findViewById(R.id.sale_twoiv);
                holder2.iv2 = (ImageView) view.findViewById(R.id.sale_yuan2);
                holder2.rltwo= (RelativeLayout) view.findViewById(R.id.sale_yuanrl);
                view.setTag(holder2);
            }
        } else {
            if (type == type_one) {
                holder = (ViewHolder) view.getTag();
            } else if (type == type_two) {
                holder2 = (ViewHolder2) view.getTag();
            }
        }
        if (type == type_one) {
            final HouseArrBean bean = (HouseArrBean) list.get(i);
            holder.tv1.setText(bean.salesTrait);
            holder.tv2.setText(bean.resblockName + " " + bean.circleTypeName);
            holder.tv3.setText(bean.bedroomAmount + "室" + bean.parlorAmount + "厅 " +
                    getint(bean.totalPrices) + "㎡");
            holder.tv4.setText(getint(bean.totalPrices) + "万");
            setBiaoqian(bean.houseLabel, holder);//设置标签
            holder.iv.setImageResource(R.mipmap.defult_twopic);
            if(isShowYuan){
                holder.rlone .setVisibility(View.VISIBLE);
                if(bean.isSelected){
                    holder.iv1.setImageResource(R.mipmap.entrust_type_checked);
                }else {
                    holder.iv1.setImageResource(R.mipmap.entrust_type_unchecked);
                }
                holder.iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView iv= (ImageView) view;
                        if(!bean.isSelected){
                            if(temp>=5){
                                ToastUtil.showToast("最多选取5项");
                                return;
                            }
                            temp++;
                            bean.isSelected=true;
                            iv.setImageResource(R.mipmap.entrust_type_checked);
                        }else {
                            temp--;
                            bean.isSelected=false;
                            iv.setImageResource(R.mipmap.entrust_type_unchecked);
                        }
                        if(compareCallBack!=null){
                            compareCallBack.changCompareCount(temp);
                        }
                    }
                });
            }else {
                holder.rlone .setVisibility(View.GONE);
            }
            loader.displayImg(bean.titleImg, holder.iv);
        } else if (type == type_two) {
            final HouseOneArrBean oneArrBean = (HouseOneArrBean) list.get(i);
            holder2.tv1.setText(oneArrBean.resblockOneName);
            holder2.tv2.setText(oneArrBean.resblockType);
            holder2.tv3.setText(oneArrBean.bedroomAmount + "室" + oneArrBean.parlorAmount + "厅 " + oneArrBean.buildSize + "㎡ "
                    + oneArrBean.unitprBegin + "-" + oneArrBean.unitprEnd + "万/㎡");
            holder2.tv4.setText(getint(oneArrBean.totalprBegin) + "-" + getint(oneArrBean.totalprEnd) + "万");
            if(isShowYuan){
                LogUtils.e(oneArrBean.isSelected+"");
                holder2.rltwo .setVisibility(View.VISIBLE);
                if(oneArrBean.isSelected){
                    holder2.iv2.setImageResource(R.mipmap.entrust_type_checked);
                }else {
                    holder2.iv2.setImageResource(R.mipmap.entrust_type_unchecked);
                }
                holder2.iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView iv= (ImageView) view;
                        if(!oneArrBean.isSelected){
                            if(temp>=5){
                                ToastUtil.showToast("最多选取5项");
                                return;
                            }
                            temp++;
                            oneArrBean.isSelected=true;
                            iv.setImageResource(R.mipmap.entrust_type_checked);
                        }else {
                            temp--;
                            oneArrBean.isSelected=false;
                            iv.setImageResource(R.mipmap.entrust_type_unchecked);
                        }
                        if(compareCallBack!=null){
                            compareCallBack.changCompareCount(temp);
                        }
                    }
                });
            }else {
                holder2.rltwo .setVisibility(View.GONE);
            }
            holder2.iv.setImageResource(R.mipmap.defult_onepic);
//            loader.displayImg(oneArrBean.titlepicImg, holder2.iv);
            Glide.with(context).load(oneArrBean.titlepicImg).into(holder2.iv);

        }
        return view;
    }
    class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv6;
        TextView tv7;
        ImageView iv;
        ImageView iv1;
        RelativeLayout rlone;
    }

    class ViewHolder2 {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        ImageView iv;
        ImageView iv2;
        RelativeLayout rltwo;
    }

    public void setBiaoqian(String houseLabel, ViewHolder holder) {
        if (houseLabel == null) {
            return;
        }
        String[] biaoqian = houseLabel.trim().split(" ");
        int len = biaoqian.length;
        if (len == 1) {
            holder.tv5.setVisibility(View.VISIBLE);
            holder.tv5.setText(biaoqian[0]);
        } else if (len == 2) {
            holder.tv5.setVisibility(View.VISIBLE);
            holder.tv6.setVisibility(View.VISIBLE);
            holder.tv5.setText(biaoqian[0]);
            holder.tv5.setText(biaoqian[1]);
        } else {
            holder.tv5.setVisibility(View.VISIBLE);
            holder.tv6.setVisibility(View.VISIBLE);
            holder.tv7.setVisibility(View.VISIBLE);
            holder.tv5.setText(biaoqian[0]);
            holder.tv5.setText(biaoqian[1]);
            holder.tv5.setText(biaoqian[2]);
        }
    }

    public String getint(String totalPrices) {
        int x = (int) Math.round(Double.valueOf(totalPrices));
        return x + "";
    }
    private CompareCallBack compareCallBack;

    public void setCompareCallBack(CompareCallBack compareCallBack) {
        this.compareCallBack = compareCallBack;
    }

    public interface CompareCallBack{
        void changCompareCount(int count);
    }
}
