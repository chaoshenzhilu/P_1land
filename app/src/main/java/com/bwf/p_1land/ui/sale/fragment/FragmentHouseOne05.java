package com.bwf.p_1land.ui.sale.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.StringUtils;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.ApartmentImgVos;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;

/**
 * Created by Administrator on 2016/12/13.
 */

public class FragmentHouseOne05 extends BaseFragment {
    private LinearLayout ll;
    @Override
    public int getLayout() {
        return R.layout.fragment_houseone05;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {
        ll=findViewByIdNoCast(R.id.fragmen05_ll);
    }

    @Override
    public void dataInit() {

    }

    @Override
    public void afterInit() {

    }
    public void setFragment05Data(HouseOneData houseOneData, ImageLoader loader, final FragmentCallback callback){
        for (final ApartmentImgVos apartmentImgVos:houseOneData.result.apartmentImgVos){
            ImageView imageView=new ImageView(getContext());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.height=250;
            params.width=400;
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            loader.displayImg(apartmentImgVos.imgUrl,imageView);
            TextView textView=new TextView(getContext());
            textView.setPadding(0,20,0,0);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(apartmentImgVos.bedroomAmount+"室"+apartmentImgVos.parlorAmount+"厅"+
            apartmentImgVos.toiletAmount+"卫 "+ StringUtils.getint(apartmentImgVos.totalprBegin)+"-"+
            StringUtils.getint(apartmentImgVos.totalprEnd)+"万 "+StringUtils.getint(apartmentImgVos.innenbereichSize)+"平米");
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(12);
            LinearLayout linearLayout=new LinearLayout(getContext());
            linearLayout.setPadding(0,0,20,0);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            ll.addView(linearLayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onShow(apartmentImgVos.imgUrl);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {

    }
    public interface FragmentCallback{
        void onShow(String url);
    }
}
