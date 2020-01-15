package com.dyh.commonlib.ui.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetCityTaskRequestBean;
import com.dyh.commonlib.entity.response.BannerItemBean;
import com.dyh.commonlib.presenter.BannerManagePresenter;
import com.dyh.commonlib.ui.adapter.BannerListAdapter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Banner管理
 */
public class BannerManageActivity extends BaseMVPActivity implements IDefaultManageView<BannerItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BannerManagePresenter mPresenter = new BannerManagePresenter(this);
    private BannerListAdapter mListAdapter;
    private final GetCityTaskRequestBean mRequestBean = new GetCityTaskRequestBean();
    private final int REQUEST_CODE_EDIT = 100;//编辑

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_EDIT == requestCode && RESULT_OK == resultCode) {
            refreshList();
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_banner_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        readyGoForResult(AddOrEditBannerActivity.class, REQUEST_CODE_EDIT);
                        break;
                }
            }
        });

        //初始化列表
        mListAdapter = new BannerListAdapter(R.layout.item_banner_manage);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        //加载更多
        mListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadList(mRequestBean);
            }
        }, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //设置列表项按钮点击监听
        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.mEditTextView://编辑
                        Bundle bundle = new Bundle();
                        bundle.putString(IntentConstants.KEY_BANNER_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                        readyGoForResult(AddOrEditBannerActivity.class, REQUEST_CODE_EDIT, bundle);
                        break;
                    case R.id.mDeleteTextView://删除
                        mPresenter.deleteCityTask(mListAdapter.getItem(position));
                        break;
                }
            }
        });

        refreshList();
    }

    /**
     * 刷新列表
     */
    private void refreshList() {
        mPresenter.refreshList(mRequestBean);//刷新列表

    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        refreshList();
    }

    @Override
    public void showList(List<BannerItemBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<BannerItemBean> list) {
        mListAdapter.addListBottom(list);
    }
}
