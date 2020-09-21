package com.dyh.android.winehall.ui.adapter;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.CourseCommentItemResponseBean;
import com.dyh.android.winehall.weigit.RatingBarView;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/18 0018
 * description:课程详情--全部评价列表
 */
public class CourseCommentListAdapter extends BaseQuickRecyclerViewAdapter<CourseCommentItemResponseBean> {
    public CourseCommentListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CourseCommentItemResponseBean item) {
        CourseCommentItemResponseBean.UserBean userBean = item.getUser();
        if (null != userBean) {
            EasyGlide.loadCircleImage(mContext, userBean.getAvatar_show(), helper.getView(R.id.mHeaderImageView));
            helper.setText(R.id.mNickNameTextView, userBean.getNickname());
        }
        RatingBarView mRatingBar = helper.getView(R.id.mRatingBarView);
        int score = 0;
        try {
            score = MathUtil.getIntegerNumber(item.getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRatingBar.setStar(score, false);
        helper.setText(R.id.mCommentTextView, item.getContent())
                .setText(R.id.mScoreTextView, item.getScore())
                .setText(R.id.mTimeTextView, item.getCreated_at());
    }
}
