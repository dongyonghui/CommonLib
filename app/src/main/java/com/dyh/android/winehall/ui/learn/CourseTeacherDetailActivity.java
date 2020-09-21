package com.dyh.android.winehall.ui.learn;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.common.LecturerBean;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.entity.response.TeacherDetailResponseBean;
import com.dyh.android.winehall.presenter.TeacherDetailPresenter;
import com.dyh.android.winehall.ui.adapter.TeacherCourseListAdapter;
import com.dyh.android.winehall.view.ITeacherDetailView;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.dw.util.MyStringUtil;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.refresh_layout.SmartRefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnLoadMoreListener;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnRefreshListener;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;

import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/9 0009 17:05
 * description: 课程详情--讲师详情
 */
public class CourseTeacherDetailActivity extends BaseMVPActivity implements ITeacherDetailView {

    @BindView(R.id.mLoadingContentView)
    ConstraintLayout mLoadingContentView;
    @BindView(R.id.mHeaderImageView)
    ImageView mHeaderImageView;
    @BindView(R.id.mTeacherNameTextView)
    TextView mTeacherNameTextView;
    @BindView(R.id.mTeacherRemarkTextView)
    TextView mTeacherRemarkTextView;
    @BindView(R.id.mTeacherRemark2TextView)
    TextView mTeacherRemark2TextView;
    @BindView(R.id.mFollowTextView)
    TextView mFollowTextView;
    @BindView(R.id.mCourseTitleTextView)
    TextView mCourseTitleTextView;
    @BindView(R.id.mCourseCountTextView)
    TextView mCourseCountTextView;
    @BindView(R.id.mExpandAllTextView)
    TextView mExpandAllTextView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private final TeacherDetailPresenter mTeacherDetailPresenter
            = new TeacherDetailPresenter(this);

    //课节列表适配器
    private TeacherCourseListAdapter mTeacherCourseListAdapter
            = new TeacherCourseListAdapter(R.layout.item_teacher_course);

    private String mDetailId;           //讲师id
    private boolean isFollowAlready;    //是否已关注

    /**
     * 构建传递的参数
     *
     * @param lecturerBean
     * @return
     */
    public static Bundle buildIntentData(LecturerBean lecturerBean) {
        Bundle bundle = new Bundle();
        if (null != lecturerBean) {
            bundle.putString(IntentConstants.KEY_DETAIL_ID_STRING, lecturerBean.getLecturer_id());
        }
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_detail_teacher_detail;
    }

    @Override
    public int getMultipleStatusContentViewId() {
        return R.id.mLoadingContentView;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        StatusBarUtils.transparentStatusBar(getWindow());

        initListView();
        initRefreshView();

        //详情id
        mDetailId = getIntent().getStringExtra(IntentConstants.KEY_DETAIL_ID_STRING);
        mTeacherDetailPresenter.loadDetailData(mDetailId);
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
                mTeacherDetailPresenter.loadDetailData(mDetailId);
            }
        });

        //上拉加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mTeacherDetailPresenter.loadMoreList();
            }
        });
    }

    private void initListView() {
        //初始化课节列表
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mTeacherCourseListAdapter);

        //点击列表跳转到详情页
        mTeacherCourseListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                readyGo(CourseDetailActivity.class,
                        CourseDetailActivity.buildIntentData(mTeacherCourseListAdapter.getItem(position)));
            }
        });
    }

    @OnClick(R.id.mFollowTextView)
    public void followTeacher() {
        mTeacherDetailPresenter.followTeacher(mDetailId, isFollowAlready);
    }

    @OnClick(R.id.mExpandAllTextView)
    public void expandAllLessonList() {
        mExpandAllTextView.setVisibility(View.GONE);
        mTeacherDetailPresenter.refreshList();
    }

    @OnClick(R.id.mCloseImageButton)
    public void close() {
        finish();
    }

    @Override
    public void showDetail(TeacherDetailResponseBean teacherDetailResponseBean) {
        if (null == teacherDetailResponseBean) return;

        //展示顶部讲师信息
        EasyGlide.loadCircleImage(this, teacherDetailResponseBean.getAvatar_show(), mHeaderImageView, R.mipmap.person_head_default);
        mTeacherNameTextView.setText(teacherDetailResponseBean.getName());
        List<String> verifyShowArray = teacherDetailResponseBean.getVerify_show();
        String remark = UIConfig.EMPTY_DATA_DEFAULT;
        if (null != verifyShowArray && verifyShowArray.size() > 0) {
            remark = MyStringUtil.join(" / ", verifyShowArray);
        }
        mTeacherRemarkTextView.setText(remark);
        mTeacherRemark2TextView.setText(teacherDetailResponseBean.getBrief());
        refreshFollowStatus(teacherDetailResponseBean.isIs_followed());

        TeacherDetailResponseBean.CourseInfo courseInfo = teacherDetailResponseBean.getCourse();
        if (null == courseInfo) return;
        int listCount = null == courseInfo.list ? 0 : courseInfo.list.size();
        int totalCount = MathUtil.getIntegerNumber(courseInfo.total);
        //如果详情返回的列表数量已经是全部列表（即列表大小大于等于总数），表示是最后一页，则隐藏展开按钮，否则显示展开按钮
        enableLoadMoreView(listCount >= totalCount);

        //展示课节列表
        mTeacherCourseListAdapter.setNewData(courseInfo.list);
        mCourseCountTextView.setText(MessageFormat.format("({0})", (TextUtils.isEmpty(courseInfo.total) ? "0" : courseInfo.total)));
    }

    @Override
    public void refreshFollowStatus(boolean isFollowAlready) {
        this.isFollowAlready = isFollowAlready;
        mFollowTextView.setText(isFollowAlready ?
                R.string.cancelFollow : R.string.follow);
    }

    @Override
    public void onRefreshPageSuccess(List<HomeLearnCourseItemResponseBean> list, boolean isLastPage) {
        mTeacherCourseListAdapter.setNewData(list);
        enableLoadMoreView(isLastPage);
    }

    @Override
    public void onLoadMorePageSuccess(List<HomeLearnCourseItemResponseBean> list, boolean isLastPage) {
        mTeacherCourseListAdapter.addListBottom(list);
        enableLoadMoreView(isLastPage);
    }


    /**
     * 设置加载下一页状态
     *
     * @param isLastPage
     */
    private void enableLoadMoreView(boolean isLastPage) {
        mSmartRefreshLayout.setNoMoreData(isLastPage);
        if (isLastPage) {
            mExpandAllTextView.setVisibility(View.GONE);
        } else {
            mExpandAllTextView.setVisibility(View.VISIBLE);
        }
    }
}
