package com.bwf.p_1land.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.util.DisplayUtil;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;
import com.bwf.p_1land.ui.research.adapter.ReSearchPopAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class SearchPopupWindow extends PopupWindow {
    private View view;
    private ListView lv;
    private ReSearchPopAdapter adapter;

    public SearchPopupWindow(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.search_popupwindow, null);
        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context) - 50);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    public void setPopupWindow(Context context, final List<OnlineResult> list, final SearchCallBack callBack) {
        lv = (ListView) view.findViewById(R.id.seacher_poplv);
        adapter = new ReSearchPopAdapter(context);
        lv.setAdapter(adapter);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setIscheck(list,i);
                adapter.notifyDataSetChanged();
                if(callBack!=null){
                    callBack.changSearch(i);
                }

                dismiss();
            }
        });
    }
    public void setIscheck(List<OnlineResult> list,int position){
        for(int i=0;i<list.size();i++){
            if(i==position){
                list.get(i).isSelect=true;
            }else {
                list.get(i).isSelect=false;
            }
        }
    }


    public void showPopWindow(View view) {
        if (!this.isShowing()) {
            this.showAsDropDown(view);
        }
    }

    public interface SearchCallBack {
        void changSearch(int position);
    }
}

