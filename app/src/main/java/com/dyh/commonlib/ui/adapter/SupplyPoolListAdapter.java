package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolManageItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：供给池列表
 */
public class SupplyPoolListAdapter extends BaseQuickRecyclerViewAdapter<SupplyPoolManageItemBean> {
    public SupplyPoolListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SupplyPoolManageItemBean item) {

        List<ShopItemBean> shopItemBeanList = item.getShopList();
        StringBuilder shopsInfo = new StringBuilder();
        if (null != shopItemBeanList) {
            for (int i = 0; i < shopItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                shopsInfo.append(shopItemBeanList.get(i).getName()).append(";");
            }
        }

        StringBuilder deliveryAddressInfo = new StringBuilder();
        List<DeliveryAddressManageItemBean> addressManageItemBeanList = item.getDeliveryAddressList();
        if (null != addressManageItemBeanList) {
            for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                deliveryAddressInfo.append(addressManageItemBeanList.get(i).getRemark()).append(";");
            }
        }


        helper.setText(R.id.mNameTextView, item.getName())//名称
                .setTextColor(R.id.mNameTextView, item.isIsUsualPool()
                        ? mContext.getResources().getColor(R.color.text_red) : mContext.getResources().getColor(R.color.text_black))
                .setText(R.id.mCityTextView, item.getCity())//
                .setText(R.id.mManagerTextView,
                        mContext.getString(R.string.formatString_nameAndPhone, item.getPersonName(), item.getPhone()))//负责人
                .setText(R.id.mShopsTextView, shopsInfo.toString())//店铺
                .setText(R.id.mDeliverAddressTextView, deliveryAddressInfo.toString())//配送地址
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
