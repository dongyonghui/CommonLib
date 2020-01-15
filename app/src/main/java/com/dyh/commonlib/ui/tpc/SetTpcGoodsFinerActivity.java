package com.dyh.commonlib.ui.tpc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.SubmitAuditingRequestBean;
import com.dyh.commonlib.entity.response.FinerTimeItemBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.presenter.SetTpcGoodsFinerPresenter;
import com.dyh.commonlib.presenter.TpcGoodsOptionPresenter;
import com.dyh.commonlib.ui.adapter.SelectedFinerTimeListAdapter;
import com.dyh.commonlib.ui.adapter.SetFinerTimeListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 14:41
 * 描述：设置优选
 */
public class SetTpcGoodsFinerActivity extends BaseMVPActivity implements IPageView<FinerTimeItemBean>, IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSelectedItemRecyclerView)
    RecyclerView mSelectedItemRecyclerView;

    private SetTpcGoodsFinerPresenter mPresenter = new SetTpcGoodsFinerPresenter(this);
    private TpcGoodsOptionPresenter mOptionPresenter = new TpcGoodsOptionPresenter(this);
    private SelectedFinerTimeListAdapter selectedFinerTimeListAdapter = new SelectedFinerTimeListAdapter(R.layout.item_selected_item);
    private SetFinerTimeListAdapter setFinerTimeListAdapter = new SetFinerTimeListAdapter(R.layout.item_set_finer_time, selectedFinerTimeListAdapter);
    TpcGoodsItemBean tpcGoodsItemBean;


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_tpc_goods_finer;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //根据传入的商品信息展示数据
        tpcGoodsItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_TPC_GOODS_ITEM_JSON), TpcGoodsItemBean.class);
        //添加已选中的选项
        initSelectedItems();

        //保存
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        mOptionPresenter.setFinerTime(tpcGoodsItemBean, selectedFinerTimeListAdapter.getData());
                        break;
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(setFinerTimeListAdapter);
        setFinerTimeListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);
        //上拉加载更多
        setFinerTimeListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadList();
            }
        }, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //设置选中的列表
        mSelectedItemRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSelectedItemRecyclerView.setAdapter(selectedFinerTimeListAdapter);

        mPresenter.refreshList();
    }

    /**
     * 初始化默认选中的项
     */
    private void initSelectedItems() {
        if (null == tpcGoodsItemBean) {
            return;
        }
        //优先使用对象列表，最新API返回以此为准
        if (null != tpcGoodsItemBean.getCateList()) {
            List<FinerTimeItemBean> list = new ArrayList<>();
            for (SubmitAuditingRequestBean.CateListBean cateItemBean : tpcGoodsItemBean.getCateList()) {
                FinerTimeItemBean itemBean = new FinerTimeItemBean();
                list.add(itemBean);
                itemBean.setId(cateItemBean.getId());
                itemBean.setName(cateItemBean.getName());
                if (TextUtils.isEmpty(cateItemBean.getTime())) {
                    continue;
                }
                String[] timeArray = cateItemBean.getTime().split("-");
                if (timeArray.length == 2) {
                    itemBean.setEndTimeStr(timeArray[0]);
                    itemBean.setSendTimeStr(timeArray[1]);
                }
            }
            selectedFinerTimeListAdapter.addListBottom(list);
            return;
        }

        //兼容老版本API数据格式
        if (null != tpcGoodsItemBean.getPreferenceIds()) {
            List<FinerTimeItemBean> list = new ArrayList<>();
            for (int i = 0; i < tpcGoodsItemBean.getPreferenceIds().size(); i++) {
                FinerTimeItemBean itemBean = new FinerTimeItemBean();
                itemBean.setId(tpcGoodsItemBean.getPreferenceIds().get(i));
                if (null != tpcGoodsItemBean.getPreferenceTime() && tpcGoodsItemBean.getPreferenceTime().size() > i) {
                    String time = tpcGoodsItemBean.getPreferenceTime().get(i);
                    String[] timeArray = time.split("-");
                    itemBean.setName(time);
                    if (timeArray.length == 2) {
                        itemBean.setEndTimeStr(timeArray[0]);
                        itemBean.setSendTimeStr(timeArray[1]);
                    }
                }
                list.add(itemBean);
            }
            selectedFinerTimeListAdapter.addListBottom(list);
        }
    }

    @Override
    public void showList(List<FinerTimeItemBean> list) {
        setFinerTimeListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<FinerTimeItemBean> list) {
        setFinerTimeListAdapter.addListBottom(list);
    }

    @Override
    public void onOptionOk() {
        setResult(RESULT_OK);
        finish();
    }
}
