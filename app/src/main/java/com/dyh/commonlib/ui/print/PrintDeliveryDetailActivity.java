package com.dyh.commonlib.ui.print;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.local.LocalPrintDeliveryListBean;
import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterHeaderBean;
import com.dyh.commonlib.presenter.PrinterPresenter;
import com.dyh.commonlib.ui.adapter.PrinterDeliveryDetailListAdapter;
import com.dyh.commonlib.ui.view.IPrinterView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.vip.dw.bluetoothprinterlib.BluetoothPrintManager;
import cn.vip.dw.bluetoothprinterlib.PrinterConfig;

/**
 * 打印配送单详情页
 */
public class PrintDeliveryDetailActivity extends BaseMVPActivity implements IPrinterView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSelectAllCheckBox)
    CheckBox mSelectAllCheckBox;

    private PrinterPresenter printerPresenter = new PrinterPresenter(this);
    private PrinterDeliveryDetailListAdapter adapter = new PrinterDeliveryDetailListAdapter(R.layout.item_bdaddress_detail_list);
    private LocalPrintDeliveryListBean shopListBean;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            adapter.setAllChecked(b);
        }
    };


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_print_delivery_detail;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        String json = getIntent().getStringExtra(IntentConstants.KEY_BD_DELIVERY_ADDRESS_LOCALBEAN_JSON);
        shopListBean = JsonUtils.object(json, LocalPrintDeliveryListBean.class);

        mCommonTitleBar.getCenterTextView().setText(null == shopListBean ? getString(R.string.printDeliveryOrder) : shopListBean.deliveryAddressName);

        //全选
        mSelectAllCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        adapter.setOnItemCheckedChangedListener(new PrinterDeliveryDetailListAdapter.OnItemCheckedChangedListener() {
            @Override
            public void onCheckedChanged(LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean item, boolean isChecked) {
                mSelectAllCheckBox.setOnCheckedChangeListener(null);
                mSelectAllCheckBox.setChecked(adapter.isAllSelected());
                mSelectAllCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        });


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setNewData(shopListBean.localPrintDeliveryAddressFloorListBeanList);
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.mPrintTextView)
    public void print() {
        PrinterConfig printerConfig = BluetoothPrintManager.getInstance().getPrinterConfig(this);
        int printerNum = 1;
        int printerWidth = 58;
        if (printerConfig != null) {
            printerNum = printerConfig.getPrintCount();
            printerWidth = printerConfig.getPagerWidth();
        }
        List<DeliveryAddressPrinterHeaderBean> printerHeaderBeanList = new ArrayList<>();
        StringBuilder headerNameStr = new StringBuilder();
        for (LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean datum : adapter.getData()) {
            if (!datum.isChecked) {
                continue;
            }
            headerNameStr.append(datum.floor).append(getString(R.string.floor)).append(";");
            printerHeaderBeanList.add(datum.getPrinterHeaderBean());
        }
        if (TextUtils.isEmpty(headerNameStr)) {
            EasyToast.getDEFAULT().show(R.string.selectFloor);
            return;
        }

        int finalPrinterNum = printerNum;
        int finalPrinterWidth = printerWidth;
        new MyAppDialog(this)
                .setTitle(getString(R.string.printDeliveryFloorsDialogTitle))
                .setMessage(headerNameStr.toString())
                .setPositive(getString(R.string.common_confirm), new OnDialogClickListener() {

                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        printerPresenter.printDeliveryList(printerHeaderBeanList, finalPrinterWidth, finalPrinterNum);
                    }
                })
                .setNegative(getString(R.string.cancel))
                .show();
    }

    @Override
    public void onPrintFinish() {

    }
}
