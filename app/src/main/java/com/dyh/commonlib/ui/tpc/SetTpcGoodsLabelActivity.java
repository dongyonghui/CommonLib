package com.dyh.commonlib.ui.tpc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.LabelItemBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.presenter.SetTpcGoodsLabelPresenter;
import com.dyh.commonlib.presenter.TpcGoodsOptionPresenter;
import com.dyh.commonlib.ui.adapter.SelectedLabelTimeListAdapter;
import com.dyh.commonlib.ui.adapter.SetLabelListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 17:47
 * 描述：设置标签
 */
public class SetTpcGoodsLabelActivity extends BaseMVPActivity implements IPageView<LabelItemBean>, IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSelectedItemRecyclerView)
    RecyclerView mSelectedItemRecyclerView;

    private SetTpcGoodsLabelPresenter mPresenter = new SetTpcGoodsLabelPresenter(this);
    private TpcGoodsOptionPresenter mOptionPresenter = new TpcGoodsOptionPresenter(this);
    private SelectedLabelTimeListAdapter selectedListAdapter = new SelectedLabelTimeListAdapter(R.layout.item_selected_item);
    private SetLabelListAdapter setLabelListAdapter = new SetLabelListAdapter(R.layout.item_set_label, selectedListAdapter);
    TpcGoodsItemBean tpcGoodsItemBean;


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_tpc_goods_label;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //根据传入的商品信息展示数据
        tpcGoodsItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_TPC_GOODS_ITEM_JSON), TpcGoodsItemBean.class);

        //保存
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        mOptionPresenter.setLabel(tpcGoodsItemBean, selectedListAdapter.getData());
                        break;
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(setLabelListAdapter);
        setLabelListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);
        //上拉加载更多
        setLabelListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
        mSelectedItemRecyclerView.setAdapter(selectedListAdapter);

        //初始化已选的选项
        if (null != tpcGoodsItemBean) {
            selectedListAdapter.addListBottom(tpcGoodsItemBean.getTagsList());
        }

        //获取列表
        mPresenter.refreshList();
    }

    @Override
    public void showList(List<LabelItemBean> list) {
        setLabelListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<LabelItemBean> list) {
        setLabelListAdapter.addListBottom(list);
    }

    @Override
    public void onOptionOk() {
        setResult(RESULT_OK);
        finish();
    }
}
