package com.bwf.p_1land.ui.sale;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneResult;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.ImgUrlArr;
import com.bwf.p_1land.ui.sale.adapter.TextViewAdapter;
import com.bwf.p_1land.ui.sale.adapter.ThumAdapter;
import com.bwf.p_1land.ui.sale.adapter.ViewPagerAdapter;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne04;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class ThumbnailActivity extends BaseActivity {
    private ImageView thumbnailIv;
    private List<ImgUrlArr> list;
    private FragmentHouseOne04 fragmentHouseOne04;
    private HouseOneResult result;
    private ImageLoader loader;
    private ArrayList<String> arr;
    private Gallery gallery;
    private TextViewAdapter adapter;
    private ScrollView scrollView;
    private LinearLayout ll,sll;
    private int ischecked=-1;
    private boolean flag=true;
    private ViewPagerAdapter adapter1;
    @Override
    public int getContentViewId() {
        return R.layout.activity_thumbnail;
    }

    @Override
    public void beforInitView() {
        result = (HouseOneResult) getIntent().getSerializableExtra("HouseOneResult");
        if (result != null) {
            list = result.imgUrlArr;
        }
    }

    @Override
    public void initView() {
        fragmentHouseOne04 = (FragmentHouseOne04) getSupportFragmentManager().findFragmentById(R.id.thumbnail_fragment);
        gallery = findViewByIdNoCast(R.id.thumbnail_gallery);
        scrollView=findViewByIdNoCast(R.id.thumbnail_scrollview);
        ll=findViewByIdNoCast(R.id.thumbnail_ll);
        sll=findViewByIdNoCast(R.id.thumbnail_sll);
        thumbnailIv=findViewByIdNoCast(R.id.thumbnail_iv);
        setOnclick(thumbnailIv);
    }

    @Override
    public void initViewData() {
        loader = new ImageLoader();
        arr = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean flag = true;
            for (String x:arr) {
                if (x.equals(getType(list.get(i).picType))) {
                    flag = false;
                }
            }
            if (flag) {
                arr.add(getType(list.get(i).picType));
            }
        }
        setRecyclerViewData();
    }
    @Override
    public void afterInitView() {
        fragmentHouseOne04.setFragment04Data(list, loader, false, false);
        adapter = new TextViewAdapter(this, arr);
        gallery.setAdapter(adapter);
        fragmentHouseOne04.setGalleryCallBack(galleryCallBack);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                flag=false;
                LinearLayout ll= (LinearLayout) gallery.getChildAt(i);
                TextView tv= (TextView) ll.getChildAt(0);
                tv.setTextSize(26);
                tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                if(ischecked!=-1){
                    LinearLayout ll2= (LinearLayout) gallery.getChildAt(ischecked);
                    TextView tv2= (TextView) ll2.getChildAt(0);
                    tv2.setTextSize(22);
                    tv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                ischecked=i;
                fragmentHouseOne04.chagePager(arr,i);
                thumbnailIv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag=true;
                    }
                },50);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fragmentHouseOne04.getAdapter().setManagerActivity(managerActivity);
    }

    @Override
    public void onClick(View view) {
        if(scrollView.getVisibility()==View.GONE){
            scrollView.setVisibility(View.VISIBLE);
            sll.setVisibility(View.VISIBLE);
            ll.setVisibility(View.GONE);
            findViewById(R.id.thumbnail_fragment).setVisibility(View.GONE);
            gallery.setVisibility(View.GONE);
            thumbnailIv.setImageResource(R.mipmap.thum_close);
        }else {
            scrollView.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
            sll.setVisibility(View.GONE);
            findViewById(R.id.thumbnail_fragment).setVisibility(View.VISIBLE);
            gallery.setVisibility(View.VISIBLE);
            thumbnailIv.setImageResource(R.mipmap.list_icon);
        }
    }
    public String getType(String type) {
        if (type.equals("1")) {
            return "外景图";
        }
        if (type.equals("2")) {
            return "地理位置图";
        }
        if (type.equals("3")) {
            return "座栋分布图";
        }
        if (type.equals("4")) {
            return "户型图";
        }
        if (type.equals("5")) {
            return "样板间";
        }
        if (type.equals("6")) {
            return "实勘图";
        }
        return "";
    }
    public void setRecyclerViewData(){
        for(String x:arr){
            TextView tv=new TextView(this);
            tv.setTextSize(22);
            tv.setTextColor(Color.WHITE);
            tv.setPadding(10,10,10,10);
            tv.setText(x);
            ArrayList<String> arrayList=new ArrayList<>();
            for(ImgUrlArr imgUrlArr:list){
                if(x.equals(getType(imgUrlArr.picType))){
                    arrayList.add(imgUrlArr.picName);
                }
            }
            RecyclerView recyclerView=new RecyclerView(this);
            GridLayoutManager manager=new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(manager);
            ThumAdapter adapter=new ThumAdapter(this,loader,arrayList);
            adapter.setCallBack(callBack);
            recyclerView.setAdapter(adapter);
            sll.addView(tv);
            sll.addView(recyclerView);
        }
    }
    //通过改变viewpager改变gallery的选中
    private FragmentHouseOne04.GalleryCallBack galleryCallBack=new FragmentHouseOne04.GalleryCallBack() {
        @Override
        public void changeGallery(int position) {
            if(flag){
                position=position%list.size();
                for(int i=0;i<arr.size();i++){
                    if( arr.get(i).equals(getType(list.get(position).picType))){
                        gallery.setSelection(i);
                        return;
                    }
                }
            }
        }
    };
    private ThumAdapter.AdapterCallBack callBack=new ThumAdapter.AdapterCallBack() {
        @Override
        public void back(String uri) {
            if(scrollView.getVisibility()==View.GONE){
                scrollView.setVisibility(View.VISIBLE);
                sll.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                findViewById(R.id.thumbnail_fragment).setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                thumbnailIv.setImageResource(R.mipmap.thum_close);
            }else {
                scrollView.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
                sll.setVisibility(View.GONE);
                findViewById(R.id.thumbnail_fragment).setVisibility(View.VISIBLE);
                gallery.setVisibility(View.VISIBLE);
                thumbnailIv.setImageResource(R.mipmap.list_icon);
            }
            for(int i=0;i<list.size();i++){
                if(uri.equals(list.get(i).picName)){
                    fragmentHouseOne04.setPager(i);
                    return;
                }
            }
        }
    };

    private ViewPagerAdapter.ManagerActivity managerActivity=new ViewPagerAdapter.ManagerActivity() {
        @Override
        public void manager() {
            finish();
        }
    };
}
