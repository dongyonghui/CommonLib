package com.dyh.commonlib.ui.tpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.constants.UIConstants;
import com.dyh.commonlib.entity.request.EditTpcGoodsRequestBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.presenter.TpcGoodsEditPresenter;
import com.dyh.commonlib.presenter.UploadImagePresenter;
import com.dyh.commonlib.ui.adapter.GoodsEditImagesAdapter;
import com.dyh.commonlib.ui.view.ITpcGoodsEditView;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.picture.PictureSelector;
import com.dyh.common.lib.picture.config.PictureConfig;
import com.dyh.common.lib.picture.entity.LocalMedia;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 编辑头牌菜
 */
public class TpcGoodsEditActivity extends BaseMVPActivity implements ITpcGoodsEditView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mGoodsNameEditText)
    EditText mGoodsNameEditText;
    @BindView(R.id.mGoodsRemarkEditText)
    EditText mGoodsRemarkEditText;
    @BindView(R.id.mOriginalPriceEditText)
    EditText mOriginalPriceEditText;
    @BindView(R.id.mSupplyPriceEditText)
    EditText mSupplyPriceEditText;
    @BindView(R.id.mAddPriceEditText)
    EditText mAddPriceEditText;
    @BindView(R.id.mRealPayTextView)
    TextView mRealPayTextView;
    @BindView(R.id.mEveryDayLimitedEditText)
    EditText mEveryDayLimitedEditText;
    @BindView(R.id.mEveryOneEveryDayLimitedEditText)
    EditText mEveryOneEveryDayLimitedEditText;
    @BindView(R.id.mFirstDiscountCheckBox)
    CheckBox mFirstDiscountCheckBox;
    @BindView(R.id.mLimitedTimeDiscountCheckBox)
    CheckBox mLimitedTimeDiscountCheckBox;
    @BindView(R.id.mGoodsMainImagesRecyclerView)
    RecyclerView mGoodsMainImagesRecyclerView;
    @BindView(R.id.mGoodsDetailImagesRecyclerView)
    RecyclerView mGoodsDetailImagesRecyclerView;

    TpcGoodsItemBean tpcGoodsItemBean;
    TpcGoodsEditPresenter mPresenter = new TpcGoodsEditPresenter(this);
    UploadImagePresenter mUploadImagePresenter = new UploadImagePresenter(this);
    GoodsEditImagesAdapter detailImagesAdapter = new GoodsEditImagesAdapter(R.layout.item_tpc_goods_edit_images);
    GoodsEditImagesAdapter mainImagesAdapter = new GoodsEditImagesAdapter(R.layout.item_tpc_goods_edit_images);

    GoodsEditImagesAdapter currentOptionAdapter;//当前操作的适配器
    BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            currentOptionAdapter = (GoodsEditImagesAdapter) adapter;
            switch (view.getId()) {
                case R.id.mDeleteImageButton:
                    ((GoodsEditImagesAdapter) adapter).delete(position);
                    break;
                case R.id.mImageView:
                    if (GoodsEditImagesAdapter.ITEM_ADD.equals(adapter.getItem(position))) {
                        CommonImagesUtil.selectGoodsImage(TpcGoodsEditActivity.this);
                    }
                    break;
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    if (null != selectList && selectList.size() > 0) {
                        mUploadImagePresenter.uploadImage(new File(selectList.get(0).getCutPath()));
                    }
                    break;
            }
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tpc_goods_edit;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //根据传入的商品信息展示数据
        tpcGoodsItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_TPC_GOODS_ITEM_JSON), TpcGoodsItemBean.class);

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
        //收单特惠和限时秒杀互斥
        mFirstDiscountCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLimitedTimeDiscountCheckBox.setChecked(false);
                }
            }
        });
        mLimitedTimeDiscountCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFirstDiscountCheckBox.setChecked(false);
                }
            }
        });


        mainImagesAdapter.setOnItemChildClickListener(onItemChildClickListener);
        detailImagesAdapter.setOnItemChildClickListener(onItemChildClickListener);
        initUIData();
    }

    /**
     * 保存数据
     */
    public void save() {
        if (mainImagesAdapter.getItemCount() <= 1) {
            EasyToast.getDEFAULT().show(R.string.uploadGoodsImage);
            return;
        }
        EditTpcGoodsRequestBean requestBean = new EditTpcGoodsRequestBean();
        requestBean.setName(mGoodsNameEditText.getText().toString());
        requestBean.setDesc(mGoodsRemarkEditText.getText().toString());
        requestBean.setLimitNum(mEveryDayLimitedEditText.getText().toString());
        requestBean.setPersonLimitNum(mEveryOneEveryDayLimitedEditText.getText().toString());

        if (mFirstDiscountCheckBox.isChecked()) {
            requestBean.setWareType(ServerConstants.WareType.FIRST_DISCOUNT);
        } else if (mLimitedTimeDiscountCheckBox.isChecked()) {
            requestBean.setWareType(ServerConstants.WareType.LIMITED_TIME_DISCOUNT);
        }
        requestBean.setAuditingType(tpcGoodsItemBean.getAuditingType());
        requestBean.setShopId(tpcGoodsItemBean.getShopId());
        requestBean.setSpu(tpcGoodsItemBean.getSpu());
        requestBean.setPictures(mainImagesAdapter.getItem(0));
        List<String> list = new ArrayList<>(mainImagesAdapter.getData());
        list.remove(list.size() - 1);
        requestBean.setPictureList(list);
        //设置价格信息，设置到规格信息里
        List<TpcGoodsItemBean.StandardsBean> standardsBeanList = tpcGoodsItemBean.getStandards();
        if (null != standardsBeanList && standardsBeanList.size() > 0) {
            TpcGoodsItemBean.StandardsBean standardsBean = standardsBeanList.get(0);
            standardsBean.setOnlineStorePrice(mOriginalPriceEditText.getText().toString());
            standardsBean.setOnlineStoreGroupPrice(mSupplyPriceEditText.getText().toString());
            standardsBean.setAddPrice(mAddPriceEditText.getText().toString());
            list = new ArrayList<>(detailImagesAdapter.getData());
            list.remove(list.size() - 1);
            standardsBean.setPictureList(list);
        }
        requestBean.setStandards(standardsBeanList);
        mPresenter.update(requestBean);
    }

    @Override
    public void onUploadImageSuccess(String url) {
        if (null != currentOptionAdapter) {
            currentOptionAdapter.addListTop(url);
        }
    }

    @Override
    public void onSubmitSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    private void initUIData() {
        if (null != tpcGoodsItemBean) {
            mGoodsNameEditText.setText(tpcGoodsItemBean.getName());
            mGoodsRemarkEditText.setText(tpcGoodsItemBean.getDescription());
            if (null != tpcGoodsItemBean.getStandards() && tpcGoodsItemBean.getStandards().size() > 0) {
                TpcGoodsItemBean.StandardsBean standardsBean = tpcGoodsItemBean.getStandards().get(0);
                mOriginalPriceEditText.setText(standardsBean.getOnlineStorePrice());
                mSupplyPriceEditText.setText(standardsBean.getOnlineStoreGroupPrice());
                mAddPriceEditText.setText(standardsBean.getAddPrice());

                //详情图片
                detailImagesAdapter.addListBottom(standardsBean.getPictureList());
                mGoodsDetailImagesRecyclerView.setLayoutManager(new GridLayoutManager(this, UIConstants.COUNT_GOODS_DETAIL_IMAGES_GRID_COL));
                mGoodsDetailImagesRecyclerView.setAdapter(detailImagesAdapter);
            }
            mEveryDayLimitedEditText.setText(tpcGoodsItemBean.getLimitNum());
            mEveryOneEveryDayLimitedEditText.setText(tpcGoodsItemBean.getPersonLimitNum());

            //展示图片
            mainImagesAdapter.addListBottom(tpcGoodsItemBean.getPictureList());

            mGoodsMainImagesRecyclerView.setLayoutManager(new GridLayoutManager(this, UIConstants.COUNT_GOODS_DETAIL_IMAGES_GRID_COL));
            mGoodsMainImagesRecyclerView.setAdapter(mainImagesAdapter);

            //增加添加按钮
            mainImagesAdapter.addListBottom(GoodsEditImagesAdapter.ITEM_ADD);
            detailImagesAdapter.addListBottom(GoodsEditImagesAdapter.ITEM_ADD);

            //根据类型选中复选框
            mFirstDiscountCheckBox.setChecked(ServerConstants.AUDITING_TYPE.FIRST_DISCOUNT.equals(tpcGoodsItemBean.getAuditingType()));
            mLimitedTimeDiscountCheckBox.setChecked(ServerConstants.AUDITING_TYPE.LIMITED_TIME_DISCOUNT.equals(tpcGoodsItemBean.getAuditingType()));
        }
    }
}
