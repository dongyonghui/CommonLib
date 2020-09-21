package com.dyh.android.winehall.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.common.LecturerBean;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.common.lib.dw.listview.BaseMultiQuickRecyclerViewAdapter;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 13:26
 * description: 首页课程列表适配器
 */
public class HomeLearnCourseListAdapter extends BaseMultiQuickRecyclerViewAdapter<HomeLearnCourseItemResponseBean> {


    public HomeLearnCourseListAdapter(Context context) {
        super(context);
        addItemType(HomeLearnCourseItemResponseBean.TYPE_IMAGE_COUNT, R.layout.item_home_learn_course);
        addItemType(HomeLearnCourseItemResponseBean.TYPE_IMAGE_ARRAY, R.layout.item_home_learn_course2);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeLearnCourseItemResponseBean item) {
        String flagImageUrl = item.getFirstFlagImageUrl();
        //设置共通信息
        helper.setImageUrl(R.id.mFlagImageView, flagImageUrl)
                .setGone(R.id.mFlagImageView, !TextUtils.isEmpty(flagImageUrl))
                .setText(R.id.mTitleTextView, item.getTitle())
                .setText(R.id.mRemarkTextView, item.getRemarkInfo())
                .setText(R.id.mSummaryTextView, item.getBrief());

        //设置讲师信息
        LecturerBean lecturerBean = item.getLecturer();
        ImageView headerImageView = helper.getView(R.id.mHeaderImageView);
        if (null != lecturerBean) {
            EasyGlide.loadCircleImage(MyApplication.getInstance(), lecturerBean.getAvatar_show(), headerImageView, R.mipmap.person_head_default);
            helper.setText(R.id.mTeacherNameTextView, lecturerBean.getName())
                    .setText(R.id.mTeacherRemarkTextView, lecturerBean.getBrief());
        } else {
            headerImageView.setImageResource(R.mipmap.person_head_default);
            helper.setText(R.id.mTeacherNameTextView, UIConfig.EMPTY_DATA_DEFAULT)
                    .setText(R.id.mTeacherRemarkTextView, UIConfig.EMPTY_DATA_DEFAULT);
        }

        switch (item.getItemType()) {
            case HomeLearnCourseItemResponseBean.TYPE_IMAGE_COUNT:
                showCountImage(helper, item.getCover_show());
                break;
            case HomeLearnCourseItemResponseBean.TYPE_IMAGE_ARRAY:
                showImageArray(helper, item.getCover_show());
                break;
        }
    }

    private void showImageArray(BaseViewHolder helper, List<String> cover_show) {
        HomeLearnCourseImageListAdapter imageListAdapter = new HomeLearnCourseImageListAdapter(R.layout.item_home_learn_course_image);
        RecyclerView recyclerView = helper.getView(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()
                , LinearLayoutManager.HORIZONTAL, false));
        imageListAdapter.setNewData(cover_show);
        recyclerView.setAdapter(imageListAdapter);
        imageListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                helper.getConvertView().performClick();
            }
        });
    }

    private void showCountImage(BaseViewHolder helper, List<String> cover_show) {
        helper.setGone(R.id.mImageView1, false)
                .setGone(R.id.mImageView2, false)
                .setGone(R.id.mImageView3, false);
        if (null == cover_show) return;
        for (int i = 0; i < cover_show.size(); i++) {
            ImageView imageView = null;
            switch (i) {
                case 0:
                    imageView = helper.getView(R.id.mImageView1);
                    break;
                case 1:
                    imageView = helper.getView(R.id.mImageView2);
                    break;
                case 2:
                    imageView = helper.getView(R.id.mImageView3);
                    break;
            }

            if (null == imageView) continue;

            imageView.setVisibility(View.VISIBLE);
            EasyGlide.loadRoundCornerImage(MyApplication.getInstance(), cover_show.get(i)
                    , UIConfig.HOME_COURSE_IMAGE_RADIUS, imageView);
        }
    }

}
