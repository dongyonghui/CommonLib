package com.dyh.commonlib.ui.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.LocalPrintDeliveryListBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;


/**
 * 作者：董永慧
 * 邮箱：648731994@qq.com
 * 时间：2019/5/7/007 15:46
 * 配送地址管理列表
 */
public class PrinterDeliveryDetailListAdapter extends BaseQuickRecyclerViewAdapter<LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean> {
    public interface OnItemCheckedChangedListener {
        void onCheckedChanged(LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean item, boolean isChecked);
    }
    
    private OnItemCheckedChangedListener onItemCheckedChangedListener;
    public PrinterDeliveryDetailListAdapter(int layoutResId) {
        super(layoutResId);
    }

    public OnItemCheckedChangedListener getOnItemCheckedChangedListener() {
        return onItemCheckedChangedListener;
    }

    public void setOnItemCheckedChangedListener(OnItemCheckedChangedListener onItemCheckedChangedListener) {
        this.onItemCheckedChangedListener = onItemCheckedChangedListener;
    }

    @Override
    protected void convert(BaseViewHolder holder, LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean item) {

        CheckBox checkBox = holder.getView(R.id.mCheckBox);
        checkBox.setText(item.floor + "层");
        //设置选中状态
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(item.isChecked);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.isChecked = b;
                notifyDataSetChanged();
                if (null != onItemCheckedChangedListener) {
                    onItemCheckedChangedListener.onCheckedChanged(item, b);
                }
            }
        });
    }

    /**
     * @return true表示全选
     */
    public boolean isAllSelected() {
        if (null == mData) {
            return false;
        }
        for (LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean mDatum : mData) {
            if (!mDatum.isChecked) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置全部的选中状态
     *
     * @param isChecked
     */
    public synchronized void setAllChecked(boolean isChecked) {
        if (null == mData) {
            return;
        }
        for (LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean mDatum : mData) {
            mDatum.isChecked = isChecked;
        }
        notifyDataSetChanged();
    }
}
