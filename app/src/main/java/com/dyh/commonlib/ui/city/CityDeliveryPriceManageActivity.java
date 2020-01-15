package com.dyh.commonlib.ui.city;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CityDeliverPriceBean;
import com.dyh.commonlib.presenter.CityDeliveryPriceManagePresenter;
import com.dyh.commonlib.ui.view.ICityDeliveryPriceManageView;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 城市配送费管理
 */
public class CityDeliveryPriceManageActivity extends BaseMVPActivity implements ICityDeliveryPriceManageView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mFirstCustomerDeliveryPriceEditText)
    EditText mFirstCustomerDeliveryPriceEditText;
    @BindView(R.id.mNotFirstCustomerDeliveryPriceEditText)
    EditText mNotFirstCustomerDeliveryPriceEditText;
    @BindView(R.id.mNoDeliveryMoneyPriceEditText)
    EditText mNoDeliveryMoneyPriceEditText;

    private CityDeliveryPriceManagePresenter managePresenter = new CityDeliveryPriceManagePresenter(this);


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_delivery_price_manage;
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
                        save();
                        break;
                }
            }
        });
        managePresenter.getDeliveryPrice();
    }

    /**
     * 提交数据
     */
    private void save() {
        if (TextUtils.isEmpty(mFirstCustomerDeliveryPriceEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputFirstCustomerDeliveryPricePlease);
            return;
        }
        if (TextUtils.isEmpty(mNoDeliveryMoneyPriceEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputNotFirstCustomerDeliveryPricePlease);
            return;
        }
        if (TextUtils.isEmpty(mNoDeliveryMoneyPriceEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputNoDeliveryPricePlease);
            return;
        }
        CityDeliverPriceBean cityDeliverPriceBean = new CityDeliverPriceBean();
        cityDeliverPriceBean.setFirstDeliveryFee(mFirstCustomerDeliveryPriceEditText.getText().toString());
        cityDeliverPriceBean.setDeliveryFee(mNotFirstCustomerDeliveryPriceEditText.getText().toString());
        cityDeliverPriceBean.setFreeFee(mNoDeliveryMoneyPriceEditText.getText().toString());
        managePresenter.submit(cityDeliverPriceBean);
    }

    @Override
    public void onLoadDeliveryPriceSuccess(CityDeliverPriceBean deliverPriceBean) {
        if (deliverPriceBean == null) {
            return;
        }
        mFirstCustomerDeliveryPriceEditText.setText(deliverPriceBean.getFirstDeliveryFee());
        mNotFirstCustomerDeliveryPriceEditText.setText(deliverPriceBean.getDeliveryFee());
        mNoDeliveryMoneyPriceEditText.setText(deliverPriceBean.getFreeFee());
    }

    @Override
    public void onSubmitSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }
}
