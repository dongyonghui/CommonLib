package com.dyh.android.winehall.weigit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：lblbh on 2017/2/27 15:05
 * 邮箱：lanbuhan@163.com
 * 功能：禁止滑动
 */
public class CustomViewPagerAddCache extends LazyViewPagerAddCache {
    private boolean isScrollable ;

    public CustomViewPagerAddCache(Context context) {
        super(context);
    }

    public CustomViewPagerAddCache(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScrollable == false) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScrollable == false) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }


    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }
}