package com.dyh.commonlib.ui.supply_pool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetSupplyPoolRequestBean;
import com.dyh.commonlib.entity.response.SupplyPoolManageItemBean;
import com.dyh.commonlib.presenter.SupplyPoolManagePresenter;
import com.dyh.commonlib.ui.adapter.SupplyPoolListAdapter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 供给池管理
 */
public class SupplyPoolManageActivity extends BaseMVPActivity implements IDefaultManageView<SupplyPoolManageItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;

    private SupplyPoolManagePresenter mPresenter = new SupplyPoolManagePresenter(this);
    private SupplyPoolListAdapter mListAdapter;
    private final GetSupplyPoolRequestBean mRequestBean = new GetSupplyPoolRequestBean();
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
        return R.layout.activity_supply_pool_manage;
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
                        readyGoForResult(AddOrEditSupplyPoolActivity.class, REQUEST_CODE_EDIT);
                        break;
                }
            }
        });

        //初始化列表
        mListAdapter = new SupplyPoolListAdapter(R.layout.item_supply_pool_manage);
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

        //设置列表项点击事件
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(IntentConstants.KEY_SUPPLY_POOL_MANAGE_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                readyGoForResult(SupplyPoolDetailActivity.class, REQUEST_CODE_EDIT, bundle);
            }
        });

        //自动搜索
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                refreshList();
            }
        });

        //设置列表项按钮点击监听
        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.mDeleteTextView://删除
                        mPresenter.deleteDeliverAddress(mListAdapter.getItem(position));
                        break;
                }
            }
        });

        refreshList();
    }

    private void refreshList() {
        //根据初始化的筛选条件刷新列表
        mRequestBean.name = mNameEditText.getText().toString();
        mPresenter.refreshList(mRequestBean);//刷新列表

    }

    @OnClick(R.id.mSearchTextView)
    public void onSearchButtonClicked() {
        refreshList();
    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        refreshList();
    }

    @Override
    public void showList(List<SupplyPoolManageItemBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<SupplyPoolManageItemBean> list) {
        mListAdapter.addListBottom(list);
    }
}
