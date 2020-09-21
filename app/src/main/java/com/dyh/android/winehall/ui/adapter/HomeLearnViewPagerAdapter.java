package com.dyh.android.winehall.ui.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dyh.android.winehall.ui.fragment.HomeLearnViewPagerItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 10:45
 * description:首页学酒单个页面
 */
public class HomeLearnViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<HomeLearnViewPagerItemFragment> mDataList = new ArrayList<>();

    public HomeLearnViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setNewData(List<HomeLearnViewPagerItemFragment> list) {
        mDataList.clear();
        if (null != list)
            mDataList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public HomeLearnViewPagerItemFragment getItem(int position) {
        if (position >= 0 && position < mDataList.size()) {
            return mDataList.get(position);
        }
        return null;
    }

    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }
}
