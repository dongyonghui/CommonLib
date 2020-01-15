package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.weigit.SlantedTextView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：Tpc列表
 */
public class TpcGoodsListAdapter extends BaseQuickRecyclerViewAdapter<TpcGoodsItemBean> {
    public TpcGoodsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TpcGoodsItemBean item) {
        helper.setText(R.id.mGoodsNameTextView, item.getName())//商品名称
                .setText(R.id.mGoodsRemarkTextView, item.getDescription())//商品描述
                .setGone(R.id.mGoodsRemarkTextView, !TextUtils.isEmpty(item.getDescription()))//描述为空则隐藏组件
                .setText(R.id.mStatusTextView, item.getGoodsAuditingStatusText())//审核状态
                .setText(R.id.mShopNameTextView, mContext.getString(R.string.formatString_shopName, item.getShopName()))//店铺名称
                .setText(R.id.mLimitNumTextView, mContext.getString(R.string.formatString_limitNum4EveryDay, item.getLimitNum(), item.getPersonLimitNum()))//每日库存
                .setText(R.id.mPriceTextView, item.getPriceTextInfo())//价格信息
                .setText(R.id.mAddedPriceTextView, item.getAddedPriceTextInfo())//加价信息
                .setGone(R.id.mAddedPriceTextView, !TextUtils.isEmpty(item.getAddedPriceTextInfo()))
                .setGone(R.id.mOptionButtonRootView, TpcGoodsItemBean.AUDITING_STATUS_WAITING2AUDIT.equals(item.getAuditingStatus()))
                .addOnClickListener(R.id.mAgreeAndAddPriceTextView)
                .addOnClickListener(R.id.mRejectTextView);

        //设置指定类型商品标签
        SlantedTextView mSlantedTextView = helper.getView(R.id.mSlantedTextView);
        mSlantedTextView.setVisibility(View.GONE);
        if (item.isFirstDiscountGoods()) {
            mSlantedTextView.setVisibility(View.VISIBLE);
            mSlantedTextView.setText(R.string.firstDiscount);
            mSlantedTextView.setSlantedBackgroundColor(mContext.getResources().getColor(R.color.bg_slanted_firstDiscount));
        } else if (item.isLimitedTimeDiscountGoods()) {
            mSlantedTextView.setVisibility(View.VISIBLE);
            mSlantedTextView.setText(R.string.limitedTimeDiscount);
            mSlantedTextView.setSlantedBackgroundColor(mContext.getResources().getColor(R.color.bg_slanted_limitedTimeDiscount));
        }

        //商品图片
        EasyGlide.loadImage(mContext, CommonImagesUtil.getFullImageHttpUrl(item.getPicture()), (ImageView) helper.getView(R.id.mImageView));
    }
}
