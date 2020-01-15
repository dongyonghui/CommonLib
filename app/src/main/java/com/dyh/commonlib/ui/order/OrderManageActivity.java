package com.dyh.commonlib.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.GetOrderListRequestBean;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.commonlib.presenter.OrderManagePresenter;
import com.dyh.commonlib.ui.adapter.OrderListAdapter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.mylistener.MyTextChangeListener;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单管理
 */
public class OrderManageActivity extends BaseMVPActivity implements IDefaultManageView<OrderItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;
    @BindView(R.id.mRefundInfoRootView)
    View mRefundInfoRootView;
    @BindView(R.id.mRefundAllInfoTextView)
    TextView mRefundAllInfoTextView;
    @BindView(R.id.mRefundAllTextView)
    TextView mRefundAllTextView;

    private String refundInfo;//退款信息

    private OrderManagePresenter mPresenter = new OrderManagePresenter(this);
    private OrderListAdapter mListAdapter;
    private final GetOrderListRequestBean mRequestBean = new GetOrderListRequestBean();
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
        return R.layout.activity_order_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {

        //初始化列表
        mListAdapter = new OrderListAdapter(R.layout.item_order_manage);
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

        //自动搜索
        mNameEditText.addTextChangedListener(new MyTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                refreshList();
            }
        });

        //设置列表项按钮点击监听
        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle;
                switch (view.getId()) {
                    case R.id.mRefundTextView://退款
                        bundle = new Bundle();
                        bundle.putInt(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.REFUND_ORDER);
                        bundle.putString(IntentConstants.KEY_ORDER_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                        readyGoForResult(RefundOrderActivity.class, REQUEST_CODE_EDIT, bundle);
                        break;
                    case R.id.mRefundPartTextView://部分退款
                        bundle = new Bundle();
                        bundle.putInt(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.REFUND_ORDER_PART);
                        bundle.putString(IntentConstants.KEY_ORDER_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                        readyGoForResult(RefundOrderActivity.class, REQUEST_CODE_EDIT, bundle);
                        break;
                }
            }
        });

        refreshList();
    }

    private void refreshList() {
        //根据初始化的筛选条件刷新列表
        mRequestBean.keyword = mNameEditText.getText().toString();
        mPresenter.refreshList(mRequestBean);//刷新列表

    }

    @OnClick(R.id.mRefundAllTextView)
    public void refundAll() {
        Bundle bundle = new Bundle();
        bundle.putInt(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.REFUND_ORDER_LIST);
        bundle.putString(IntentConstants.KEY_ORDER_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(0)));
        bundle.putString(IntentConstants.KEY_REFUND_INFO, refundInfo);
        readyGoForResult(RefundOrderActivity.class, REQUEST_CODE_EDIT, bundle);
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
    public void showList(List<OrderItemBean> list) {
        mListAdapter.setNewData(list);
        refreshRefundInfos();
    }

    @Override
    public void appendList(List<OrderItemBean> list) {
        mListAdapter.addListBottom(list);
        refreshRefundInfos();
    }

    /**
     * 刷新退款信息
     */
    private void refreshRefundInfos() {
        if (TextUtils.isEmpty(mNameEditText.getText())) {
            mRefundInfoRootView.setVisibility(View.GONE);
        } else {
            mRefundInfoRootView.setVisibility(View.VISIBLE);
            List<OrderItemBean> list = mListAdapter.getData();
            int count = 0;
            double payFreight = 0, payActual = 0.00;
            for (OrderItemBean orderItemBean : list) {
                if (!ServerConstants.RefundStatus.isRefundedAlready(MathUtil.getIntegerNumber(orderItemBean.getRefundStatus()))) {
                    count += MathUtil.getIntegerNumber(orderItemBean.getCount());
                }
                payActual = MathUtil.sum(MathUtil.getDoubleNumber(orderItemBean.getPayActual()), payActual);
                payFreight += MathUtil.getDoubleNumber(orderItemBean.getPayFreight());
            }
            mRefundAllInfoTextView.setText(getString(R.string.formatString_refundOrderListTotalInfo, MathUtil.getMoneyNumber(payFreight), MathUtil.getMoneyNumber(payActual)));
            if (count > 0) {
                mRefundAllTextView.setVisibility(View.VISIBLE);
                refundInfo = getString(R.string.formatString_refundInfo, list.get(0).getNumber(), String.valueOf(count), MathUtil.getMoneyNumber(payActual));
            } else {
                refundInfo = null;
                mRefundAllTextView.setVisibility(View.GONE);
            }
        }

    }
}
