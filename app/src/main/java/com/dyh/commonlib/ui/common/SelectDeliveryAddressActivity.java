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
import com.dyh.commonlib.entity.request.GetDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.presenter.SelectDeliveryAddressPresenter;
import com.dyh.commonlib.ui.adapter.SelectDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.adapter.SelectedDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;

/**
 * 选择配送地址
 */
public class SelectDeliveryAddressActivity extends BaseMVPActivity implements IPageView<DeliveryAddressManageItemBean> {

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

    private SelectDeliveryAddressPresenter mPresenter = new SelectDeliveryAddressPresenter(this);
    private SelectedDeliveryAddressListAdapter selectedListAdapter = new SelectedDeliveryAddressListAdapter(R.layout.item_selected_item);
    private SelectDeliveryAddressListAdapter selectListAdapter = new SelectDeliveryAddressListAdapter(R.layout.item_select_delivery_address, selectedListAdapter);
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
        String json = getIntent().getStringExtra(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON);
        if (!TextUtils.isEmpty(json)) {
            List<DeliveryAddressManageItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<DeliveryAddressManageItemBean>>() {
            }.getType());
            selectedListAdapter.setNewData(selectedList);
        }

        //设置全选监听
        mSelectAllCheckBox.setOnCheckedChangeListener(selectAllListener);
        selectListAdapter.setOnItemCheckedChangedListener(new SelectDeliveryAddressListAdapter.OnItemCheckedChangedListener() {
            @Override
            public void onCheckedChanged(DeliveryAddressManageItemBean item, boolean isChecked) {
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
                mSwipeRefreshLayout.setRefreshing(false);
                refreshList();
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
                refreshList();
            }
        });

        //设置选中的列表
        mSelectedItemRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSelectedItemRecyclerView.setAdapter(selectedListAdapter);

        refreshList();
    }

    private void refreshList() {
        GetDeliveryAddressRequestBean requestBean = new GetDeliveryAddressRequestBean();
        requestBean.address = mNameEditText.getText().toString();
        mPresenter.refreshList(requestBean);
    }

    /**
     * 刷新全选状态
     */
    private void refreshCheckAllStatus() {
        mSelectAllCheckBox.setOnCheckedChangeListener(null);
        List<DeliveryAddressManageItemBean> list = selectListAdapter.getData();
        List<DeliveryAddressManageItemBean> selectedList = selectedListAdapter.getData();
        boolean isCheckedAll = true;
        if (list.size() == selectedList.size()) {
            for (DeliveryAddressManageItemBean itemBean : list) {
                if (!selectedList.contains(itemBean)) {
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
        List<DeliveryAddressManageItemBean> list = selectedListAdapter.getData();
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON, JsonUtils.toJson(list));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showList(List<DeliveryAddressManageItemBean> list) {
        selectListAdapter.setNewData(list);
        refreshCheckAllStatus();
    }

    @Override
    public void appendList(List<DeliveryAddressManageItemBean> list) {
        selectListAdapter.addListBottom(list);
        refreshCheckAllStatus();
    }
}
