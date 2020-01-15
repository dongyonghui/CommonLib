package com.dyh.commonlib.ui.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.AddOrEditBannerRequestBean;
import com.dyh.commonlib.entity.response.BannerItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.presenter.AddOrEditBannerPresenter;
import com.dyh.commonlib.presenter.UploadImagePresenter;
import com.dyh.commonlib.ui.adapter.SelectedDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.common.SelectDeliveryAddressActivity;
import com.dyh.commonlib.ui.view.IAddOrEditBannerView;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.picture.PictureSelector;
import com.dyh.common.lib.picture.config.PictureConfig;
import com.dyh.common.lib.picture.entity.LocalMedia;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建或编辑banner
 */
public class AddOrEditBannerActivity extends BaseMVPActivity implements IAddOrEditBannerView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mImageView)
    ImageView mImageView;
    @BindView(R.id.mDeliverAddressRecyclerView)
    RecyclerView mDeliverAddressRecyclerView;
    @BindView(R.id.mTypeRadioGroup)
    RadioGroup mTypeRadioGroup;
    @BindView(R.id.mToPageEditText)
    EditText mToPageEditText;

    private BannerItemBean bannerItemBean;
    private String imageUrl;//图片的url
    private UploadImagePresenter uploadImagePresenter = new UploadImagePresenter(this);
    private AddOrEditBannerPresenter mPresenter = new AddOrEditBannerPresenter(this);
    private SelectedDeliveryAddressListAdapter selectedListAdapter = new SelectedDeliveryAddressListAdapter(R.layout.item_selected_item);
    private final int REQUEST_SELECT_DELIVERY_ADDRESS = 100;//选择配送地址

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
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
                        uploadImagePresenter.uploadImage(new File(selectList.get(0).getCutPath()));
                    }
                    break;
                case REQUEST_SELECT_DELIVERY_ADDRESS://选择配送地址
                    // 添加已选中的选项
                    String json = data.getStringExtra(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON);
                    if (!TextUtils.isEmpty(json)) {
                        List<DeliveryAddressManageItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<DeliveryAddressManageItemBean>>() {
                        }.getType());
                        selectedListAdapter.setNewData(selectedList);
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
        return R.layout.activity_add_or_edit_banner;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        bannerItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_BANNER_ITEM_JSON), BannerItemBean.class);

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

        //选择的配送地址列表
        mDeliverAddressRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDeliverAddressRecyclerView.setAdapter(selectedListAdapter);
        selectedListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectedListAdapter.remove(position);
            }
        });

        refreshEditDatas();
    }

    /**
     * 刷新编辑数据
     */
    private void refreshEditDatas() {
        if (bannerItemBean == null) {
            return;
        }

        mCommonTitleBar.getCenterTextView().setText(R.string.editBanner);
        imageUrl = CommonImagesUtil.getFullImageHttpUrl(bannerItemBean.getImageUrl());
        Glide.with(this).load(CommonImagesUtil.getFullImageHttpUrl(imageUrl)).into(mImageView);

        selectedListAdapter.setNewData(bannerItemBean.getDeliveryAddress());
        mToPageEditText.setText(bannerItemBean.getToUrl());
        //连接类型
        if (ServerConstants.BANNER_TYPE.IN_APP_PAGES.equals(bannerItemBean.getType())) {
            mTypeRadioGroup.check(R.id.mInAppPagesRadioButton);
        } else if (ServerConstants.BANNER_TYPE.WEB_URL.equals(bannerItemBean.getType())) {
            mTypeRadioGroup.check(R.id.mWebUrlRadioButton);
        }
    }

    @OnClick(R.id.mAddImageTextView)
    public void selectImage() {
        CommonImagesUtil.selectBannerImage(this);
    }

    @OnClick(R.id.mAddDeliveryAddressTextView)
    public void selectDeliveryAddress() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON, JsonUtils.toJson(selectedListAdapter.getData()));
        readyGoForResult(SelectDeliveryAddressActivity.class, REQUEST_SELECT_DELIVERY_ADDRESS, bundle);
    }


    @Override
    public void onSubmitSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onUploadImageSuccess(String url) {
        this.imageUrl = url;
        Glide.with(this).load(CommonImagesUtil.getFullImageHttpUrl(imageUrl)).into(mImageView);
    }

    private void save() {
        if (TextUtils.isEmpty(imageUrl)) {
            EasyToast.getDEFAULT().show(R.string.addImagePlease);
            return;
        }
        AddOrEditBannerRequestBean requestBean = new AddOrEditBannerRequestBean();
        if (null != bannerItemBean) {
            requestBean.bannerId = bannerItemBean.getBannerId();
        }

        //配送地址
        requestBean.deliveryAddress = selectedListAdapter.getData();

        //跳转类型
        switch (mTypeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mInAppPagesRadioButton:
                requestBean.type = ServerConstants.BANNER_TYPE.IN_APP_PAGES;
                break;
            case R.id.mWebUrlRadioButton:
                requestBean.type = ServerConstants.BANNER_TYPE.WEB_URL;
                break;
        }

        //图片
        requestBean.imageUrl = this.imageUrl;
        requestBean.toUrl = mToPageEditText.getText().toString();

        mPresenter.addOrEditBanner(requestBean);
    }

}
