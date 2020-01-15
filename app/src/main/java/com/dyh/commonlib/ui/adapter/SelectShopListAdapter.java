package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：选择店铺列表适配器
 */
public class SelectShopListAdapter extends BaseQuickRecyclerViewAdapter<ShopItemBean> {

    public interface OnItemCheckedChangedListener {
        void onCheckedChanged(ShopItemBean item, boolean isChecked);
    }

    private OnItemCheckedChangedListener onItemCheckedChangedListener;

    private SelectedShopListAdapter selectedListAdapter;

    public SelectShopListAdapter(int layoutResId, SelectedShopListAdapter selectedListAdapter) {
        super(layoutResId);
        this.selectedListAdapter = selectedListAdapter;
        if (null != selectedListAdapter) {
            selectedListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopItemBean shopItemBean = selectedListAdapter.getItem(position);
                    selectedListAdapter.remove(position);
                    notifyDataSetChanged();
                    if (null != onItemCheckedChangedListener) {
                        onItemCheckedChangedListener.onCheckedChanged(shopItemBean, false);
                    }
                }
            });
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopItemBean item) {
        helper.setText(R.id.mNameTextView, item.getName())
                .setText(R.id.mAddressTextView, item.getAddress())
                .setText(R.id.mNoTextView, item.getShopId());

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

                    if (null != onItemCheckedChangedListener) {
                        onItemCheckedChangedListener.onCheckedChanged(item, isChecked);
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

    public OnItemCheckedChangedListener getOnItemCheckedChangedListener() {
        return onItemCheckedChangedListener;
    }

    public void setOnItemCheckedChangedListener(OnItemCheckedChangedListener onItemCheckedChangedListener) {
        this.onItemCheckedChangedListener = onItemCheckedChangedListener;
    }
}
