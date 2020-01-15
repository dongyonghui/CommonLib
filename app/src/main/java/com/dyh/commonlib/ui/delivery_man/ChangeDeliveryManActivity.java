package com.dyh.commonlib.ui.delivery_man;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetDeliveryManRequestBean;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.commonlib.presenter.DeliveryManManagePresenter;
import com.dyh.commonlib.ui.adapter.Change2DeliveryManListAdapter;
import com.dyh.commonlib.ui.view.IDeliveryManManageView;
import com.dyh.common.lib.dw.mylistener.MyTextChangeListener;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 改派骑手
 */
public class ChangeDeliveryManActivity extends BaseMVPActivity implements IDeliveryManManageView<DeliveryManItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;

    private DeliveryManManagePresenter mPresenter = new DeliveryManManagePresenter(this);
    private Change2DeliveryManListAdapter mListAdapter;
    private final GetDeliveryManRequestBean mRequestBean = new GetDeliveryManRequestBean();
    private DeliveryManItemBean itemBean;
    private boolean isNeedDeleteAfterChange = false;//是否需要在改派成功后删除


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_delivery_man;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        itemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON), DeliveryManItemBean.class);
        isNeedDeleteAfterChange = getIntent().getBooleanExtra(IntentConstants.KEY_NEED_DELETE_BOOLEAN, false);

        if (isNeedDeleteAfterChange) {
            mCommonTitleBar.getCenterTextView().setText(R.string.changeDeliveryMan4Delete);
        }

        //初始化列表
        mListAdapter = new Change2DeliveryManListAdapter(R.layout.item_change_delivery_man);
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
                mPresenter.changeDeliveryMan(itemBean, mListAdapter.getItem(position), isNeedDeleteAfterChange);
            }
        });

        //自动搜索
        mNameEditText.addTextChangedListener(new MyTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshList();
            }
        });

        refreshList();
    }

    private void refreshList() {
        //根据初始化的筛选条件刷新列表
        mRequestBean.phone = mNameEditText.getText().toString();
        mPresenter.refreshList(mRequestBean);//刷新列表

    }

    @OnClick(R.id.mSearchTextView)
    public void onSearchButtonClicked() {
        refreshList();
    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showList(List<DeliveryManItemBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<DeliveryManItemBean> list) {
        mListAdapter.addListBottom(list);
    }

    @Override
    public void toChangeDeliveryMan4Delete(DeliveryManItemBean deliveryManItemBean) {

    }
}
