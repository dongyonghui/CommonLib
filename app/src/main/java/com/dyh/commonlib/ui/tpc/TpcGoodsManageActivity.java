package com.dyh.commonlib.ui.tpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetFilterListRequestBean;
import com.dyh.commonlib.entity.request.GetTpcGoodsRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.model.FilterModel;
import com.dyh.commonlib.presenter.FilterManagePresenter;
import com.dyh.commonlib.presenter.TpcGoodsManagePresenter;
import com.dyh.commonlib.presenter.TpcGoodsOptionPresenter;
import com.dyh.commonlib.ui.adapter.TpcGoodsListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IFilterManageView;
import com.dyh.commonlib.ui.view.IPageView;
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
 * 头牌菜管理页面
 */
public class TpcGoodsManageActivity extends BaseMVPActivity implements IPageView<TpcGoodsItemBean>,
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

    private TpcGoodsManagePresenter mPresenter = new TpcGoodsManagePresenter(this);
    private FilterManagePresenter mFilterPresenter = new FilterManagePresenter(this);
    private TpcGoodsOptionPresenter mOptionPresenter = new TpcGoodsOptionPresenter(this);
    private TpcGoodsListAdapter mGoodsListAdapter;
    private final GetTpcGoodsRequestBean mGetGoodsListRequestBean = new GetTpcGoodsRequestBean();
    private List<FiltrateBean> mFiltrateBeanList = FilterModel.getTpcGoodsFilterData();//筛选条件
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
        return R.layout.activity_tpc_goods_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //初始化列表
        mGoodsListAdapter = new TpcGoodsListAdapter(R.layout.item_tpc_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGoodsListAdapter);
        mGoodsListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        //加载更多
        mGoodsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadList(mGetGoodsListRequestBean);
            }
        }, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mPresenter.refreshList(mGetGoodsListRequestBean);
            }
        });

        //设置列表项点击事件
        mGoodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(IntentConstants.KEY_TPC_GOODS_ITEM_JSON, JsonUtils.toJson(mGoodsListAdapter.getItem(position)));
                readyGoForResult(TpcGoodsDetailActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
            }
        });

        //设置列表项按钮点击监听
        mGoodsListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.mAgreeAndAddPriceTextView://加价通过
                        mOptionPresenter.agreeAndAddPrice(mGoodsListAdapter.getItem(position));
                        break;
                    case R.id.mRejectTextView://驳回
                        mOptionPresenter.reject(mGoodsListAdapter.getItem(position));
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
        mFilterPresenter.loadCityList();//获取城市列表

        //监听选项点击事件，动态获取对应关联数据
        screenPopWindow.setOnItemClickListener(new ScreenPopWindow.OnItemClickListener() {
            @Override
            public void onItemClick(FiltrateBean parentBean, FiltrateBean.Children clickedItemBean) {
                if ("cityId".equals(parentBean.getKey())) {
                    GetFilterListRequestBean requestBean = new GetFilterListRequestBean();
                    requestBean.cityId = clickedItemBean.getValue();
                    mFilterPresenter.loadDeliveryAddressList(requestBean);
                    mFilterPresenter.loadSupplyPoolList(requestBean);
                }
            }
        });

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
        //菜品类型
        FiltrateBean filtrateBean = selectedValues.get("auditingType");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.auditingType = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.auditingType = "0";
        }

        //审核状态
        filtrateBean = selectedValues.get("auditingStatus");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.auditingStatus = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.auditingStatus = "0";
        }

        //配送地址
        filtrateBean = selectedValues.get("deliveryAddressId");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.deliveryAddressId = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.deliveryAddressId = "0";
        }

        //供给池
        filtrateBean = selectedValues.get("supplyPoolId");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.supplyPoolId = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.supplyPoolId = "0";
        }

        //城市
        filtrateBean = selectedValues.get("cityId");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.cityId = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.cityId = "0";
        }

        //输入的内容
        filtrateBean = selectedValues.get("name");
        if (null != filtrateBean && !filtrateBean.isAllSelected()) {
            mGetGoodsListRequestBean.name = filtrateBean.getSelectedSingleValue();
            filterList.add(filtrateBean.getSelectedSingleShowText());
        } else {
            mGetGoodsListRequestBean.name = null;
        }

        if (filterList.size() == 0) {
            filterList.add("全部");
        }

        mFilterTagGroup.setTags(filterList);//显示选中的条件
        mPresenter.refreshList(mGetGoodsListRequestBean);//刷新列表
    }

    @Override
    public void showList(List<TpcGoodsItemBean> list) {
        mGoodsListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<TpcGoodsItemBean> list) {
        mGoodsListAdapter.addListBottom(list);
    }

    @Override
    public void onLoadCitySuccess(List<CityItemBean> list) {
        //如果城市列表为空，则不添加选择城市选项
        if (null != list && list.size() > 0) {
            FiltrateBean filtrateBean = new FiltrateBean();
            List<FiltrateBean.Children> childrenList = new ArrayList<>();
            filtrateBean.setChildren(childrenList);
            filtrateBean.setTitleName("城市");
            filtrateBean.setKey("cityId");
            childrenList.add(new FiltrateBean.Children("全部", "0"));
            for (CityItemBean cityItemBean : list) {
                childrenList.add(new FiltrateBean.Children(cityItemBean.name, cityItemBean.id));
            }
            screenPopWindow.replaceOrAdd(filtrateBean);
        }


        //获取城市列表成功后获取地址和供给池
        GetFilterListRequestBean requestBean = new GetFilterListRequestBean();
        requestBean.cityId = "0";
        mFilterPresenter.loadDeliveryAddressList(requestBean);
        mFilterPresenter.loadSupplyPoolList(requestBean);
    }

    @Override
    public void onLoadDeliveryAddressSuccess(List<DeliveryAddressItemBean> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        FiltrateBean filtrateBean = new FiltrateBean();
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        filtrateBean.setChildren(childrenList);
        filtrateBean.setTitleName("配送地址");
        filtrateBean.setKey("deliveryAddressId");
        childrenList.add(new FiltrateBean.Children("全部", "0"));
        for (DeliveryAddressItemBean itemBean : list) {
            childrenList.add(new FiltrateBean.Children(itemBean.remark, itemBean.deliveryAddressId));
        }
        screenPopWindow.replaceOrAdd(filtrateBean);
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
