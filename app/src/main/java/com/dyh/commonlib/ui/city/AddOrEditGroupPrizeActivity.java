package com.dyh.commonlib.ui.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.AddOrEditGroupPrizeRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.GroupPrizeItemBean;
import com.dyh.commonlib.presenter.AddOrEditGropPrizePresenter;
import com.dyh.commonlib.presenter.UploadImagePresenter;
import com.dyh.commonlib.ui.adapter.AddGroupPrizeListAdapter;
import com.dyh.commonlib.ui.adapter.SelectedDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.common.SelectDeliveryAddressActivity;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.commonlib.ui.view.IUploadImageView;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.picture.PictureSelector;
import com.dyh.common.lib.picture.config.PictureConfig;
import com.dyh.common.lib.picture.entity.LocalMedia;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建或编辑团队奖励
 */
public class AddOrEditGroupPrizeActivity extends BaseMVPActivity implements IDefaultOptionView, IUploadImageView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mDeliverAddressRecyclerView)
    RecyclerView mDeliverAddressRecyclerView;
    @BindView(R.id.mGroupPrizeRecyclerView)
    RecyclerView mGroupPrizeRecyclerView;
    @BindView(R.id.mUseNotInputAddressCheckBox)
    CheckBox mUseNotInputAddressCheckBox;

    private SelectedDeliveryAddressListAdapter selectedListAdapter = new SelectedDeliveryAddressListAdapter(R.layout.item_selected_item);
    private AddGroupPrizeListAdapter groupPrizeListAdapter = new AddGroupPrizeListAdapter(R.layout.item_add_group_prize);
    private final int REQUEST_SELECT_DELIVERY_ADDRESS = 100;//选择配送地址
    private GroupPrizeItemBean groupPrizeItemBean;
    private AddOrEditGropPrizePresenter mPresenter = new AddOrEditGropPrizePresenter(this);
    private UploadImagePresenter uploadImagePresenter = new UploadImagePresenter(this);
    private int currentOptionPrizePosition = -1;//当前操作的奖励项索引

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            switch (requestCode) {
                case REQUEST_SELECT_DELIVERY_ADDRESS://选择配送地址
                    // 添加已选中的选项
                    String json = data.getStringExtra(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON);
                    if (!TextUtils.isEmpty(json)) {
                        List<DeliveryAddressManageItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<DeliveryAddressManageItemBean>>() {
                        }.getType());
                        selectedListAdapter.setNewData(selectedList);
                    }
                    break;
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
            }
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_group_prize;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {

        groupPrizeItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_GROUP_PRIZE_ITEM_JSON), GroupPrizeItemBean.class);

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

        //奖励列表
        mGroupPrizeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGroupPrizeRecyclerView.setAdapter(groupPrizeListAdapter);
        groupPrizeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentOptionPrizePosition = position;
                CommonImagesUtil.selectGoodsImage(AddOrEditGroupPrizeActivity.this);
            }
        });

        refreshEditDatas();
    }


    @OnClick(R.id.mAddDeliveryAddressTextView)
    public void selectDeliveryAddress() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON, JsonUtils.toJson(selectedListAdapter.getData()));
        readyGoForResult(SelectDeliveryAddressActivity.class, REQUEST_SELECT_DELIVERY_ADDRESS, bundle);
    }

    @Override
    public void onOptionOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onUploadImageSuccess(String url) {
        GroupPrizeItemBean.WareListBean item = groupPrizeListAdapter.getItem(currentOptionPrizePosition);
        if (item != null) {
            item.setPicture(CommonImagesUtil.getFullImageHttpUrl(url));
            groupPrizeListAdapter.notifyItemChanged(currentOptionPrizePosition);
        }
    }


    private void refreshEditDatas() {
        //如果是新建，则添加三个空的团体奖励
        if (groupPrizeItemBean == null) {
            for (int i = 0; i < 3; i++) {
                groupPrizeListAdapter.addListBottom(new GroupPrizeItemBean.WareListBean());
            }
            return;
        }

        mCommonTitleBar.getCenterTextView().setText(R.string.editGroupPrize);

        //是否使用非录入的地址
        mUseNotInputAddressCheckBox.setChecked(ServerConstants.DEFAULT_INT_TRUE.equals(groupPrizeItemBean.getDefaultAward()));

        //设置团体奖励
        groupPrizeListAdapter.addListBottom(groupPrizeItemBean.getWareList());

        //设置配送地址列表
        List<DeliveryAddressManageItemBean> addressListBeanList = groupPrizeItemBean.getDeliveryAddressList();
        if (null != addressListBeanList) {
            List<DeliveryAddressManageItemBean> list = new ArrayList<>();
            for (DeliveryAddressManageItemBean addressListBean : addressListBeanList) {
                DeliveryAddressManageItemBean itemBean = new DeliveryAddressManageItemBean();
                itemBean.setDeliveryAddressId(addressListBean.getDeliveryAddressId());
                itemBean.setRemark(addressListBean.getTitle());
                itemBean.setTitle(addressListBean.getTitle());
                list.add(itemBean);
            }
            selectedListAdapter.setNewData(list);
        }
    }

    private void save() {
        //检查配送地址是否选择
        if (selectedListAdapter.getItemCount() <= 0) {
            EasyToast.getDEFAULT().show(R.string.selectDeliveryAddressPlease);
            return;
        }

        List<GroupPrizeItemBean.WareListBean> wareListBeanList = groupPrizeItemBean.getWareList();
        int maxPersonNumber = 0;
        for (int i = 0; i < wareListBeanList.size(); i++) {
            if(TextUtils.isEmpty(wareListBeanList.get(i).getName())){
                EasyToast.getDEFAULT().show(R.string.inputThreePrizeNamePlease);
                return;
            }
            if(TextUtils.isEmpty(wareListBeanList.get(i).getPersonNum())){
                EasyToast.getDEFAULT().show(R.string.inputThreePrizePersonNumPlease);
                return;
            }
            if(TextUtils.isEmpty(wareListBeanList.get(i).getPicture())){
                EasyToast.getDEFAULT().show(R.string.addThreePrizeImagePlease);
                return;
            }
            int num = MathUtil.getIntegerNumber(wareListBeanList.get(i).getPersonNum());
            if(i == 0 && num <= 0){
                EasyToast.getDEFAULT().show(R.string.prizeBiggerInfo1);
                return;
            }
            if(i == 1 && num <= maxPersonNumber){
                EasyToast.getDEFAULT().show(R.string.prizeBiggerInfo2);
                return;
            }
            if(i == 2 && num <= maxPersonNumber){
                EasyToast.getDEFAULT().show(R.string.prizeBiggerInfo3);
                return;
            }
            maxPersonNumber = num;
        }

        AddOrEditGroupPrizeRequestBean requestBean = new AddOrEditGroupPrizeRequestBean();
        if (null != groupPrizeItemBean) {
            requestBean.setUserTeamAwardId(groupPrizeItemBean.getUserTeamAwardId());
        }

        //默认城市奖励 0 不是 1 是
        requestBean.setDefaultAward(mUseNotInputAddressCheckBox.isChecked() ? ServerConstants.DEFAULT_INT_TRUE : ServerConstants.DEFAULT_INT_FALSE);

        //设置奖励信息
        requestBean.setWareList(groupPrizeListAdapter.getData());

        //设置配送地址
        List<AddOrEditGroupPrizeRequestBean.DeliveryAddressListBean> addressListBeanList = new ArrayList<>();
        List<DeliveryAddressManageItemBean> deliveryAddressItemBeans = selectedListAdapter.getData();
        for (DeliveryAddressManageItemBean deliveryAddressItemBean : deliveryAddressItemBeans) {
            AddOrEditGroupPrizeRequestBean.DeliveryAddressListBean item = new AddOrEditGroupPrizeRequestBean.DeliveryAddressListBean();
            item.setDeliveryAddressId(deliveryAddressItemBean.getDeliveryAddressId());
            item.setDeliveryAddressName(deliveryAddressItemBean.getTitle());
            addressListBeanList.add(item);
        }
        requestBean.setDeliveryAddressList(addressListBeanList);
        mPresenter.addOrEdit(requestBean);
    }
}
