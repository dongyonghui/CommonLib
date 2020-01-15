package com.dyh.commonlib.ui.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.commonlib.presenter.RefundOrderPresenter;
import com.dyh.commonlib.ui.adapter.RefundGoodsListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单退款
 */
public class RefundOrderActivity extends BaseMVPActivity implements IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mInfoTextView)
    TextView mInfoTextView;
    @BindView(R.id.mReasonEditText)
    EditText mReasonEditText;
    @BindView(R.id.mResponsiblePartyRadioGroup)
    RadioGroup mResponsiblePartyRadioGroup;
    @BindView(R.id.mRefundGoodsInfoRootView)
    View mRefundGoodsInfoRootView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private int flag;//退款标记
    private String refundInfo;//退款提示信息
    private OrderItemBean orderItemBean;//订单信息
    private RefundGoodsListAdapter refundGoodsListAdapter = new RefundGoodsListAdapter(R.layout.item_refund_goods);
    private RefundOrderPresenter mPresenter = new RefundOrderPresenter(this);


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund_order;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        flag = getIntent().getIntExtra(IntentConstants.KEY_FLAG_INT, 0);
        refundInfo = getIntent().getStringExtra(IntentConstants.KEY_REFUND_INFO);
        orderItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_ORDER_ITEM_JSON), OrderItemBean.class);
        mRefundGoodsInfoRootView.setVisibility(View.GONE);
        if (null != orderItemBean) {
            mInfoTextView.setText(getString(R.string.formatString_orderNo, orderItemBean.getOrderIdStr()));

            //如果是部分退款，则展示所有商品信息
            if (IntentConstants.Flag.REFUND_ORDER_PART == flag) {
                mRefundGoodsInfoRootView.setVisibility(View.VISIBLE);
                refundGoodsListAdapter.setNewData(orderItemBean.getItems());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.setAdapter(refundGoodsListAdapter);
            }
        }
        if (!TextUtils.isEmpty(refundInfo)) {
            mInfoTextView.setText(refundInfo);
        }
    }

    @OnClick(R.id.mRefundTextView)
    public void refund() {
        mPresenter.refund(orderItemBean, flag, mReasonEditText.getText().toString(), mResponsiblePartyRadioGroup.getCheckedRadioButtonId());
    }


    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }
}
