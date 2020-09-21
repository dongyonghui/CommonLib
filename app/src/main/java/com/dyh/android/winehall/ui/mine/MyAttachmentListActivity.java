package com.dyh.android.winehall.ui.mine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.AttachmentItemBean;
import com.dyh.android.winehall.presenter.MyAttachmentListPresenter;
import com.dyh.android.winehall.ui.adapter.MyAttachmentListAdapter;
import com.dyh.android.winehall.view.IBasePageView;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
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
 * created on: 2020/9/21 0021
 * description: 我的资料--保存资料列表
 */
public class MyAttachmentListActivity extends BaseMVPActivity implements IBasePageView<AttachmentItemBean> {
    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    //逻辑处理
    private final MyAttachmentListPresenter mCourseCommentListPresenter
            = new MyAttachmentListPresenter(this);

    //列表适配器
    private final MyAttachmentListAdapter mListAdapter
            = new MyAttachmentListAdapter(R.layout.item_my_attachment_list);

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_attachment_list;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        initListView();
        initRefreshView();

        mCourseCommentListPresenter.refreshList();
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
                mCourseCommentListPresenter.refreshList();
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

        mListAdapter.setGeneralEmptyView(getString(R.string.empty_view_hint), mRecyclerView);
        mRecyclerView.setAdapter(mListAdapter);
    }

    /**
     * 设置加载下一页状态
     *
     * @param isLastPage
     */
    private void enableLoadMoreView(boolean isLastPage) {
        mSmartRefreshLayout.setNoMoreData(isLastPage);
    }

    @Override
    public void onRefreshPageSuccess(List<AttachmentItemBean> list, boolean isLastPage) {
        mListAdapter.setNewData(list);
        enableLoadMoreView(isLastPage);
    }

    @Override
    public void onLoadMorePageSuccess(List<AttachmentItemBean> list, boolean isLastPage) {
        mListAdapter.addListBottom(list);
        enableLoadMoreView(isLastPage);
    }
}
