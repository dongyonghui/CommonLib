package com.dyh.commonlib.ui.delivery_man;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.AddOrEditDeliveryManRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.commonlib.presenter.AddOrEditDeliveryManPresenter;
import com.dyh.commonlib.ui.adapter.SelectedDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.common.SelectDeliveryAddressActivity;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建或编辑骑手
 */
public class AddOrEditDeliveryManActivity extends BaseMVPActivity implements IDefaultOptionView {

    private final int REQUEST_SELECT_DELIVERY_ADDRESS = 110;//选择店铺

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;
    @BindView(R.id.mAccountEditText)
    EditText mAccountEditText;
    @BindView(R.id.mPhoneEditText)
    EditText mPhoneEditText;
    @BindView(R.id.mDeliverAddressRecyclerView)
    RecyclerView mDeliverAddressRecyclerView;

    private AddOrEditDeliveryManPresenter mPresenter = new AddOrEditDeliveryManPresenter(this);
    private DeliveryManItemBean deliveryManItemBean;
    private SelectedDeliveryAddressListAdapter selectedListAdapter = new SelectedDeliveryAddressListAdapter(R.layout.item_selected_item);

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && null != data) {
            switch (requestCode) {
                case REQUEST_SELECT_DELIVERY_ADDRESS://选择配送地址
                    // 添加已选中的选项
                    String json = data.getStringExtra(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON);
                    if (!TextUtils.isEmpty(json)) {
                        List<DeliveryAddressManageItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<DeliveryAddressManageItemBean>>() {
                        }.getType());
                        selectedListAdapter.setNewData(selectedList);
                    }
                    break;
            }
        }
    }



    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_delivery_man;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //获取编辑数据
        deliveryManItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_DELIVERY_MAN_ITEM_JSON), DeliveryManItemBean.class);

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

        mDeliverAddressRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDeliverAddressRecyclerView.setAdapter(selectedListAdapter);
        selectedListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectedListAdapter.remove(position);
            }
        });
        initEditData();
    }


    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.mSelectDeliveryAddressTextView)
    public void selectDeliveryAddress() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON, JsonUtils.toJson(selectedListAdapter.getData()));
        readyGoForResult(SelectDeliveryAddressActivity.class, REQUEST_SELECT_DELIVERY_ADDRESS, bundle);
    }


    private void initEditData() {
        if (null != deliveryManItemBean) {
            mCommonTitleBar.getCenterTextView().setText(R.string.editDeliveryMan);
            mNameEditText.setText(deliveryManItemBean.getName());
            mAccountEditText.setText(deliveryManItemBean.getUserName());
            mPhoneEditText.setText(deliveryManItemBean.getPhone());
            selectedListAdapter.setNewData(deliveryManItemBean.getDeliveryAddressList());
        }
    }


    /**
     * 保存数据
     */
    private void save() {
        if (TextUtils.isEmpty(mNameEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputPersonNamePlease);
            return;
        }
        AddOrEditDeliveryManRequestBean requestBean = new AddOrEditDeliveryManRequestBean();
        if (null != deliveryManItemBean) {
            requestBean.deliveryManId = deliveryManItemBean.getId();
        }
        requestBean.name = mNameEditText.getText().toString();
        requestBean.userName = mAccountEditText.getText().toString();
        requestBean.phone = mPhoneEditText.getText().toString();
        requestBean.deliveryAddressIds = new ArrayList<>();
        List<DeliveryAddressManageItemBean> list = selectedListAdapter.getData();
        for (DeliveryAddressManageItemBean itemBean : list) {
            requestBean.deliveryAddressIds.add(itemBean.getDeliveryAddressId());
        }


        mPresenter.addOrEdit(requestBean);
    }
}
