package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：选择分类二级列表适配器
 */
public class SetCategorySecondListAdapter extends BaseQuickRecyclerViewAdapter<CategoryItemBean> {

    private SelectedCategoryListAdapter selectedListAdapter;

    public SetCategorySecondListAdapter(int layoutResId, SelectedCategoryListAdapter selectedListAdapter) {
        super(layoutResId);
        this.selectedListAdapter = selectedListAdapter;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryItemBean item) {
        helper.setText(R.id.mNameTextView, item.getName());

        //监听复选框
        CheckBox checkBox = helper.getView(R.id.mCheckBox);
        if (null != selectedListAdapter) {
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(selectedListAdapter.getData().contains(item));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedListAdapter.removeItem(item);
                        selectedListAdapter.addListBottom(item);
                    } else {
                        selectedListAdapter.removeItem(item);
                    }
                }
            });
        }
        //点击列表项改变复选框状态
        helper.getView(R.id.mRootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
    }
}
