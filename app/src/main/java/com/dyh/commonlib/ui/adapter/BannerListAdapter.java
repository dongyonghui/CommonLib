package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.response.BannerItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：Tpc列表
 */
public class BannerListAdapter extends BaseQuickRecyclerViewAdapter<BannerItemBean> {
    public BannerListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BannerItemBean item) {
        StringBuilder deliveryAddressInfo = new StringBuilder();
        List<DeliveryAddressManageItemBean> addressManageItemBeanList = item.getDeliveryAddress();
        if (null != addressManageItemBeanList) {
            for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                deliveryAddressInfo.append(addressManageItemBeanList.get(i).getRemark()).append(";");
            }
        }
        helper.setText(R.id.mClickedEffectTextView, item.getToUrl())//
                .setText(R.id.mStatusTextView, ServerConstants.BANNER_TYPE.IN_APP_PAGES.equals(item.getType())
                        ? R.string.inAppPages : R.string.webUrl)//跳转类型
                .setText(R.id.mDeliverAddressTextView, deliveryAddressInfo.toString())//配送地址
                .addOnClickListener(R.id.mEditTextView)
                .addOnClickListener(R.id.mDeleteTextView);

        //商品图片
        Glide.with(mContext).load(CommonImagesUtil.getFullImageHttpUrl(item.getImageUrl())).into((ImageView) helper.getView(R.id.mImageView));
    }
}
