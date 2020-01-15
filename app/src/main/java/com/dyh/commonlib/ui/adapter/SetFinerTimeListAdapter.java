package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.FinerTimeItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：选择优选时间列表适配器
 */
public class SetFinerTimeListAdapter extends BaseQuickRecyclerViewAdapter<FinerTimeItemBean> {

    private SelectedFinerTimeListAdapter selectedFinerTimeListAdapter;

    public SetFinerTimeListAdapter(int layoutResId, SelectedFinerTimeListAdapter selectedFinerTimeListAdapter) {
        super(layoutResId);
        this.selectedFinerTimeListAdapter = selectedFinerTimeListAdapter;
        if (null != selectedFinerTimeListAdapter) {
            selectedFinerTimeListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    selectedFinerTimeListAdapter.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FinerTimeItemBean item) {
        helper.setText(R.id.mNameTextView, item.getName())
                .setText(R.id.mTimeTextView, item.getEndTimeStr() + "-" + item.getSendTimeStr())
                .setText(R.id.mCityTextView, item.getCity());

        //监听复选框
        CheckBox checkBox = helper.getView(R.id.mCheckBox);
        if (null != selectedFinerTimeListAdapter) {
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(selectedFinerTimeListAdapter.getData().contains(item));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedFinerTimeListAdapter.removeItem(item);
                        selectedFinerTimeListAdapter.addListBottom(item);
                    } else {
                        selectedFinerTimeListAdapter.removeItem(item);
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
