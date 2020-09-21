package com.dyh.android.winehall.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.common.LessonsBean;
import com.dyh.android.winehall.entity.local.LocalCourseCommentTotalInfoBean;
import com.dyh.android.winehall.entity.local.LocalCourseDetailBottomInfoBean;
import com.dyh.android.winehall.entity.response.CourseCommentItemResponseBean;
import com.dyh.android.winehall.util.MyTimeFormatUtil;
import com.dyh.android.winehall.weigit.RatingBarView;
import com.dyh.common.lib.dw.listview.BaseMultiQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.weigit.materialratingbar.MaterialRatingBar;
import com.dyh.common.lib.weigit.powertext.LabelTextView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/8 0008 16:32
 * description: 课程详情底部信息列表
 */
public class CourseDetailBottomInfoListAdapter extends BaseMultiQuickRecyclerViewAdapter<LocalCourseDetailBottomInfoBean> {

    public CourseDetailBottomInfoListAdapter(Context context) {
        super(context);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_TILE_DEFAULT, R.layout.item_course_detail_title);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT, R.layout.item_course_detail_comments_title);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT_FOOTER, R.layout.item_course_detail_comments_footer);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_LESSON, R.layout.item_course_detail_lesson);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_HR, R.layout.item_course_detail_hr);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_TEXT, R.layout.item_course_detail_text);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_IMAGE, R.layout.item_course_detail_image);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H2, R.layout.item_course_detail_h2);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H1, R.layout.item_course_detail_h1);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COMMENT, R.layout.item_course_detail_comment);
        addItemType(LocalCourseDetailBottomInfoBean.TYPE_SHOW_ALL_LESSONS, R.layout.item_course_detail_more_course);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        switch (helper.getItemViewType()) {
            case LocalCourseDetailBottomInfoBean.TYPE_TILE_DEFAULT:
                showTitleInfo(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT:
                showCommentTitleInfo(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT_FOOTER:
                showCommentFooterInfo(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_LESSON:
                showLessonInfo(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H1:
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H2:
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_TEXT:
                showDetailText(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_IMAGE:
                showDetailImage(helper, item);
                break;
            case LocalCourseDetailBottomInfoBean.TYPE_ITEM_COMMENT:
                showCommentInfo(helper, item);
                break;
        }
    }

    private void showCommentInfo(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof CourseCommentItemResponseBean) {
            CourseCommentItemResponseBean commentInfoBean = (CourseCommentItemResponseBean) item.data;
            CourseCommentItemResponseBean.UserBean userBean = commentInfoBean.getUser();
            if (null != userBean) {
                EasyGlide.loadCircleImage(mContext, userBean.getAvatar_show(), helper.getView(R.id.mHeaderImageView));
                helper.setText(R.id.mNickNameTextView, userBean.getNickname());
            }
            RatingBarView mRatingBar = helper.getView(R.id.mRatingBarView);
            int score = 0;
            try {
                score = MathUtil.getIntegerNumber(commentInfoBean.getScore());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mRatingBar.setStar(score, false);
            helper.setText(R.id.mCommentTextView, commentInfoBean.getContent())
                    .setText(R.id.mScoreTextView, commentInfoBean.getScore())
                    .setText(R.id.mTimeTextView, commentInfoBean.getCreated_at());
        }
    }

    private void showDetailImage(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof String) {
            helper.setImageUrl(R.id.mImageView, (String) item.data);
        }
    }

    private void showDetailText(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        helper.setText(R.id.mTextView, item.data.toString());
    }

    private void showLessonInfo(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof LessonsBean) {
            LessonsBean lessonsBean = (LessonsBean) item.data;
            LabelTextView mTitleLabelTextView = helper.getView(R.id.mTitleLabelTextView);
            mTitleLabelTextView.setOriginalText(lessonsBean.getTitle());
            mTitleLabelTextView.setLabelText(mContext.getString(R.string.tryAndSee));
            helper
                    .setText(R.id.mCourseDurationTextView,
                            MyTimeFormatUtil.getDurationTimeString(MathUtil.getIntegerNumber(lessonsBean.getAudition())))
                    .setGone(R.id.mLineBottom, !lessonsBean.isLastOne);
        }
    }

    private void showTitleInfo(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof LocalCourseDetailBottomInfoBean.TitleDefaultData) {
            LocalCourseDetailBottomInfoBean.TitleDefaultData titleDefaultData = (LocalCourseDetailBottomInfoBean.TitleDefaultData) item.data;
            helper.setText(R.id.mTitleTextView, titleDefaultData.title)
                    .setText(R.id.mCountTextView, titleDefaultData.countInfo);
        }
    }

    private void showCommentTitleInfo(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof LocalCourseCommentTotalInfoBean) {
            LocalCourseCommentTotalInfoBean totalInfoBean = (LocalCourseCommentTotalInfoBean) item.data;
            helper.setText(R.id.mScoreTextView, totalInfoBean.score)
                    .setText(R.id.mCountTextView, totalInfoBean.countInfo);

            MaterialRatingBar mMaterialRatingBar = helper.getView(R.id.mMaterialRatingBar);
            float score = 0;
            try {
                score = Float.parseFloat(totalInfoBean.score);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mMaterialRatingBar.setRating(score);
        }
    }

    private void showCommentFooterInfo(BaseViewHolder helper, LocalCourseDetailBottomInfoBean item) {
        if (null == item || null == item.data) return;
        if (item.data instanceof String) {
            helper.setText(R.id.mTitleTextView, (String) item.data);
        }
    }
}
