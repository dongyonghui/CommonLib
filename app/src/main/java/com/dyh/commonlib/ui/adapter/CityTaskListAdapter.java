package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CityTaskItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：城市任务列表
 */
public class CityTaskListAdapter extends BaseQuickRecyclerViewAdapter<CityTaskItemBean> {
    public CityTaskListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CityTaskItemBean item) {

        helper.setText(R.id.mCityTextView, item.getCity())//名称
                .setText(R.id.mManagerTextView, item.getOperatorName())//
                .setText(R.id.mTargetOrderNumberTextView, item.getTargetOrderCount())//
                .setText(R.id.mTargetOrderMoneyTextView, item.getTargetTradePrice())//
                .setText(R.id.mCurrentProgressTextView, mContext.getString(R.string.formatString_currentCityTaskProgress,
                        item.getCurrentOrderCount(), item.getCurrentTradePrice()))//
                .addOnClickListener(R.id.mEditTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
