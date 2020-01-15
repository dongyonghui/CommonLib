package com.dyh.commonlib.ui.tpc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.presenter.SetTpcGoodsCategoryPresenter;
import com.dyh.commonlib.presenter.TpcGoodsOptionPresenter;
import com.dyh.commonlib.ui.adapter.SelectedCategoryListAdapter;
import com.dyh.commonlib.ui.adapter.SetCategoryListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.entity.SectionEntity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 14:41
 * 描述：设置分类
 */
public class SetTpcGoodsCategoryActivity extends BaseMVPActivity implements IPageView<SectionEntity<CategoryItemBean>>, IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSelectedItemRecyclerView)
    RecyclerView mSelectedItemRecyclerView;

    private SetTpcGoodsCategoryPresenter mPresenter = new SetTpcGoodsCategoryPresenter(this);
    private TpcGoodsOptionPresenter mOptionPresenter = new TpcGoodsOptionPresenter(this);
    private SelectedCategoryListAdapter selectedListAdapter = new SelectedCategoryListAdapter(R.layout.item_selected_item);
    private SetCategoryListAdapter setCategoryListAdapter = new SetCategoryListAdapter(R.layout.item_set_category, R.layout.header_set_category, selectedListAdapter);
    TpcGoodsItemBean tpcGoodsItemBean;


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_tpc_goods_category;
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
                        mOptionPresenter.setTpcGoodsCategory(tpcGoodsItemBean, selectedListAdapter.getData());
                        break;
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(setCategoryListAdapter);
        setCategoryListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshTpcCategoryList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //设置选中的列表
        mSelectedItemRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSelectedItemRecyclerView.setAdapter(selectedListAdapter);

        mPresenter.refreshTpcCategoryList();
    }

    /**
     * 初始化默认选中的项
     */
    private void initSelectedItems() {
        if (null == tpcGoodsItemBean) {
            return;
        }

        //优先使用最新API数据
        if (null != tpcGoodsItemBean.getCategoryResponseList()) {
            selectedListAdapter.addListBottom(tpcGoodsItemBean.getCategoryResponseList());
            return;
        }

        //兼容老版本API，只返回ids和nameList的情况
        String categoryIds = tpcGoodsItemBean.getCategoryIds();
        if (TextUtils.isEmpty(categoryIds)) {
            return;
        }
        String[] idArray = categoryIds.split(",");
        if (idArray.length == 0) {
            return;
        }
        List<CategoryItemBean> list = new ArrayList<>();
        for (int i = 0; i < idArray.length; i++) {
            CategoryItemBean categoryItemBean = new CategoryItemBean();
            categoryItemBean.setId(idArray[i]);
            if (null == tpcGoodsItemBean.getCategoryNames() || tpcGoodsItemBean.getCategoryNames().size() <= i) {
                continue;
            }
            categoryItemBean.setName(tpcGoodsItemBean.getCategoryNames().get(i));
            list.add(categoryItemBean);
        }
        selectedListAdapter.addListBottom(list);
    }

    @Override
    public void showList(List<SectionEntity<CategoryItemBean>> list) {
        setCategoryListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<SectionEntity<CategoryItemBean>> list) {
        setCategoryListAdapter.addListBottom(list);
    }

    @Override
    public void onOptionOk() {
        setResult(RESULT_OK);
        finish();
    }
}
