package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.AddTakePointForResultBean;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：选择取餐点列表适配器
 */
public class SelectedTakePointsListAdapter extends BaseQuickRecyclerViewAdapter<AddTakePointForResultBean> {

    private final List<CategoryItemBean> selectedItemList = new ArrayList<>();

    public SelectedTakePointsListAdapter(int layoutResId) {
        super(layoutResId);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                remove(position);
            }
        });
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddTakePointForResultBean item) {
        helper.setText(R.id.mInfoTextView, item.name);
    }
}
