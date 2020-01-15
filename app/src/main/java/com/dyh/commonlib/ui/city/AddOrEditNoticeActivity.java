package com.dyh.commonlib.ui.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.AddOrEditNoticeRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.NoticeItemBean;
import com.dyh.commonlib.presenter.AddOrEditNoticePresenter;
import com.dyh.commonlib.ui.adapter.SelectedDeliveryAddressListAdapter;
import com.dyh.commonlib.ui.common.SelectDeliveryAddressActivity;
import com.dyh.commonlib.ui.view.IAddOrEditNoticeView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.TagGroup;
import com.dyh.common.lib.weigit.picker.picker.DatePicker;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建或编辑消息公告
 */
public class AddOrEditNoticeActivity extends BaseMVPActivity implements IAddOrEditNoticeView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNoticeTagGroup)
    TagGroup mNoticeTagGroup;
    @BindView(R.id.mStartTimeTextView)
    TextView mStartTimeTextView;
    @BindView(R.id.mEndTimeTextView)
    TextView mEndTimeTextView;
    @BindView(R.id.mDeliverAddressRecyclerView)
    RecyclerView mDeliverAddressRecyclerView;
    @BindView(R.id.mCityRow)
    View mCityRow;
    @BindView(R.id.mCitySpinner)
    Spinner mCitySpinner;

    private SelectedDeliveryAddressListAdapter selectedListAdapter = new SelectedDeliveryAddressListAdapter(R.layout.item_selected_item);
    private final int REQUEST_SELECT_DELIVERY_ADDRESS = 100;//选择配送地址
    private NoticeItemBean noticeItemBean;
    private String cityId, cityName;
    private List<CityItemBean> cityList;

    private AddOrEditNoticePresenter mPreseneter = new AddOrEditNoticePresenter(this);

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
            }
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_notice;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {

        noticeItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_NOTICE_ITEM_JSON), NoticeItemBean.class);

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

        //初始化登录者相关信息
        //默认不需要选择城市
        mCityRow.setVisibility(View.GONE);
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (null != spEntity.loginResponseBean) {
            if (null != spEntity.loginResponseBean.getOper()) {
                cityId = spEntity.loginResponseBean.getOper().getCityId();
                cityName = spEntity.loginResponseBean.getOper().getCity();
            }
            //管理员需要选择城市
            if (spEntity.loginResponseBean.isAdmin()) {
                mPreseneter.loadCityList();
                mCityRow.setVisibility(View.VISIBLE);
            }
        }

        refreshEditDatas();
    }

    private void refreshEditDatas() {
        if (noticeItemBean == null) {
            return;
        }
        //设置消息公告
        mNoticeTagGroup.setTags(noticeItemBean.getNoticeNameList());
        
        //设置城市信息
        cityId = noticeItemBean.getCityId();
        cityName = noticeItemBean.getCity();
        //设置时间
        mStartTimeTextView.setText(noticeItemBean.getStartTimeStr());
        mEndTimeTextView.setText(noticeItemBean.getEndTimeStr());
        //设置配送地址列表
        List<NoticeItemBean.AddressListBean> addressListBeanList = noticeItemBean.getAddressList();
        if (null != addressListBeanList) {
            List<DeliveryAddressManageItemBean> list = new ArrayList<>();
            for (NoticeItemBean.AddressListBean addressListBean : addressListBeanList) {
                DeliveryAddressManageItemBean itemBean = new DeliveryAddressManageItemBean();
                itemBean.setDeliveryAddressId(addressListBean.getDeliveryAddressId());
                itemBean.setRemark(addressListBean.getDeliveryAddressName());
                list.add(itemBean);
            }
            selectedListAdapter.setNewData(list);
        }
    }


    @OnClick(R.id.mAddDeliveryAddressTextView)
    public void selectDeliveryAddress() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON, JsonUtils.toJson(selectedListAdapter.getData()));
        readyGoForResult(SelectDeliveryAddressActivity.class, REQUEST_SELECT_DELIVERY_ADDRESS, bundle);
    }

    @OnClick(R.id.mStartTimeTextView)
    public void selectStartTime() {
        showSelectDateDialog(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                String date = year + "-" + month + "-" + day;
                mStartTimeTextView.setText(date);
            }
        });
    }

    @OnClick(R.id.mEndTimeTextView)
    public void selectEndTime() {
        showSelectDateDialog(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                String date = year + "-" + month + "-" + day;
                mEndTimeTextView.setText(date);
            }
        });
    }

    @Override
    public void onLoadCitySuccess(List<CityItemBean> list) {
        if (list == null) {
            return;
        }
        CityItemBean allCity = new CityItemBean();
        allCity.id = "0";
        allCity.name = getString(R.string.all);
        list.add(0, allCity);
        int selectedIndex = 0;
        if (!TextUtils.isEmpty(cityId)) {
            for (int i = 0; i < list.size(); i++) {
                if (cityId.equals(list.get(i).id)) {
                    selectedIndex = i;
                    break;
                }
            }
        }

        this.cityList = list;


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (CityItemBean cityItemBean : list) {
            arrayAdapter.add(cityItemBean.name);
        }

        mCitySpinner.setAdapter(arrayAdapter);
        mCitySpinner.setSelection(selectedIndex);
    }

    @Override
    public void onOptionOk() {
        setResult(RESULT_OK);
        finish();
    }

    private void save() {
        //城市是否选择
        if (mCityRow.getVisibility() == View.VISIBLE && TextUtils.isEmpty(cityId)) {
            EasyToast.getDEFAULT().show(R.string.selectCityPlease);
            return;
        }

        //检查公告内容
        if (mNoticeTagGroup.getTags().length <= 0) {
            EasyToast.getDEFAULT().show(R.string.addNoticePlease);
            return;
        }

        //检查时间
        if (TextUtils.isEmpty(mStartTimeTextView.getText()) || TextUtils.isEmpty(mEndTimeTextView.getText())) {
            EasyToast.getDEFAULT().show(R.string.selectTimePlease);
            return;
        }

        //检查配送地址是否选择
        if (selectedListAdapter.getItemCount() <= 0) {
            EasyToast.getDEFAULT().show(R.string.selectDeliveryAddressPlease);
            return;
        }

        AddOrEditNoticeRequestBean requestBean = new AddOrEditNoticeRequestBean();
        if (null != noticeItemBean) {
            requestBean.noticeId = noticeItemBean.getNoticeId();
        }
        requestBean.city = cityName;
        requestBean.cityId = cityId;
        requestBean.noticeNameList = Arrays.asList(mNoticeTagGroup.getTags());
        requestBean.startTime = mStartTimeTextView.getText().toString();
        requestBean.endTime = mEndTimeTextView.getText().toString();
        requestBean.deliveryAddressIds = new ArrayList<>();
        List<DeliveryAddressManageItemBean> deliveryAddressItemBeans = selectedListAdapter.getData();
        for (DeliveryAddressManageItemBean deliveryAddressItemBean : deliveryAddressItemBeans) {
            requestBean.deliveryAddressIds.add(deliveryAddressItemBean.getDeliveryAddressId());
        }
        mPreseneter.addOrEdit(requestBean);
    }

    DatePicker datePicker;

    /**
     * 初始化选择时间对话框
     */
    private void initTimeDialog() {
        datePicker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        datePicker.setWidth((int) (datePicker.getScreenWidthPixels()));
        Calendar currentCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);
        datePicker.setRangeStart(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1, currentCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setSelectedItem(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1, currentCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 展示选择时间对话框
     *
     * @param onYearMonthDayPickListener
     */
    private void showSelectDateDialog(DatePicker.OnYearMonthDayPickListener onYearMonthDayPickListener) {
        if (null == datePicker) {
            initTimeDialog();
        }
        datePicker.setOnDatePickListener(onYearMonthDayPickListener);
        datePicker.show();
    }
}
