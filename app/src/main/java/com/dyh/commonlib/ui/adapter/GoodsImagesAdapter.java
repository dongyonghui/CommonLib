package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：商品图片列表
 */
public class GoodsImagesAdapter extends BaseQuickRecyclerViewAdapter<String> {
    public GoodsImagesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        EasyGlide.loadImage(mContext, CommonImagesUtil.getFullImageHttpUrl(item), helper.getView(R.id.mImageView));
    }
}
