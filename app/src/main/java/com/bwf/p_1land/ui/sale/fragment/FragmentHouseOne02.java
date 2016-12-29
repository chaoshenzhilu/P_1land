package com.bwf.p_1land.ui.sale.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.ListViewUtils;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.ShowArrBean;
import com.bwf.p_1land.ui.sale.adapter.GuWenAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentHouseOne02 extends BaseFragment {
    private ListView lv;
    private TextView tv_title;
    private GuWenAdapter adapter;
    @Override
    public int getLayout() {
        return R.layout.fragment_houseone02;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {
        lv=findViewByIdNoCast(R.id.fragment02_lv);
        tv_title=findViewByIdNoCast(R.id.fragment02_tv1);
    }

    @Override
    public void dataInit() {

    }

    @Override
    public void afterInit() {

    }

    public void setFragment02Data(String count,List<ShowArrBean> list, ImageLoader loader) {
        tv_title.setText("最熟悉本房的顾问("+count+")");
        adapter=new GuWenAdapter(list,getContext(),loader);
        lv.setAdapter(adapter);
        ListViewUtils.measureListViewHeight(lv);
    }

    @Override
    public void onClick(View view) {

    }
}
