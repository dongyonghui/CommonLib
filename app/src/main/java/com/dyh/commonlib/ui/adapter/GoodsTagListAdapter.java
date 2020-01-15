package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.LabelItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：商品标签列表适配器
 */
public class GoodsTagListAdapter extends BaseQuickRecyclerViewAdapter<LabelItemBean> {

    private final List<LabelItemBean> selectedItemList = new ArrayList<>();

    public GoodsTagListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LabelItemBean item) {
        helper.setText(R.id.mInfoTextView, item.getTagsName());
    }
}
