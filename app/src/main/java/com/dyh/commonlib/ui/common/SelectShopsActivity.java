package com.dyh.commonlib.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.presenter.SelectShopsPresenter;
import com.dyh.commonlib.ui.adapter.SelectShopListAdapter;
import com.dyh.commonlib.ui.adapter.SelectedShopListAdapter;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;

/**
 * 选择店铺
 */
public class SelectShopsActivity extends BaseMVPActivity implements IPageView<ShopItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSelectedItemRecyclerView)
    RecyclerView mSelectedItemRecyclerView;
    @BindView(R.id.mSelectAllCheckBox)
    CheckBox mSelectAllCheckBox;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;

    private SelectShopsPresenter mPresenter = new SelectShopsPresenter(this);
    private SelectedShopListAdapter selectedListAdapter = new SelectedShopListAdapter(R.layout.item_selected_item);
    private SelectShopListAdapter selectListAdapter = new SelectShopListAdapter(R.layout.item_select_shop, selectedListAdapter);
    private final CompoundButton.OnCheckedChangeListener selectAllListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            selectedListAdapter.clearList();
            if (isChecked) {
                selectedListAdapter.addListBottom(selectListAdapter.getData());
            }
            selectListAdapter.notifyDataSetChanged();
        }
    };


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_shops;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        // 添加已选中的选项
        String json = getIntent().getStringExtra(IntentConstants.KEY_SELECTED_SHOP_LIST_RESULT_JSON);
        if (!TextUtils.isEmpty(json)) {
            List<ShopItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<ShopItemBean>>() {
            }.getType());
            selectedListAdapter.setNewData(selectedList);
        }

        //设置全选监听
        mSelectAllCheckBox.setOnCheckedChangeListener(selectAllListener);
        selectListAdapter.setOnItemCheckedChangedListener(new SelectShopListAdapter.OnItemCheckedChangedListener() {
            @Override
            public void onCheckedChanged(ShopItemBean item, boolean isChecked) {
                refreshCheckAllStatus();
            }
        });

        //保存
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        save();
                        break;
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(selectListAdapter);
        selectListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);
        //上拉加载更多
        selectListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadList();
            }
        }, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshList(mNameEditText.getText().toString());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //搜索内容改变后自动搜索
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.refreshList(mNameEditText.getText().toString());
            }
        });

        //设置选中的列表
        mSelectedItemRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSelectedItemRecyclerView.setAdapter(selectedListAdapter);

        mPresenter.refreshList(mNameEditText.getText().toString());
    }

    /**
     * 刷新全选状态
     */
    private void refreshCheckAllStatus() {
        mSelectAllCheckBox.setOnCheckedChangeListener(null);
        List<ShopItemBean> shopItemBeanList = selectListAdapter.getData();
        List<ShopItemBean> shopItemBeanList2 = selectedListAdapter.getData();
        boolean isCheckedAll = true;
        if (shopItemBeanList.size() == shopItemBeanList2.size()) {
            for (ShopItemBean shopItemBean : shopItemBeanList) {
                if (!shopItemBeanList2.contains(shopItemBean)) {
                    isCheckedAll = false;
                    break;
                }
            }
        } else {
            isCheckedAll = false;
        }
        mSelectAllCheckBox.setChecked(isCheckedAll);
        mSelectAllCheckBox.setOnCheckedChangeListener(selectAllListener);
    }

    private void save() {
        List<ShopItemBean> list = selectedListAdapter.getData();
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.KEY_SELECTED_SHOP_LIST_RESULT_JSON, JsonUtils.toJson(list));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showList(List<ShopItemBean> list) {
        selectListAdapter.setNewData(list);
        refreshCheckAllStatus();
    }

    @Override
    public void appendList(List<ShopItemBean> list) {
        selectListAdapter.addListBottom(list);
        refreshCheckAllStatus();
    }
}
