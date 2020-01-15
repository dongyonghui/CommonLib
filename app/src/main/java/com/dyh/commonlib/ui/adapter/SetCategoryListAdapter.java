package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.common.lib.dw.listview.BaseSectionQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.recyclerview_helper.entity.SectionEntity;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/28/028 16:04
 * 描述：设置分类列表适配器
 */
public class SetCategoryListAdapter extends BaseSectionQuickRecyclerViewAdapter<SectionEntity<CategoryItemBean>> {

    private SelectedCategoryListAdapter selectedListAdapter;


    public SetCategoryListAdapter(int layoutResId, int sectionHeadResId, SelectedCategoryListAdapter selectedListAdapter) {
        super(layoutResId, sectionHeadResId, null);
        this.selectedListAdapter = selectedListAdapter;
        if (null != selectedListAdapter) {
            selectedListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    selectedListAdapter.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity<CategoryItemBean> item) {
        helper.setText(R.id.mInfoTextView, item.header);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SectionEntity<CategoryItemBean> item) {
        helper.setText(R.id.mNameTextView, item.t.getName());

        //设置二级列表
        SetCategorySecondListAdapter setCategoryListAdapter = new SetCategorySecondListAdapter(R.layout.item_set_category_second, selectedListAdapter);
        setCategoryListAdapter.setNewData(item.t.getChildList());
        RecyclerView recyclerView = helper.getView(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setAdapter(setCategoryListAdapter);

        //监听复选框
        CheckBox checkBox = helper.getView(R.id.mCheckBox);
        if (null != selectedListAdapter) {
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(selectedListAdapter.getData().contains(item.t));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedListAdapter.removeItem(item.t);
                        selectedListAdapter.addListBottom(item.t);
                    } else {
                        selectedListAdapter.removeItem(item.t);
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
