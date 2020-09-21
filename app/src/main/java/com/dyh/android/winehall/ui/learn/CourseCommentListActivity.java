package com.dyh.android.winehall.ui.learn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.entity.response.CourseCommentItemResponseBean;
import com.dyh.android.winehall.entity.response.CourseDetailResponseBean;
import com.dyh.android.winehall.presenter.CourseCommentListPresenter;
import com.dyh.android.winehall.ui.adapter.CourseCommentListAdapter;
import com.dyh.android.winehall.view.ICourseCommentListView;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.materialratingbar.MaterialRatingBar;
import com.dyh.common.lib.weigit.refresh_layout.SmartRefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnLoadMoreListener;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnRefreshListener;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/18 0018
 * description:课程详情--全部评价列表
 */
public class CourseCommentListActivity extends BaseMVPActivity implements ICourseCommentListView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    //逻辑处理
    private final CourseCommentListPresenter mCourseCommentListPresenter
            = new CourseCommentListPresenter(this);

    //列表适配器
    private final CourseCommentListAdapter mCourseCommentListAdapter
            = new CourseCommentListAdapter(R.layout.item_course_comment_list);


    private String mDetailId;           //课程ID
    private String mCount;              //总数
    private String mScore;              //总分数

    /**
     * 构建传递的参数
     *
     * @param courseDetailResponseBean
     * @return
     */
    public static Bundle buildIntentData(CourseDetailResponseBean courseDetailResponseBean) {
        Bundle bundle = new Bundle();
        if (null != courseDetailResponseBean) {
            bundle.putString(IntentConstants.KEY_DETAIL_ID_STRING, courseDetailResponseBean.getCourse_id());
            bundle.putString(IntentConstants.KEY_SCORE_STRING, courseDetailResponseBean.getScore());
            bundle.putString(IntentConstants.KEY_COUNT_STRING, courseDetailResponseBean.getComment_count());
        }
        return bundle;
    }

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_comment_list;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        mDetailId = getIntent().getStringExtra(IntentConstants.KEY_DETAIL_ID_STRING);
        mCount = getIntent().getStringExtra(IntentConstants.KEY_COUNT_STRING);
        mScore = getIntent().getStringExtra(IntentConstants.KEY_SCORE_STRING);

        initListView();
        initRefreshView();

        mCourseCommentListPresenter.refreshList(mDetailId);
    }

    @Override
    public void hideLoadingView() {
        super.hideLoadingView();
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }

    private void initRefreshView() {
        mSmartRefreshLayout.setNoMoreData(false);
        //下拉刷新
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mCourseCommentListPresenter.refreshList(mDetailId);
            }
        });

        //上拉加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mCourseCommentListPresenter.loadMoreList();
            }
        });
    }

    private void initListView() {
        //初始化课节列表
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        //添加头部信息
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_course_comment_list_header, null);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerView);
        float score = 0;
        try {
            score = Float.parseFloat(mScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        headerViewHolder.mMaterialRatingBar.setRating(score);
        headerViewHolder.mScoreTextView.setText(mScore);
        headerViewHolder.mCountTextView.setText(MessageFormat.format("({0})", mCount));
        mCourseCommentListAdapter.addHeaderView(headerView);


        mCourseCommentListAdapter.setGeneralEmptyView(getString(R.string.empty_view_hint), mRecyclerView);
        mRecyclerView.setAdapter(mCourseCommentListAdapter);
    }

    @Override
    public void onRefreshPageSuccess(List<CourseCommentItemResponseBean> list, boolean isLastPage) {
        mCourseCommentListAdapter.setNewData(list);
        enableLoadMoreView(isLastPage);
    }

    @Override
    public void onLoadMorePageSuccess(List<CourseCommentItemResponseBean> list, boolean isLastPage) {
        mCourseCommentListAdapter.addListBottom(list);
        enableLoadMoreView(isLastPage);
    }


    /**
     * 设置加载下一页状态
     *
     * @param isLastPage
     */
    private void enableLoadMoreView(boolean isLastPage) {
        mSmartRefreshLayout.setNoMoreData(isLastPage);
    }


    protected class HeaderViewHolder {
        private TextView mTitleTextView;
        private MaterialRatingBar mMaterialRatingBar;
        private TextView mScoreTextView;
        private TextView mCountTextView;

        public HeaderViewHolder(View view) {
            mTitleTextView = (TextView) view.findViewById(R.id.mTitleTextView);
            mMaterialRatingBar = (MaterialRatingBar) view.findViewById(R.id.mMaterialRatingBar);
            mScoreTextView = (TextView) view.findViewById(R.id.mScoreTextView);
            mCountTextView = (TextView) view.findViewById(R.id.mCountTextView);
        }
    }
}
