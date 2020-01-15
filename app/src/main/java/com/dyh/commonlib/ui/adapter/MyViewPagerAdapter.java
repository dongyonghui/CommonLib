package com.dyh.commonlib.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 项目名称：GouKu_Manage
 * 类描述：
 * 创建人：mayn
 * 创建时间：2018/7/11 12:56
 * 修改人：mayn
 * 修改时间：2018/7/11 12:56
 * 修改备注：
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private Fragment[] TAB_FRAGMENTS;

    public MyViewPagerAdapter(FragmentManager fm, Fragment[] TAB_FRAGMENTS) {
        super(fm);
        this.TAB_FRAGMENTS = TAB_FRAGMENTS;
    }


    @Override
    public Fragment getItem(int position) {
        if (TAB_FRAGMENTS.length <= position) {
            return null;
        }
        return TAB_FRAGMENTS[position];
    }

    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return TAB_FRAGMENTS.length;
    }
}
