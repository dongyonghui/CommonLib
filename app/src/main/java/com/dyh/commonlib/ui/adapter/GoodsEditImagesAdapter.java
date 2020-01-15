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
 * 描述：编辑商品图片列表
 */
public class GoodsEditImagesAdapter extends BaseQuickRecyclerViewAdapter<String> {

    public static final String ITEM_ADD = "item_add";

    public GoodsEditImagesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.mDeleteImageButton)
                .addOnClickListener(R.id.mImageView);
        //如果是添加按钮
        if (ITEM_ADD.equals(item)) {
            helper.setGone(R.id.mDeleteImageButton, false);//不展示删除按钮
            helper.setImageResource(R.id.mImageView, R.mipmap.add_grey);
        } else {
            helper.setGone(R.id.mDeleteImageButton, true);
            EasyGlide.loadImage(mContext, CommonImagesUtil.getFullImageHttpUrl(item), helper.getView(R.id.mImageView));
        }
    }
}
