package com.dyh.android.winehall.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.presenter.HomeLearnViewPagerItemPresenter;
import com.dyh.android.winehall.ui.adapter.HomeLearnCourseListAdapter;
import com.dyh.android.winehall.ui.learn.CourseDetailActivity;
import com.dyh.android.winehall.view.IBasePageView;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.refresh_layout.SmartRefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnLoadMoreListener;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnRefreshListener;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import butterknife.BindView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 10:11
 * description: 首页学酒模块ViewPager单项页面
 */
public class HomeLearnViewPagerItemFragment extends BaseMVPFragment implements IBasePageView<HomeLearnCourseItemResponseBean> {

    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private HomeLearnCourseListAdapter mHomeLearnCourseListAdapter; //课程列表适配器

    //逻辑处理
    private HomeLearnViewPagerItemPresenter mPresenter =
            new HomeLearnViewPagerItemPresenter(this);
    private String mCategoryId;                     //分类数据

    /**
     * 构建传递的参数
     *
     * @param categoryId
     * @return
     */
    public static Bundle buildIntentData(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_DETAIL_ID_STRING, categoryId);
        return bundle;
    }

    @Override
    protected void lazyLoad(boolean isFirst) {
        super.lazyLoad(isFirst);
        mPresenter.refreshList(mCategoryId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_learn_view_pager;
    }

    @Override
    public void hideLoadingView() {
        super.hideLoadingView();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle)
            this.mCategoryId = bundle.getString(IntentConstants.KEY_DETAIL_ID_STRING);

        initListViews();
        initRefreshLoadViews();
    }

    /**
     * 初始化列表
     */
    private void initListViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeLearnCourseListAdapter = new HomeLearnCourseListAdapter(getContext());
        mHomeLearnCourseListAdapter.setEmptyView(R.layout.common_lib_empty_view, mRecyclerView);
        mRecyclerView.setAdapter(mHomeLearnCourseListAdapter);

        //点击列表事件处理
        mHomeLearnCourseListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = CourseDetailActivity.buildIntentData(mHomeLearnCourseListAdapter.getItem(position));
                readyGo(CourseDetailActivity.class, bundle);
            }
        });
    }

    /**
     * 初始化分页组件
     */
    private void initRefreshLoadViews() {
        mSmartRefreshLayout.setNoMoreData(true);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.refreshList(mCategoryId);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadMoreList();
            }
        });
    }

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }

    @Override
    public void onRefreshPageSuccess(List<HomeLearnCourseItemResponseBean> list, boolean isLastPage) {
        mSmartRefreshLayout.setNoMoreData(isLastPage);
        if (null == mHomeLearnCourseListAdapter) {
            initListViews();
        }

        mHomeLearnCourseListAdapter.setNewData(list);
    }

    @Override
    public void onLoadMorePageSuccess(List<HomeLearnCourseItemResponseBean> list, boolean isLastPage) {
        if (null == mHomeLearnCourseListAdapter) return;

        mSmartRefreshLayout.setNoMoreData(isLastPage);

        mHomeLearnCourseListAdapter.addListBottom(list);
    }
}
