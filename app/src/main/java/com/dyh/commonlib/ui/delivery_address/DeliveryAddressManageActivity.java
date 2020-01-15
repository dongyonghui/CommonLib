package com.dyh.commonlib.ui.delivery_address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.request.GetFilterListRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.commonlib.model.FilterModel;
import com.dyh.commonlib.presenter.DeliveryAddressManagePresenter;
import com.dyh.commonlib.presenter.FilterManagePresenter;
import com.dyh.commonlib.ui.adapter.DeliveryAddressListAdapter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IFilterManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.TagGroup;
import com.dyh.common.lib.weigit.pop_filter.bean.FiltrateBean;
import com.dyh.common.lib.weigit.pop_filter.view.ScreenPopWindow;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 配送地址管理页面
 */
public class DeliveryAddressManageActivity extends BaseMVPActivity implements IDefaultManageView<DeliveryAddressManageItemBean>,
        IFilterManageView, IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mFilterRootView)
    View mFilterRootView;
    @BindView(R.id.mFilterTagGroup)
    TagGroup mFilterTagGroup;

    private DeliveryAddressManagePresenter mPresenter = new DeliveryAddressManagePresenter(this);
    private FilterManagePresenter mFilterPresenter = new FilterManagePresenter(this);
    private DeliveryAddressListAdapter mListAdapter;
    private final GetDeliveryAddressRequestBean mRequestBean = new GetDeliveryAddressRequestBean();
    private List<FiltrateBean> mFiltrateBeanList = FilterModel.getDeliveryFilterData();//筛选条件
    private ScreenPopWindow screenPopWindow;
    private final int REQUEST_CODE_EDIT_GOODS = 100;//编辑商品

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_EDIT_GOODS == requestCode && RESULT_OK == resultCode) {
            refreshList();
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery_address_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //初始化列表
        mListAdapter = new DeliveryAddressListAdapter(R.layout.item_delivery_address);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        readyGo(AddOrEditDeliveryAddressActivity.class);
                        break;
                }
            }
        });

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
                mSwipeRefreshLayout.setRefreshing(false);
                mPresenter.refreshList(mRequestBean);
            }
        });

        //设置列表项点击事件
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(IntentConstants.KEY_DELIVERY_ADDRESS_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                readyGoForResult(AddOrEditDeliveryAddressActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
            }
        });

        //设置列表项按钮点击监听
        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.mOpenOrCloseTextView://开启或关闭
                        mPresenter.openOrCloseDeliverAddress(mListAdapter.getItem(position));
                        break;
                    case R.id.mDeleteTextView://删除
                        mPresenter.deleteDeliverAddress(mListAdapter.getItem(position));
                        break;
                }
            }
        });

        //初始化筛选条件
        initScreenPopWindow();

        refreshList();
    }

    private void refreshList() {
        //根据初始化的筛选条件刷新列表
        refreshListByFilter(screenPopWindow.getFilterSelectedValue());
    }

    @OnClick(R.id.mFilterRootView)
    public void onFilterClicked() {
        if (null == screenPopWindow) {
            initScreenPopWindow();
        }
        screenPopWindow.showAsDropDown(mFilterRootView);
    }

    /**
     * 初始化筛选对话框
     */
    private void initScreenPopWindow() {

        screenPopWindow = new ScreenPopWindow(this, mFiltrateBeanList);
        screenPopWindow.build();
        GetFilterListRequestBean requestBean = new GetFilterListRequestBean();
        requestBean.cityId = "0";
        mFilterPresenter.loadSupplyPoolList(requestBean);//供给池

        //确定筛选条件
        screenPopWindow.setOnConfirmClickListener(new ScreenPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick(Map<String, FiltrateBean> selectedValues) {
                refreshListByFilter(selectedValues);
            }
        });
    }


    /**
     * 根据筛选条件刷新列表
     *
     * @param selectedValues
     */
    private void refreshListByFilter(Map<String, FiltrateBean> selectedValues) {
        if (null == selectedValues) {
            return;
        }
        List<String> filterList = new ArrayList<>();
        //供给池
        FiltrateBean filtrateBean = selectedValues.get("supplyPoolId");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mRequestBean.supplyPoolId = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mRequestBean.supplyPoolId = "0";
        }

        //输入的内容
        filtrateBean = selectedValues.get("address");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mRequestBean.address = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mRequestBean.address = null;
        }

        if (filterList.size() == 0) {
            filterList.add("全部");
        }

        mFilterTagGroup.setTags(filterList);//显示选中的条件
        mPresenter.refreshList(mRequestBean);//刷新列表
    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        refreshList();
    }

    @Override
    public void showList(List<DeliveryAddressManageItemBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<DeliveryAddressManageItemBean> list) {
        mListAdapter.addListBottom(list);
    }

    @Override
    public void onLoadCitySuccess(List<CityItemBean> list) {

    }

    @Override
    public void onLoadDeliveryAddressSuccess(List<DeliveryAddressItemBean> list) {

    }

    @Override
    public void onLoadSupplyPoolSuccess(List<SupplyPoolItemBean> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        FiltrateBean filtrateBean = new FiltrateBean();
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        filtrateBean.setChildren(childrenList);
        filtrateBean.setTitleName("供给池");
        filtrateBean.setKey("supplyPoolId");
        childrenList.add(new FiltrateBean.Children("全部", "0"));
        for (SupplyPoolItemBean itemBean : list) {
            childrenList.add(new FiltrateBean.Children(itemBean.name, itemBean.supplyPoolId));
        }
        screenPopWindow.replaceOrAdd(filtrateBean);
    }

    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        refreshList();
    }
}
