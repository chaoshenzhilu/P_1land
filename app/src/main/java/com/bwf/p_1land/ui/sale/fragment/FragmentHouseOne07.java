package com.bwf.p_1land.ui.sale.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;

/**
 * Created by Administrator on 2016/12/13.
 */

public class FragmentHouseOne07 extends BaseFragment {

    private ImageView fragmen07Iv;
    private TextView fragmen07Tv;
    @Override
    public int getLayout() {
        return R.layout.fragment_houseone07;
    }

    @Override
    public void beforInit() {
    }

    @Override
    public void init() {
        fragmen07Iv=findViewByIdNoCast(R.id.fragmen07_iv);
        fragmen07Tv=findViewByIdNoCast(R.id.fragmen07_tv);
    }

    @Override
    public void dataInit() {

    }

    @Override
    public void afterInit() {

    }

    public void setFragment07Data(HouseOneData houseOneData, ImageLoader loader) {

        fragmen07Tv.setText("[" + houseOneData.result.districtName + " " +
                houseOneData.result.circleTypeName + "]" + houseOneData.result.lage);
        String imgUrl = "http://api.map.baidu.com/staticimage?center="
                + houseOneData.result.lage
                + "&width=480&height=270&zoom=15&scale=1"
                + "&markers=" + houseOneData.result.lage;
//        loader.displayImg(imgUrl, fragmen07Iv);
        Glide.with(getActivity()).load(imgUrl).into(fragmen07Iv);
    }

    @Override
    public void onClick(View view) {

    }
}
