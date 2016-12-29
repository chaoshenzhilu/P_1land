package com.bwf.p_1land.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyMapView extends RelativeLayout {
    TextView tv_name,tv_num;
    ImageView iv;
    public MyMapView(Context context) {
        this(context,null);
    }
    public MyMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.map_view, this, true);
        tv_name= (TextView) findViewById(R.id.map_name);
        tv_num= (TextView) findViewById(R.id.map_num);
        iv= (ImageView) findViewById(R.id.map_iv);
    }

    public void setText(String name,String num){
        tv_name.setText(name);
        tv_num.setText(num);
    }
}
