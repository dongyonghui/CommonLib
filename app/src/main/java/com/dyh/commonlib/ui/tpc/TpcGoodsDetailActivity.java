package com.dyh.commonlib.ui.tpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.constants.UIConstants;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.presenter.TpcGoodsOptionPresenter;
import com.dyh.commonlib.ui.adapter.GoodsImagesAdapter;
import com.dyh.commonlib.ui.adapter.GoodsTagListAdapter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TpcGoodsDetailActivity extends BaseMVPActivity implements IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mGoodsNameTextView)
    TextView mGoodsNameTextView;
    @BindView(R.id.mStatusTextView)
    TextView mStatusTextView;
    @BindView(R.id.mGoodsRemarkTextView)
    TextView mGoodsRemarkTextView;
    @BindView(R.id.mGoodsMainImagesTitleView)
    View mGoodsMainImagesTitleView;
    @BindView(R.id.mGoodsMainImagesRecyclerView)
    RecyclerView mGoodsMainImagesRecyclerView;
    @BindView(R.id.mTagsRecyclerView)
    RecyclerView mTagsRecyclerView;
    @BindView(R.id.mGoodsDetailImagesTitleView)
    View mGoodsDetailImagesTitleView;
    @BindView(R.id.mGoodsDetailImagesRecyclerView)
    RecyclerView mGoodsDetailImagesRecyclerView;
    @BindView(R.id.mAddedPriceTextView)
    TextView mAddedPriceTextView;
    @BindView(R.id.mPriceTextView)
    TextView mPriceTextView;
    @BindView(R.id.mLimitNumTextView)
    TextView mLimitNumTextView;
    @BindView(R.id.mShowTimeInfoTextView)
    TextView mShowTimeInfoTextView;
    @BindView(R.id.mShopInfoTextView)
    TextView mShopInfoTextView;
    @BindView(R.id.mGoodsDeliveryAddressTitleView)
    View mGoodsDeliveryAddressTitleView;
    @BindView(R.id.mGoodsDeliveryAddressTextView)
    TextView mGoodsDeliveryAddressTextView;
    @BindView(R.id.mCategoryTextView)
    TextView mCategoryTextView;
    @BindView(R.id.mWeightTextView)
    TextView mWeightTextView;
    @BindView(R.id.mRejectInfoTextView)
    TextView mRejectInfoTextView;

    @BindView(R.id.mEditTextView)
    TextView mEditTextView;
    @BindView(R.id.mAgreeTextView)
    TextView mAgreeTextView;
    @BindView(R.id.mAgreeAndAddPriceTextView)
    TextView mAgreeAndAddPriceTextView;
    @BindView(R.id.mModifyAddPriceTextView)
    TextView mModifyAddPriceTextView;
    @BindView(R.id.mSetLabelTextView)
    TextView mSetLabelTextView;
    @BindView(R.id.mModifySortTextView)
    TextView mModifySortTextView;
    @BindView(R.id.mSetClassTextView)
    TextView mSetClassTextView;
    @BindView(R.id.mSetSuperiorTextView)
    TextView mSetSuperiorTextView;
    @BindView(R.id.mRejectTextView)
    TextView mRejectTextView;
    @BindView(R.id.mDeleteTextView)
    TextView mDeleteTextView;

    TpcGoodsItemBean tpcGoodsItemBean;
    private final TpcGoodsOptionPresenter mOptionPresenter = new TpcGoodsOptionPresenter(this);
    private final int REQUEST_CODE_EDIT_GOODS = 100;//编辑商品

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_EDIT_GOODS == requestCode && RESULT_OK == resultCode) {
            onOptionOk();
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tpc_goods_detail;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //根据传入的商品信息展示数据
        tpcGoodsItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_TPC_GOODS_ITEM_JSON), TpcGoodsItemBean.class);
        if (null != tpcGoodsItemBean) {
            mGoodsNameTextView.setText(tpcGoodsItemBean.getName());//商品名称

            //设置标签
            GoodsTagListAdapter tagListAdapter = new GoodsTagListAdapter(R.layout.item_goods_tags);
            tagListAdapter.setNewData(tpcGoodsItemBean.getTagsList());
            mTagsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mTagsRecyclerView.setAdapter(tagListAdapter);

            //商品描述
            if (!TextUtils.isEmpty(tpcGoodsItemBean.getDescription())) {
                mGoodsRemarkTextView.setVisibility(View.VISIBLE);
                mGoodsRemarkTextView.setText(tpcGoodsItemBean.getDescription());
            } else {
                mGoodsRemarkTextView.setVisibility(View.GONE);
            }
            //商品审核状态
            mStatusTextView.setText(tpcGoodsItemBean.getGoodsAuditingStatusText());

            //加价信息
            String addPriceInfo = tpcGoodsItemBean.getAddedPriceTextInfo();
            if (TextUtils.isEmpty(addPriceInfo)) {
                mAddedPriceTextView.setVisibility(View.GONE);
            } else {
                mAddedPriceTextView.setVisibility(View.VISIBLE);
                mAddedPriceTextView.setText(addPriceInfo);
            }
            //商品价格信息
            mPriceTextView.setText(tpcGoodsItemBean.getPriceTextInfo());
            mLimitNumTextView.setText(getString(R.string.formatString_limitNum4EveryDay, tpcGoodsItemBean.getLimitNum(), tpcGoodsItemBean.getPersonLimitNum()));
            //展示时间
            if (null != tpcGoodsItemBean.getPreferenceTime() && tpcGoodsItemBean.getPreferenceTime().size() > 0) {
                mShowTimeInfoTextView.setVisibility(View.VISIBLE);
                mShowTimeInfoTextView.setText(getString(R.string.formatString_showTime, tpcGoodsItemBean.getShowTimeTextInfo()));
            } else {
                mShowTimeInfoTextView.setVisibility(View.GONE);
            }
            mShopInfoTextView.setText(getString(R.string.formatString_shopInfo, tpcGoodsItemBean.getShopName(),
                    tpcGoodsItemBean.getPersonName(), tpcGoodsItemBean.getShopPhone(), tpcGoodsItemBean.getAddress()));

            //商品图片列表
            if (null != tpcGoodsItemBean.getPictureList() && tpcGoodsItemBean.getPictureList().size() > 0) {
                mGoodsMainImagesTitleView.setVisibility(View.VISIBLE);
                mGoodsMainImagesRecyclerView.setVisibility(View.VISIBLE);
                GoodsImagesAdapter goodsMainImagesAdapter = new GoodsImagesAdapter(R.layout.item_tpc_goods_images);
                goodsMainImagesAdapter.addListBottom(tpcGoodsItemBean.getPictureList());
                mGoodsMainImagesRecyclerView.setLayoutManager(new GridLayoutManager(this, UIConstants.COUNT_GOODS_DETAIL_IMAGES_GRID_COL));
                mGoodsMainImagesRecyclerView.setAdapter(goodsMainImagesAdapter);
            } else {
                mGoodsMainImagesTitleView.setVisibility(View.GONE);
                mGoodsMainImagesRecyclerView.setVisibility(View.GONE);
            }


            //商品详情图
            if (null != tpcGoodsItemBean.getStandards() && tpcGoodsItemBean.getStandards().size() > 0
                    && null != tpcGoodsItemBean.getStandards().get(0).getPictureList() && tpcGoodsItemBean.getStandards().get(0).getPictureList().size() > 0) {
                mGoodsDetailImagesTitleView.setVisibility(View.VISIBLE);
                mGoodsDetailImagesRecyclerView.setVisibility(View.VISIBLE);
                GoodsImagesAdapter goodsDetailImagesAdapter = new GoodsImagesAdapter(R.layout.item_tpc_goods_images);
                goodsDetailImagesAdapter.addListBottom(tpcGoodsItemBean.getStandards().get(0).getPictureList());
                mGoodsDetailImagesRecyclerView.setLayoutManager(new GridLayoutManager(this, UIConstants.COUNT_GOODS_DETAIL_IMAGES_GRID_COL));
                mGoodsDetailImagesRecyclerView.setAdapter(goodsDetailImagesAdapter);
            } else {
                mGoodsDetailImagesTitleView.setVisibility(View.GONE);
                mGoodsDetailImagesRecyclerView.setVisibility(View.GONE);
            }


            //分类
            StringBuilder stringBuilder = new StringBuilder();
            if (null != tpcGoodsItemBean.getCategoryNames() && tpcGoodsItemBean.getCategoryNames().size() > 0) {
                for (String categoryName : tpcGoodsItemBean.getCategoryNames()) {
                    stringBuilder.append(categoryName).append("；");
                }
            }
            mCategoryTextView.setText(getString(R.string.formatString_categoryInfo, stringBuilder.toString()));

            //配送地址
            String addressInfo = tpcGoodsItemBean.getAddressTextInfo();
            if (!TextUtils.isEmpty(addressInfo)) {
                mGoodsDeliveryAddressTitleView.setVisibility(View.VISIBLE);
                mGoodsDeliveryAddressTextView.setVisibility(View.VISIBLE);
                mGoodsDeliveryAddressTextView.setText(addressInfo);
            } else {
                mGoodsDeliveryAddressTextView.setVisibility(View.GONE);
                mGoodsDeliveryAddressTitleView.setVisibility(View.GONE);
            }

            //权重
            mWeightTextView.setText(getString(R.string.formatString_weightInfo, tpcGoodsItemBean.getSort()));

            //驳回
            if (TpcGoodsItemBean.AUDITING_STATUS_REJECT.equals(tpcGoodsItemBean.getAuditingStatus())) {
                mRejectInfoTextView.setVisibility(View.VISIBLE);
                mRejectInfoTextView.setText(getString(R.string.formatString_rejectInfo, tpcGoodsItemBean.getReason()));
            } else {
                mRejectInfoTextView.setVisibility(View.GONE);
            }

            //根据商品状态展示隐藏相应的按钮
            if (TpcGoodsItemBean.AUDITING_STATUS_WAITING2AUDIT.equals(tpcGoodsItemBean.getAuditingStatus())) {//待审核
                mEditTextView.setVisibility(View.VISIBLE);
                mAgreeTextView.setVisibility(View.VISIBLE);
                mAgreeAndAddPriceTextView.setVisibility(View.VISIBLE);
                mRejectTextView.setVisibility(View.VISIBLE);
            } else if (TpcGoodsItemBean.AUDITING_STATUS_PASSED.equals(tpcGoodsItemBean.getAuditingStatus())) {//审核通过
                mEditTextView.setVisibility(View.VISIBLE);
                mModifyAddPriceTextView.setVisibility(View.VISIBLE);
                mSetLabelTextView.setVisibility(View.VISIBLE);
                mModifySortTextView.setVisibility(View.VISIBLE);
                mDeleteTextView.setVisibility(View.VISIBLE);
                //首单特惠和秒杀商品不能设为优选
                if (!ServerConstants.AUDITING_TYPE.FIRST_DISCOUNT.equals(tpcGoodsItemBean.getAuditingType())
                        && !ServerConstants.AUDITING_TYPE.LIMITED_TIME_DISCOUNT.equals(tpcGoodsItemBean.getAuditingType())) {
                    mSetClassTextView.setVisibility(View.VISIBLE);
                    mSetSuperiorTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @OnClick(R.id.mEditTextView)
    public void editTpcGoods() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_TPC_GOODS_ITEM_JSON, JsonUtils.toJson(tpcGoodsItemBean));
        readyGoForResult(TpcGoodsEditActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
    }

    @OnClick(R.id.mSetClassTextView)
    public void setCategoryTpcGoods() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_TPC_GOODS_ITEM_JSON, JsonUtils.toJson(tpcGoodsItemBean));
        readyGoForResult(SetTpcGoodsCategoryActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
    }

    @OnClick(R.id.mSetSuperiorTextView)
    public void setFinerTpcGoods() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_TPC_GOODS_ITEM_JSON, JsonUtils.toJson(tpcGoodsItemBean));
        readyGoForResult(SetTpcGoodsFinerActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
    }

    @OnClick(R.id.mSetLabelTextView)
    public void setLabelTpcGoods() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_TPC_GOODS_ITEM_JSON, JsonUtils.toJson(tpcGoodsItemBean));
        readyGoForResult(SetTpcGoodsLabelActivity.class, REQUEST_CODE_EDIT_GOODS, bundle);
    }

    @OnClick(R.id.mAgreeTextView)
    public void agreeTpcGoods() {
        mOptionPresenter.agree(tpcGoodsItemBean);
    }

    @OnClick(R.id.mAgreeAndAddPriceTextView)
    public void agreeAndAddPriceTpcGoods() {
        mOptionPresenter.agreeAndAddPrice(tpcGoodsItemBean);
    }

    @OnClick(R.id.mRejectTextView)
    public void rejectTpcGoods() {
        mOptionPresenter.reject(tpcGoodsItemBean);
    }

    @OnClick(R.id.mDeleteTextView)
    public void deleteTpcGoods() {
        mOptionPresenter.delete(tpcGoodsItemBean);
    }

    @OnClick(R.id.mModifyAddPriceTextView)
    public void modifyTpcGoodsAddPrice() {
        mOptionPresenter.modifyAddPrice(tpcGoodsItemBean);
    }

    @OnClick(R.id.mModifySortTextView)
    public void modifyTpcGoodsSort() {
        mOptionPresenter.modifySort(tpcGoodsItemBean);
    }

    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }
}
