package com.bwf.p_1land.ui.sale.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.util.StringUtils;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentHouseOne06 extends BaseFragment {
    @Bind(R.id.fragment06_tv1)
    TextView fragment06Tv1;
    @Bind(R.id.fragment06_miaoshu)
    TextView fragment06Miaoshu;
    @Bind(R.id.fragmen06_iv)
    ImageView fragmen06Iv;
    @Bind(R.id.fragment06_ll)
    LinearLayout fragment06Ll;
    private int line = 0;

    @Override
    public int getLayout() {
        return R.layout.fragment_houseone06;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public void dataInit() {

    }

    @Override
    public void afterInit() {

    }

    public void setFragment06Data(HouseOneData houseOneData) {
        setTextData(houseOneData);
    }

    public void setTextData(HouseOneData houseOneData) {
        fragment06Tv1.setText("装修标准:  " + houseOneData.result.decorationName + "\n" +
                "单平方精装装修标准:  " + StringUtils.getint(houseOneData.result.metersPrice) + "元" + "\n" +
                "占地面积:  " + StringUtils.getint(houseOneData.result.covers) + "平米" + "\n" +
                "交房时间:  " + getTime(houseOneData.result.launchTime) + "\n" +
                "容积率:  " + houseOneData.result.volumeRate + "%" + "\n" +
                "绿化率:  " + StringUtils.getint(houseOneData.result.greeningRate) + "%" + "\n" +
                "层高:  " + houseOneData.result.storey + "m" + "\n" +
                "建筑面积:  " + StringUtils.getint(houseOneData.result.buildSize) + "㎡" + "\n" +
                "物业费:  " + StringUtils.getint(houseOneData.result.propertyCosts) + "元/月/㎡" + "\n" +
                "供暖方式:  " + houseOneData.result.heating + "\n" +
                "采暖方式:  " + houseOneData.result.heating1 + "\n" +
                "楼间距:  " + StringUtils.getint(houseOneData.result.floorSpace) + "㎡" + "\n" +
                "建筑风格:  " + houseOneData.result.buildingType + "\n" +
                "车位数:  " + houseOneData.result.parkingNum + "个" + "\n" +
                "外立面材质:  " + houseOneData.result.facadeMaterial + "\n" +
                "开发商:  " + houseOneData.result.developers + "\n" +
                "物业:  " + houseOneData.result.immobilien + "\n" +
                "位置:  " + houseOneData.result.lage);
        fragment06Miaoshu.setText(houseOneData.result.formType);
        fragment06Tv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                line = fragment06Tv1.getLineCount();
                if (line > 7) {
                    fragment06Tv1.setLines(7);
                } else {
                    fragment06Tv1.setLines(line);
                }
            }
        }, 50);
    }

    public String getTime(String launchTime) {
        Date date = new Date(Long.valueOf(launchTime));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate MoreHouseOneData fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    boolean iszhankai = true;

    @OnClick(R.id.fragment06_ll)
    public void onClick() {
        if(iszhankai){
            fragmen06Iv.setImageResource(R.mipmap.content_up);
            if (line > 7) {
                fragment06Tv1.setLines(line);
            }
            iszhankai=false;
        }else {
            fragmen06Iv.setImageResource(R.mipmap.first_down);
            if (line > 7) {
                fragment06Tv1.setLines(7);
            }
            iszhankai=true;
        }
    }
}
