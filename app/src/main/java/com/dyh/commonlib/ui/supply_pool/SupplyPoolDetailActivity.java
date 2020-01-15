package com.dyh.commonlib.ui.supply_pool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolManageItemBean;
import com.dyh.commonlib.presenter.SupplyPoolManagePresenter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 供给池详情
 */
public class SupplyPoolDetailActivity extends BaseMVPActivity implements IDefaultManageView<SupplyPoolManageItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameTextView)
    TextView mNameTextView;
    @BindView(R.id.mCityTextView)
    TextView mCityTextView;
    @BindView(R.id.mManagerTextView)
    TextView mManagerTextView;
    @BindView(R.id.mShopsTextView)
    TextView mShopsTextView;
    @BindView(R.id.mDeliverAddressTextView)
    TextView mDeliverAddressTextView;

    private final int REQUEST_CODE_EDIT = 100;//编辑
    private SupplyPoolManageItemBean supplyPoolManageItemBean;
    private SupplyPoolManagePresenter supplyPoolManagePresenter = new SupplyPoolManagePresenter(this);

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
        return R.layout.activity_supply_pool_detail;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        supplyPoolManageItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_SUPPLY_POOL_MANAGE_ITEM_JSON), SupplyPoolManageItemBean.class);


        if (null != supplyPoolManageItemBean) {
            List<ShopItemBean> shopItemBeanList = supplyPoolManageItemBean.getShopList();
            StringBuilder shopsInfo = new StringBuilder();
            if (null != shopItemBeanList) {
                for (int i = 0; i < shopItemBeanList.size(); i++) {
                    if (i > 0) {
                        shopsInfo.append("\n");
                    }
                    shopsInfo.append(shopItemBeanList.get(i).getName());
                }
            }

            StringBuilder deliveryAddressInfo = new StringBuilder();
            List<DeliveryAddressManageItemBean> addressManageItemBeanList = supplyPoolManageItemBean.getDeliveryAddressList();
            if (null != addressManageItemBeanList) {
                for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                    if (i > 0) {
                        deliveryAddressInfo.append("\n");
                    }
                    deliveryAddressInfo.append(addressManageItemBeanList.get(i).getRemark());
                }
            }


            mCommonTitleBar.getCenterTextView().setText(supplyPoolManageItemBean.getName());
            mNameTextView.setText(supplyPoolManageItemBean.getName());
            mCityTextView.setText(supplyPoolManageItemBean.getCity());
            mManagerTextView.setText(getString(R.string.formatString_nameAndPhone, supplyPoolManageItemBean.getPersonName(), supplyPoolManageItemBean.getPhone()));
            mShopsTextView.setText(shopsInfo.toString());
            mDeliverAddressTextView.setText(deliveryAddressInfo.toString());
        }
    }

    @OnClick(R.id.mDrawFenceTextView)
    public void editMap() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SUPPLY_POOL_MANAGE_ITEM_JSON, JsonUtils.toJson(supplyPoolManageItemBean));
        bundle.putInt(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.EDIT_MAP_ONLY);
        readyGoForResult(AddOrEditSupplyPoolActivity.class, REQUEST_CODE_EDIT, bundle);
    }

    @OnClick(R.id.mSelectShopsTextView)
    public void editShops() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SUPPLY_POOL_MANAGE_ITEM_JSON, JsonUtils.toJson(supplyPoolManageItemBean));
        bundle.putInt(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.EDIT_SHOPS_ONLY);
        readyGoForResult(AddOrEditSupplyPoolActivity.class, REQUEST_CODE_EDIT, bundle);
    }

    @OnClick(R.id.mDeleteTextView)
    public void delete() {
        supplyPoolManagePresenter.deleteDeliverAddress(supplyPoolManageItemBean);
    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showList(List<SupplyPoolManageItemBean> list) {

    }

    @Override
    public void appendList(List<SupplyPoolManageItemBean> list) {

    }
}
