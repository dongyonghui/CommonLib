package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.HomeActionBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：首页按钮列表
 */
public class HomeActionAdapter extends BaseQuickRecyclerViewAdapter<HomeActionBean> {
    public HomeActionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeActionBean item) {
        helper.setText(R.id.mTextView, item.buttonName)
                .setImageResource(R.id.mImageView, item.buttonImgResId);
    }
}
