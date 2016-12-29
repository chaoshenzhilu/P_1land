package com.bwf.p_1land.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.util.DisplayUtil;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;
import com.bwf.p_1land.ui.sale.adapter.PopupWindowAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class SalePopupWindow extends PopupWindow {
    private Button btn;
    private EditText etMin,etMax;
    private ListView lv1,lv2;
    private View view;
    private boolean isSeconde;
    private int position;
    private PopupWindowAdapter adapter1,adapter2;
    public SalePopupWindow(Context context, boolean isSeconde, int position) {
        super(context);
        this.isSeconde=isSeconde;
        this.position=position;
        view=View.inflate(context, R.layout.online_popupwindon,null);
        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context)/2+50);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init(context);
    }
    void init(Context context){
        lv1= (ListView) view.findViewById(R.id.online_lv1);
        lv2= (ListView) view.findViewById(R.id.online_lv2);
        adapter1=new PopupWindowAdapter(context,false);
        lv1.setAdapter(adapter1);
        if(isSeconde){
            lv2.setVisibility(View.VISIBLE);
            adapter2=new PopupWindowAdapter(context,isSeconde);
            lv2.setAdapter(adapter2);
        }
        if(position==1){
            lv1.addFooterView(addFloorView(context));
            etMin= (EditText) view.findViewById(R.id.price_min);
            etMax= (EditText) view.findViewById(R.id.price_max);
            btn= (Button) view.findViewById(R.id.price_btn);
        }
    }
    public void setPopupAdapter(final List<OnlineResult> resultList, final CallBackPupopWindow callBackPupopWindow){
        adapter1.setResultList(resultList);
        adapter1.notifyDataSetChanged();
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectDatas(resultList,i);
                adapter1.notifyDataSetChanged();
                if(i==0||i==1){
                    callBackPupopWindow.changetitle(resultList.get(i),position);
                    dismiss();
                }
                if(isSeconde){
                    adapter2.setResultList(resultList.get(i).childList);
                    adapter2.notifyDataSetChanged();
                    return;
                }
                callBackPupopWindow.changetitle(resultList.get(i),position);
                dismiss();
            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectDatas(adapter2.getResultList(),i);
                adapter2.notifyDataSetChanged();
                callBackPupopWindow.changetitle(adapter2.getResultList().get(i),position);
                dismiss();
            }
        });
        if(position!=1){
            return;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minPrice=etMin.getText().toString();
                String maxPrice=etMax.getText().toString();
                if(minPrice==null||minPrice.equals("")){
                    ToastUtil.showToast("请输入自定义价格");
                    return;
                }
                if(maxPrice==null||maxPrice.equals("")){
                    ToastUtil.showToast("请输入自定义价格");
                    return;
                }
                OnlineResult onlineResult=new OnlineResult();
                onlineResult.minValue=minPrice;
                onlineResult.maxValue=maxPrice;
                onlineResult.name=minPrice+"-"+maxPrice;
                callBackPupopWindow.changetitle(onlineResult,position);
                closePopupWindow();
            }
        });
    }
    public void setSelectDatas(List<OnlineResult> resultList, int position){
        for(int i = 0;i< resultList.size();i++){
            if(i == position){
                resultList.get(i).isSelect = true;
            }else{
                resultList.get(i).isSelect = false;
            }
        }
    }
    public View addFloorView(Context context){
        View view=View.inflate(context, R.layout.pricefloorview,null);
        return view;
    }
    public void showPopupWindow(View view){
        if(!this.isShowing()){
            this.showAsDropDown(view);
        }
    }
    public void closePopupWindow(){
        this.dismiss();
    }

    public interface CallBackPupopWindow{
        void changetitle(OnlineResult onlineResult,int position);
    }
}
