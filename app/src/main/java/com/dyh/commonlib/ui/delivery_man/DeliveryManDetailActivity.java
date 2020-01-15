package com.dyh.commonlib.ui.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.commonlib.presenter.DeliveryManManagePresenter;
import com.dyh.commonlib.ui.adapter.DeliveryAddressAndShopsListAdapter;
import com.dyh.commonlib.ui.view.IDeliveryManManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 骑手详情
 */
public class DeliveryManDetailActivity extends BaseMVPActivity implements IDeliveryManManageView<DeliveryManItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameTextView)
    TextView mNameTextView;
    @BindView(R.id.mPhoneTextView)
    TextView mPhoneTextView;
    @BindView(R.id.mDeliverAddressRecyclerView)
    RecyclerView mDeliverAddressRecyclerView;

    private final int REQUEST_CODE_EDIT = 100;//编辑
    private DeliveryManItemBean itemBean;
    private DeliveryManManagePresenter deliveryManManagePresenter = new DeliveryManManagePresenter(this);

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_EDIT == requestCode && RESULT_OK == resultCode) {
            setResult(RESULT_OK);
            finish();
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_man_detail;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        itemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON), DeliveryManItemBean.class);

        if (null != itemBean) {

            mCommonTitleBar.getCenterTextView().setText(itemBean.getName());
            mNameTextView.setText(itemBean.getName());
            mPhoneTextView.setText(itemBean.getPhone());

            //设置配送地址信息
            DeliveryAddressAndShopsListAdapter listAdapter = new DeliveryAddressAndShopsListAdapter(R.layout.item_delivery_address_shops);
            listAdapter.setNewData(itemBean.getDeliveryAddressList());
            mDeliverAddressRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mDeliverAddressRecyclerView.setAdapter(listAdapter);
        }
    }

    @OnClick(R.id.mEditTextView)
    public void edit() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON, JsonUtils.toJson(itemBean));
        readyGoForResult(AddOrEditDeliveryManActivity.class, REQUEST_CODE_EDIT, bundle);
    }

    @OnClick(R.id.mChangeTextView)
    public void changeDeliveryMan() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON, JsonUtils.toJson(itemBean));
        readyGoForResult(ChangeDeliveryManActivity.class, REQUEST_CODE_EDIT, bundle);
    }

    @OnClick(R.id.mDeleteTextView)
    public void delete() {
        deliveryManManagePresenter.delete(itemBean);
    }

    @Override
    public void onOptionSuccess() {

    }

    @Override
    public void showList(List<DeliveryManItemBean> list) {

    }

    @Override
    public void appendList(List<DeliveryManItemBean> list) {

    }

    @Override
    public void toChangeDeliveryMan4Delete(DeliveryManItemBean deliveryManItemBean) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON, JsonUtils.toJson(deliveryManItemBean));
        bundle.putBoolean(IntentConstants.KEY_NEED_DELETE_BOOLEAN, true);//改派后删除
        readyGoForResult(ChangeDeliveryManActivity.class, REQUEST_CODE_EDIT, bundle);
    }
}
