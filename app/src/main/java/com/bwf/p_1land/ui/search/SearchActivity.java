package com.bwf.p_1land.ui.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.researchclass.ResearchData;
import com.bwf.p_1land.requestclass.researchclass.ResearchResult;
import com.bwf.p_1land.ui.search.adapter.SearchAdapter;
import com.bwf.p_1land.ui.sale.SaleActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**豪宅研究
 * Created by Administrator on 2016/12/1.
 */

public class SearchActivity extends BaseActivity {
    private EditText et;
    private ListView lv;
    private int chose;
    private Map<String,String> map;
    private SearchAdapter adapter;
    private ResearchResult researchResult;
    @Override
    public int getContentViewId() {
        return R.layout.activity_research;
    }

    @Override
    public void beforInitView() {
        chose=this.getIntent().getIntExtra("chose",-1);
    }

    @Override
    public void initView() {
        et=findViewByIdNoCast(R.id.research_et);
        lv=findViewByIdNoCast(R.id.research_list);
        adapter=new SearchAdapter(this);
        lv.setAdapter(adapter);
    }
    @Override
    public void initViewData() {
        map=new HashMap<String,String>();
        ensureActivity();
    }

    public void ensureActivity(){
        switch (chose){
            case 1:   //在售豪宅...请输入房源或商圈名称...
                et.setHint("请输入房源或商圈名称...");
                break;
            case 2:    //待租豪宅。。请输入房源名称...
                et.setHint("请输入房源名称...");
                break;
            case 3:     //楼盘鉴赏。。请输入楼盘或商圈名称...
                et.setHint("请输入楼盘或商圈名称...");
                break;
            case 4:     //搜索。。请输入楼盘名称或房源特征...
                et.setHint("请输入楼盘名称或房源特征...");
                break;
        }
    }


    @Override
    public void afterInitView() {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                LogUtils.e(chose+"");
                switch (chose){
                    case 1:
                        map.put("type","0");//主页面和楼盘鉴赏的搜索
                        break;
                    case 2:
                        map.put("type","2");//待租的搜索
                        break;
                    case 3:
                        map.put("type","1");//在售的搜索
                        break;
                    case 4:
                        map.put("type","0");//主页面和楼盘鉴赏的搜索
                        break;
                }
                map.put("keyWords",et.getText().toString());
                getResearchNetData();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager manager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                Intent intent=null;
                switch (chose){
                    case 1://在售豪宅
                        researchResult= (ResearchResult) adapter.getItem(i);
                        intent=new Intent();
                        intent.putExtra("researchResult",researchResult);
                        SearchActivity.this.setResult(100,intent);
                        break;
                    case 2://待租豪宅
                        researchResult= (ResearchResult) adapter.getItem(i);
                        intent=new Intent(SearchActivity.this, SaleActivity.class);
                        intent.putExtra("researchResult",researchResult);
                        SearchActivity.this.setResult(200,intent);
                        break;
                    case 3://楼盘鉴赏
                        researchResult= (ResearchResult) adapter.getItem(i);
                        intent=new Intent();
                        intent.putExtra("researchResult",researchResult);
                        SearchActivity.this.setResult(300,intent);
                        break;
                    case 4://搜索
                        researchResult= (ResearchResult) adapter.getItem(i);
                        intent=new Intent(SearchActivity.this, SaleActivity.class);
                        intent.putExtra("researchResult",researchResult);
                        startActivity(intent);
                        break;
                }
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
    public void getResearchNetData(){
        Httphelper.researchRequestData(this, map, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e(result);
                ResearchData researchData=new Gson().fromJson(result,ResearchData.class);
                LogUtils.e(researchData.toString());
                if(researchData!=null){
                    adapter.setList(researchData.result);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
    }
}
