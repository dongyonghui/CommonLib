package com.dyh.android.winehall.ui.adapter;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 15:44
 * description:
 */
public class HomeLearnCourseImageListAdapter extends BaseQuickRecyclerViewAdapter<String> {
    public HomeLearnCourseImageListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setImageUrl(R.id.mImageView, item, UIConfig.HOME_COURSE_IMAGE_RADIUS);
    }
}
