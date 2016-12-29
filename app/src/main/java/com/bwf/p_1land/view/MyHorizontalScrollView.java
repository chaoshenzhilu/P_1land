package com.bwf.p_1land.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016/12/25.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyHorizontalScrollView(Context context) {
        super(context);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            listener.scrollview(this,l, t, oldl, oldt);
        }
    }

    private MyScrollViewListener listener;

    public void setListener(MyScrollViewListener listener) {
        this.listener = listener;
    }

    public interface MyScrollViewListener{
        void scrollview(MyHorizontalScrollView scrollView,int l, int t, int oldl, int oldt);
    }
}
