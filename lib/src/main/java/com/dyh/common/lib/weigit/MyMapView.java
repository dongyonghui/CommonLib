package com.dyh.common.lib.weigit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyMapView extends FrameLayout {
 
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
 
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            getParent().requestDisallowInterceptTouchEvent(true);//请求父控件不拦截触摸事件
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {          
            getParent().requestDisallowInterceptTouchEvent(false);
        }
 
        return super.dispatchTouchEvent(ev);
    }
 
    public MyMapView(Context context) {
        super(context);
    }
 
    public MyMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public MyMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}