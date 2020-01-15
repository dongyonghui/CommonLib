package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：配送地址列表
 */
public class DeliveryAddressListAdapter extends BaseQuickRecyclerViewAdapter<DeliveryAddressManageItemBean> {
    public DeliveryAddressListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeliveryAddressManageItemBean item) {

        String sendMode = null;
        String takePoints = item.getTakePointsNameInfo();
        if (ServerConstants.DeliveryAddressUseType.TAKE_SELF.equals(item.getUseType())) {
            sendMode = mContext.getString(R.string.takeSelf);
        } else if (ServerConstants.DeliveryAddressUseType.SEND2HOME.equals(item.getUseType())) {
            sendMode = mContext.getString(R.string.send2home);
        }

        helper.setText(R.id.mAddressNameTextView, item.getRemark())//地址名称
                .setText(R.id.mDetailAddressTextView, item.getAddress())//详细地址
                .setTextColor(R.id.mStatusTextView, ServerConstants.DELIVERY_ADDRESS_USE_STATUS_USED.equals(item.getUseStatus())
                        ? mContext.getResources().getColor(R.color.text_blue) : mContext.getResources().getColor(R.color.text_red))
                .setText(R.id.mStatusTextView,
                        ServerConstants.DELIVERY_ADDRESS_USE_STATUS_USED.equals(item.getUseStatus())
                                ? mContext.getString(R.string.openAddressAlready)
                                : mContext.getString(R.string.formatString_close2Date, item.getOpenTime()))//地址状态
                .setGone(R.id.mSendTypeTextView, !TextUtils.isEmpty(sendMode))//配送模式
                .setText(R.id.mSendTypeTextView, sendMode)//配送模式
                .setGone(R.id.mTakePointTextView, !TextUtils.isEmpty(takePoints))//取餐点
                .setText(R.id.mTakePointTextView, mContext.getString(R.string.formatString_takePoints, takePoints))//取餐点
                .setText(R.id.mDeliveryManTextView, mContext.getString(R.string.formatString_bindDeliveryMan, item.getDeliverysInfo()))//绑定骑手
                .setText(R.id.mOpenOrCloseTextView,
                        ServerConstants.DELIVERY_ADDRESS_USE_STATUS_USED.equals(item.getUseStatus())
                                ? mContext.getString(R.string.closeAddress) : mContext.getString(R.string.openAddress))
                .addOnClickListener(R.id.mOpenOrCloseTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
