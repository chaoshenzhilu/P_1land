package com.bwf.p_1land.ui.compare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.requestclass.saleclass.HouseArrBean;
import com.bwf.p_1land.requestclass.saleclass.HouseOneArrBean;
import com.bwf.p_1land.requestclass.saleclass.OnLineHouseResult;
import com.bwf.p_1land.ui.sale.adapter.SaleAdapter;
import com.bwf.p_1land.view.RefreshListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CompareActivity extends BaseActivity {
    @Bind(R.id.online_compare_ivback)
    ImageView onlineCompareIvback;
    @Bind(R.id.online_compare_name)
    TextView onlineCompareName;
    @Bind(R.id.online_compare_edit)
    TextView onlineCompareEdit;
    @Bind(R.id.online_compare_addll)
    LinearLayout onlineCompareAddll;
    @Bind(R.id.compare_star)
    Button compareStar;
    @Bind(R.id.compare_clear)
    Button compareClear;
    @Bind(R.id.compare_delete)
    Button compareDelete;
    @Bind(R.id.compare_floor)
    LinearLayout compareFloor;
    private OnLineHouseResult onLineHouseResult;
    private List<Object> list;
    private SaleAdapter adapter;
    private RefreshListView listView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_compare;
    }

    @Override
    public void beforInitView() {
        ButterKnife.bind(this);
        onLineHouseResult = new OnLineHouseResult();
        onLineHouseResult.result = getIntent().getParcelableExtra("OnLineHouseResultBean");
        list = onLineHouseResult.initListData();
    }

    @Override
    public void initView() {
        listView = findViewByIdNoCast(R.id.compare_lv);
    }

    @Override
    public void initViewData() {
        compareStar.setText("开始对比(" + list.size() + ")");
        onlineCompareName.setText(getIntent().getStringExtra("SaleActivity"));
        adapter = new SaleAdapter(this);
        adapter.setList(list);
        listView.setAdapter(adapter);
    }

    @Override
    public void afterInitView() {

    }

    boolean isEdit = true;

    @OnClick({R.id.online_compare_ivback, R.id.online_compare_name, R.id.online_compare_edit,
            R.id.online_compare_addll, R.id.compare_star,R.id.compare_clear, R.id.compare_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.online_compare_ivback: //返回
                finish();
                break;
            case R.id.online_compare_name://标题

                break;
            case R.id.online_compare_edit://编辑
                if (isEdit) {
                    adapter.setShowYuan(true);
                    adapter.notifyDataSetChanged();
                    onlineCompareEdit.setText("完成");
                    compareFloor.setVisibility(View.VISIBLE);
                    compareStar.setVisibility(View.GONE);
                    isEdit = false;
                } else {
                    adapter.setShowYuan(false);
                    adapter.notifyDataSetChanged();
                    onlineCompareEdit.setText("编辑");
                    compareFloor.setVisibility(View.GONE);
                    compareStar.setVisibility(View.VISIBLE);
                    isEdit = true;
                }
                break;
            case R.id.online_compare_addll://添加比较
                finish();
                break;
            case R.id.compare_star://开始比较
//                Intent intent=new Intent(CompareActivity.this,CompareDetailActivity.class);
//                for(int i=0;i<list.size();i++){
//                    if(list.get(i) instanceof HouseArrBean){
//                        HouseArrBean houseArrBean= (HouseArrBean) list.get(i) ;
//                        intent.putExtra("houseTwo"+i,houseArrBean.housedelId);
//                    }else {
//                        HouseOneArrBean houseOneArrBean= (HouseOneArrBean) list.get(i) ;
//                        intent.putExtra("houseOne"+i,houseOneArrBean.houseOneId);
//                    }
//                }
//                intent.putExtra("count",list.size());
                Bundle bundle=new Bundle();
                bundle.putParcelable("OnLineHouseResultBean",onLineHouseResult.result);
                IntentUtils.openActivity(this,CompareDetailActivity.class,bundle);
                break;
            case R.id.compare_clear://清空
                list.clear();
                adapter.notifyDataSetChanged();
                compareStar.setText("开始对比(" + list.size() + ")");
                break;
            case R.id.compare_delete://删除
                for(int i=list.size()-1;i>=0;i--){
                    if(list.get(i) instanceof HouseArrBean){
                        HouseArrBean houseArrBean= (HouseArrBean) list.get(i) ;
                        if(houseArrBean.isSelected){
                            list.remove(houseArrBean);
                        }
                    }else {
                        HouseOneArrBean houseOneArrBean= (HouseOneArrBean) list.get(i) ;
                        if(houseOneArrBean.isSelected){
                            list.remove(houseOneArrBean);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                compareStar.setText("开始对比(" + list.size() + ")");
                break;
        }
    }
}
