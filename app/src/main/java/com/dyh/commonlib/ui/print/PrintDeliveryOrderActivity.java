package com.dyh.commonlib.ui.print;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.local.LocalPrintDeliveryListBean;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterHeaderBean;
import com.dyh.commonlib.entity.request.GetPrintDeliveryListRequest;
import com.dyh.commonlib.presenter.PrintDeliveryOrderPresenter;
import com.dyh.commonlib.presenter.PrinterPresenter;
import com.dyh.commonlib.ui.adapter.PrinterDeliveryListAdapter;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.commonlib.ui.view.IPrinterView;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;
import com.dyh.common.lib.weigit.tablayout.CommonTabLayout;
import com.dyh.common.lib.weigit.tablayout.TabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.CustomTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.OnTabSelectListener;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.vip.dw.bluetoothprinterlib.BluetoothPrintManager;
import cn.vip.dw.bluetoothprinterlib.PrinterConfig;

/**
 * 打印配送单
 */
public class PrintDeliveryOrderActivity extends BaseMVPActivity implements IPageView<LocalPrintDeliveryListBean>, IPrinterView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.mSelectAllCheckBox)
    CheckBox mSelectAllCheckBox;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private final PrintDeliveryOrderPresenter presenter = new PrintDeliveryOrderPresenter(this);
    private final PrinterPresenter printerPresenter = new PrinterPresenter(this);

    private PrinterDeliveryListAdapter mListAdapter = new PrinterDeliveryListAdapter(R.layout.item_bdaddress_manage_list);
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            mListAdapter.setAllChecked(b);
        }
    };


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_print_delivery_order;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("送餐上楼", 0, 0));
        mTabEntities.add(new TabEntity("用户自取", 0, 0));
        mTabLayout.setTabData(mTabEntities);

        //全选
        mSelectAllCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        mListAdapter.setOnItemCheckedChangedListener(new PrinterDeliveryListAdapter.OnItemCheckedChangedListener() {
            @Override
            public void onCheckedChanged(LocalPrintDeliveryListBean item, boolean isChecked) {
                checkAllSelected();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListAdapter);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                getListContent();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //详情
        mListAdapter.setOnItemClickListener(new BaseQuickRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                LocalPrintDeliveryListBean localBDDeliveryAddressShopListBean = mListAdapter.getItem(position);
                if (null != localBDDeliveryAddressShopListBean) {
                    bundle.putString(IntentConstants.KEY_BD_DELIVERY_ADDRESS_LOCALBEAN_JSON, JsonUtils.toJson(localBDDeliveryAddressShopListBean));
                }
                readyGo(PrintDeliveryDetailActivity.class, bundle);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListContent();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        getListContent();
    }

    @Override
    public void showList(List<LocalPrintDeliveryListBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<LocalPrintDeliveryListBean> list) {
        mListAdapter.addListBottom(list);
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

        //列表项
        List<DeliveryAddressPrinterHeaderBean> printerHeaderBeanList = new ArrayList<>();

        //将选中的数据转为打印数据
        List<LocalPrintDeliveryListBean> localDataList = mListAdapter.getData();
        StringBuilder headerNameStr = new StringBuilder();
        //遍历楼宇信息
        for (LocalPrintDeliveryListBean localBDDeliveryAddressShopListBean : localDataList) {
            //跳过没有选中的
            if (!localBDDeliveryAddressShopListBean.isChecked) {
                continue;
            }
            headerNameStr.append(localBDDeliveryAddressShopListBean.deliveryAddressName).append(";");
            for (LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean floorListBean : localBDDeliveryAddressShopListBean.localPrintDeliveryAddressFloorListBeanList) {
                //遍历楼层，设置header
                DeliveryAddressPrinterHeaderBean headerBean = floorListBean.getPrinterHeaderBean();
                printerHeaderBeanList.add(headerBean);
            }
        }

        if (TextUtils.isEmpty(headerNameStr)) {
            EasyToast.getDEFAULT().show(R.string.selectDeliveryAddressPlease);
            return;
        }

        int finalPrinterWidth = printerWidth;
        int finalPrinterNum = printerNum;
        new MyAppDialog(this)
                .setTitle(getString(R.string.printDeliveryDialogTitle))
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

    /**
     * 检查是否全选
     */
    private void checkAllSelected() {
        mSelectAllCheckBox.setOnCheckedChangeListener(null);
        mSelectAllCheckBox.setChecked(mListAdapter.isAllSelected());
        mSelectAllCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }


    /**
     * 获取订单列表
     */
    private void getListContent() {
        GetPrintDeliveryListRequest orderListRequestBean = new GetPrintDeliveryListRequest();
        orderListRequestBean.useType = String.valueOf(2 - mTabLayout.getCurrentTab());
        //添加城市id
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (null != spEntity && null != spEntity.loginResponseBean && null != spEntity.loginResponseBean.getOper()) {
            orderListRequestBean.cityId = spEntity.loginResponseBean.getOper().getCityId();
        }
        presenter.refreshList(orderListRequestBean);
    }

    @Override
    public void onPrintFinish() {
    }
}
