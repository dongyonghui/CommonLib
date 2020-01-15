package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.response.NoticeItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：消息公告列表
 */
public class NoticeListAdapter extends BaseQuickRecyclerViewAdapter<NoticeItemBean> {
    public NoticeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NoticeItemBean item) {
        StringBuilder deliveryAddressInfo = new StringBuilder();
        List<NoticeItemBean.AddressListBean> addressManageItemBeanList = item.getAddressList();
        if (null != addressManageItemBeanList) {
            for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                deliveryAddressInfo.append(addressManageItemBeanList.get(i).getDeliveryAddressName()).append(";");
            }
        }

        helper.setText(R.id.mNameTextView, item.getAllNameInfo())//名称
                .setTextColor(R.id.mStatusTextView, ServerConstants.NoticeStatus.PASSED.equals(item.getNoticeStatus())
                        ? mContext.getResources().getColor(R.color.text_red) : mContext.getResources().getColor(R.color.text_black))
                .setText(R.id.mStatusTextView, item.getNoticeStatusTextInfo(mContext))//
                .setText(R.id.mTimeTextView, mContext.getString(R.string.formatString_noticeShowTimes, item.getStartTimeStr(), item.getEndTimeStr()))//
                .setText(R.id.mDeliverAddressTextView, deliveryAddressInfo.toString())//
                .addOnClickListener(R.id.mEditTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
