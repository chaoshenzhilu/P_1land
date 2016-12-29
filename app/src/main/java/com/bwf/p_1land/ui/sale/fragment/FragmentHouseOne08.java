package com.bwf.p_1land.ui.sale.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.framework.util.ListViewUtils;
import com.bwf.p_1land.requestclass.morehouseoneclass.MoreHouseOneData;
import com.bwf.p_1land.requestclass.morehouseoneclass.MoreResult;
import com.bwf.p_1land.ui.sale.MoreHouseOneActivity;
import com.bwf.p_1land.ui.sale.adapter.MoreHouseOneAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/13.
 */

public class FragmentHouseOne08 extends BaseFragment {
    @Bind(R.id.fragment08_tv1)
    TextView fragment08Tv1;
    @Bind(R.id.fragment08_ll)
    LinearLayout fragment08Ll;
    @Bind(R.id.fragment08_lv)
    ListView fragment08Lv;
    private MoreHouseOneData moreHouseOneData;
    private List<MoreResult> list;
    private MoreHouseOneAdapter adapter;
    @Override
    public int getLayout() {
        return R.layout.fragment_houseone08;
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

    public void setFragment08Data() {
        Httphelper.getMoreOneHandData(getActivity(), new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                moreHouseOneData=new Gson().fromJson(result,MoreHouseOneData.class);
                if(moreHouseOneData!=null&&moreHouseOneData.result.size()!=0){
//                    fragment08Tv1.setText(moreHouseOneData.);
                    list=moreHouseOneData.result;
                    adapter=new MoreHouseOneAdapter(getContext());
                    adapter.setList(list);
                    fragment08Lv.setAdapter(adapter);
                    ListViewUtils.measureListViewHeight(fragment08Lv);
                }
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
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

    @OnClick(R.id.fragment08_ll)
    public void onClick() {
        IntentUtils.openActivity(getActivity(), MoreHouseOneActivity.class);
    }

}
