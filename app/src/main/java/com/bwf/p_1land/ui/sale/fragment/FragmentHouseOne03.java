package com.bwf.p_1land.ui.sale.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.util.StringUtils;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentHouseOne03 extends BaseFragment {
    @Bind(R.id.fragment03_tvname)
    TextView fragment03Tvname;
    @Bind(R.id.fragment03_tvprice)
    TextView fragment03Tvprice;
    @Bind(R.id.fragment03_tvhuxing)
    TextView fragment03Tvhuxing;
    @Bind(R.id.fragment03_tvdanjiao)
    TextView fragment03Tvdanjiao;
    @Bind(R.id.fragment03_tvarea1)
    TextView fragment03Tvarea1;
    @Bind(R.id.fragment03_tvarea2)
    TextView fragment03Tvarea2;
    @Bind(R.id.fragment03_tvnumber)
    TextView fragment03Tvnumber;
    @Bind(R.id.fragment03_tvaddress)
    TextView fragment03Tvaddress;

    @Override
    public int getLayout() {
        return R.layout.fragment_houseone03;
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

    @Override
    public void onClick(View view) {

    }
    public void setFragment03Data(HouseOneData houseOneData){
        fragment03Tvname.setText(houseOneData.result.resblockOneName);
        fragment03Tvprice.setText(StringUtils.getint(houseOneData.result.totalprBegin)+"-"+
                StringUtils.getint(houseOneData.result.totalprEnd)+"万");
        StringBuffer sb=new StringBuffer();
        sb.append(StringUtils.getint(houseOneData.result.bedroomAmount)).append("室")
                .append(StringUtils.getint(houseOneData.result.parlorAmount)).append("厅")
                .append(StringUtils.getint(houseOneData.result.toiletAmount)).append("卫");
        fragment03Tvhuxing.setText(sb.toString());
        fragment03Tvarea1.setText("建筑面积:  "+StringUtils.getint(houseOneData.result.buildSize)+"㎡");
        fragment03Tvarea2.setText("套内面积:  "+StringUtils.getint(houseOneData.result.innenbereichSize));
        fragment03Tvnumber.setText("房源编号:  "+houseOneData.result.roomCode);
        fragment03Tvaddress.setText("地址:  "+houseOneData.result.lage);
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
}
