package com.bwf.p_1land.ui.research;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p_1land.MyApplication;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.OnLineHouseRequest;
import com.bwf.p_1land.requestclass.assetsclass.OnlineParam;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResultData;
import com.bwf.p_1land.requestclass.searchclass.SearchChild;
import com.bwf.p_1land.requestclass.searchclass.SearchData;
import com.bwf.p_1land.ui.research.adapter.ReSearchAdapter;
import com.bwf.p_1land.view.RefreshListView;
import com.bwf.p_1land.view.SearchPopupWindow;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ReSearchActivity extends BaseActivity {
    private ImageButton ib1,ib2;
    private RefreshListView lv;
    private TextView tv;
    private OnLineHouseRequest onLineHouseRequest;
    private ReSearchAdapter adapter;
    private RelativeLayout rl;
    private List<OnlineResult> list;
    private LinearLayout ll;
    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void beforInitView() {
        OnlineResultData onlineResultData=MyApplication.getApplication().getOnlineResultData();
        for(OnlineParam onlineParam:onlineResultData.result){
            if(onlineParam.paramType.equals("1009")){
                list=onlineParam.paramList;
            }
        }
    }

    @Override
    public void initView() {
        ll=findViewByIdNoCast(R.id.seacher_ll);
        rl=findViewByIdNoCast(R.id.seacher_rl);
        ib1=findViewByIdNoCast(R.id.seacher_ib1);
        ib2=findViewByIdNoCast(R.id.seacher_ib2);
        tv=findViewByIdNoCast(R.id.seacher_tv);
        lv=findViewByIdNoCast(R.id.seacher_lv);
        setOnclick(tv,ib1,ib2);
        adapter=new ReSearchAdapter(this);
        lv.setAdapter(adapter);
    }

    @Override
    public void initViewData() {
        onLineHouseRequest=new OnLineHouseRequest();
        onLineHouseRequest.pageNo=0;
        getSearchNetData();
        showDialog();
    }

    @Override
    public void afterInitView() {
        lv.setRefreh_ListViewListener(new RefreshListView.Refreh_ListViewListener() {
            @Override
            public void onRefresh() {
                onLineHouseRequest.pageNo=0;
                getSearchNetData();
            }

            @Override
            public void onLoadMore() {
                onLineHouseRequest.pageNo++;
                getSearchNetData();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    return;
                }
                SearchChild child= (SearchChild) adapter.getItem(i-1);
                LogUtils.e(child.wordPath);
                Intent intent=new Intent(ReSearchActivity.this,ReSearchNetActivity.class);
                intent.putExtra("url",child.wordPath);
                intent.putExtra("title",child.title);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.seacher_ib1:
                finish();
                break;
            case R.id.seacher_ib2:
                IntentUtils.openActivity(ReSearchActivity.this,ReSearchSearch.class);
                break;
            case R.id.seacher_tv:
                SearchPopupWindow popupWindow=new SearchPopupWindow(this);
                popupWindow.setPopupWindow(this,list,callBack);
                popupWindow.showPopWindow(rl);
                break;
        }
    }
    private SearchPopupWindow.SearchCallBack callBack=new SearchPopupWindow.SearchCallBack() {
        @Override
        public void changSearch(int position) {
            tv.setText(list.get(position).name);
            if (position == 0) {
                lv.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
            }else {
                lv.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
            }
        }
    };
    public void getSearchNetData() {
        Httphelper.searcherRequestData(this, onLineHouseRequest, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissDialog();
                SearchData searchData = new Gson().fromJson(result, SearchData.class);
                LogUtils.e(result);
                LogUtils.e(searchData.toString());
                if (onLineHouseRequest.pageNo == 0) {
                    SearchChild child = new SearchChild();
                    child.showImgPath = searchData.result.showImgPath;
                    child.title = searchData.result.title;
                    child.wordPath = searchData.result.wordPath;
                    List<SearchChild> list = searchData.result.reportList;
                    list.add(0, child);
                    adapter.setList(list);
                } else {
                    adapter.addList(searchData.result.reportList);
                }
                adapter.notifyDataSetChanged();
                lv.setOnComplete();
            }

            @Override
            public void onFail(String errMessage) {
                dismissDialog();
                ToastUtil.showToast(errMessage);
                lv.setOnComplete();
            }
        });
    }
}
