package com.dyh.commonlib.ui.delivery_address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.local.AddTakePointForResultBean;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.AddOrEditDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.presenter.AddOrEditDeliveryAddressPresenter;
import com.dyh.commonlib.ui.adapter.SelectedShopListAdapter;
import com.dyh.commonlib.ui.adapter.SelectedTakePointsListAdapter;
import com.dyh.commonlib.ui.common.SelectShopsActivity;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加或编辑配送地址
 */
public class AddOrEditDeliveryAddressActivity extends BaseMVPActivity implements IDefaultOptionView {

    private final int REQUEST_ADD_TAKE_POINT = 100;//添加取餐点
    private final int REQUEST_SELECT_SHOPS = 110;//选择店铺

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameAutoCompleteTextView)
    AutoCompleteTextView mNameAutoCompleteTextView;
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.mDetailAddressEditText)
    EditText mDetailAddressEditText;
    @BindView(R.id.mTakePointsRecyclerView)
    RecyclerView mTakePointsRecyclerView;
    @BindView(R.id.mAroundShopRecyclerView)
    RecyclerView mAroundShopRecyclerView;
    @BindView(R.id.mSendModeRadioGroup)
    RadioGroup mSendModeRadioGroup;
    @BindView(R.id.mFirstCustomerDeliveryPriceEditText)
    EditText mFirstCustomerDeliveryPriceEditText;
    @BindView(R.id.mNotFirstCustomerDeliveryPriceEditText)
    EditText mNotFirstCustomerDeliveryPriceEditText;
    @BindView(R.id.mNoDeliveryMoneyPriceEditText)
    EditText mNoDeliveryMoneyPriceEditText;
    @BindView(R.id.mOnceLimitedPriceEditText)
    EditText mOnceLimitedPriceEditText;
    @BindView(R.id.mHoursSpinner)
    Spinner mHoursSpinner;
    @BindView(R.id.mMinutesSpinner)
    Spinner mMinutesSpinner;

    private AMap aMap;
    private LatLng mMarkerLatLng;//标记点的位置
    private final SelectedTakePointsListAdapter selectedTakePointsListAdapter = new SelectedTakePointsListAdapter(R.layout.item_selected_item);
    private final SelectedShopListAdapter selectedShopListAdapter = new SelectedShopListAdapter(R.layout.item_selected_item);
    private AddOrEditDeliveryAddressPresenter mPresenter = new AddOrEditDeliveryAddressPresenter(this);
    private DeliveryAddressManageItemBean deliveryAddressManageItemBean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && null != data) {
            switch (requestCode) {
                case REQUEST_ADD_TAKE_POINT:
                    selectedTakePointsListAdapter.addListTop(JsonUtils.object(data.getStringExtra(IntentConstants.KEY_ADD_TAKE_POINT_RESULT_JSON), AddTakePointForResultBean.class));
                    break;
                case REQUEST_SELECT_SHOPS://选择店铺
                    String json = data.getStringExtra(IntentConstants.KEY_SELECTED_SHOP_LIST_RESULT_JSON);
                    if (!TextUtils.isEmpty(json)) {
                        List<ShopItemBean> selectedList = JsonUtils.object(json, new TypeToken<List<ShopItemBean>>() {
                        }.getType());
                        selectedShopListAdapter.setNewData(selectedList);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_delivery_address;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //获取编辑数据
        deliveryAddressManageItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_DELIVERY_ADDRESS_ITEM_JSON), DeliveryAddressManageItemBean.class);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

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
        initMap();
        //点击选中的店铺项后删除
        selectedShopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectedShopListAdapter.remove(position);
            }
        });

        //设置取餐点适配器
        mTakePointsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mTakePointsRecyclerView.setAdapter(selectedTakePointsListAdapter);
        //设置周边店铺适配器
        mAroundShopRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAroundShopRecyclerView.setAdapter(selectedShopListAdapter);

        initEditData();
    }

    private void initEditData() {
        if (deliveryAddressManageItemBean == null) {
            return;
        }
        mCommonTitleBar.getCenterTextView().setText(R.string.editDeliveryAddress);
        if (ServerConstants.DeliveryAddressUseType.TAKE_SELF.equals(deliveryAddressManageItemBean.getUseType())) {
            mSendModeRadioGroup.check(R.id.mTakeSelfRadioButton);
        } else if (ServerConstants.DeliveryAddressUseType.SEND2HOME.equals(deliveryAddressManageItemBean.getUseType())) {
            mSendModeRadioGroup.check(R.id.mSend2homeRadioButton);
        }
        mNameAutoCompleteTextView.setText(deliveryAddressManageItemBean.getRemark());
        LatLng positionLatLng = new LatLng(MathUtil.getDoubleNumber(deliveryAddressManageItemBean.getLat()), MathUtil.getDoubleNumber(deliveryAddressManageItemBean.getLon()));
        setMarker(positionLatLng);
        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                positionLatLng, 18, 30, 30)));
        mDetailAddressEditText.setText(deliveryAddressManageItemBean.getAddress());
        //设置取餐点
        if (null != deliveryAddressManageItemBean.getMealAddresses()) {
            List<AddTakePointForResultBean> takePointList = new ArrayList<>();
            for (DeliveryAddressManageItemBean.MealAddressBean mealAddress : deliveryAddressManageItemBean.getMealAddresses()) {
                AddTakePointForResultBean item = new AddTakePointForResultBean();
                item.selectedPosition = new LatLng(MathUtil.getDoubleNumber(mealAddress.lat), MathUtil.getDoubleNumber(mealAddress.lon));
                item.name = mealAddress.mealAddress;
                takePointList.add(item);
            }
            selectedTakePointsListAdapter.setNewData(takePointList);
        }
        //设置店铺
        selectedShopListAdapter.setNewData(deliveryAddressManageItemBean.getShops());
        mFirstCustomerDeliveryPriceEditText.setText(deliveryAddressManageItemBean.getFirstDeliveryFee());
        mNotFirstCustomerDeliveryPriceEditText.setText(deliveryAddressManageItemBean.getDeliveryFee());
        mNoDeliveryMoneyPriceEditText.setText(deliveryAddressManageItemBean.getFreeMoney());
        mOnceLimitedPriceEditText.setText(deliveryAddressManageItemBean.getPersonPayMoney());

        //设置下班时间
        String offTime = deliveryAddressManageItemBean.getOffDutyTime();
        if (!TextUtils.isEmpty(offTime) && offTime.contains(":")) {
            String[] timeArray = offTime.split(":");
            String[] hours = getResources().getStringArray(R.array.offWorkHours);
            String[] minuts = getResources().getStringArray(R.array.offWorkMinutes);

            int hIndex = 0, mIndex = 0;
            for (int i = 0; i < hours.length; i++) {
                if (hours[i].equals(timeArray[0])) {
                    hIndex = i;
                    break;
                }
            }
            for (int i = 0; i < minuts.length; i++) {
                if (minuts[i].equals(timeArray[1])) {
                    mIndex = i;
                    break;
                }
            }
            mHoursSpinner.setSelection(hIndex);
            mMinutesSpinner.setSelection(mIndex);
        }
    }


    /**
     * 添加取餐点
     */
    @OnClick(R.id.mTakePointRow)
    public void addTakePoint() {
        if (mMarkerLatLng == null) {
            EasyToast.getDEFAULT().show(R.string.notSelectPointsOnMap);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentConstants.KEY_MAP_LATLAN, mMarkerLatLng);
        readyGoForResult(AddTakePointsOnMapActivity.class, REQUEST_ADD_TAKE_POINT, bundle);
    }

    /**
     * 添加取餐点
     */
    @OnClick(R.id.mAroundShopRow)
    public void addAroundShop() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstants.KEY_SELECTED_SHOP_LIST_RESULT_JSON, JsonUtils.toJson(selectedShopListAdapter.getData()));
        readyGoForResult(SelectShopsActivity.class, REQUEST_SELECT_SHOPS, bundle);
    }


    /**
     * 保存数据
     */
    private void save() {
        if (TextUtils.isEmpty(mNameAutoCompleteTextView.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputDeliverAddressNamePlease);
            return;
        }
        //选择的地图点
        if (null == mMarkerLatLng) {
            EasyToast.getDEFAULT().show(R.string.selectDeliveryAddressPositionPlease);
            return;
        }
        if (TextUtils.isEmpty(mDetailAddressEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputDetailAddressPlease);
            return;
        }

        if (selectedTakePointsListAdapter.getItemCount() == 0 && mSendModeRadioGroup.getCheckedRadioButtonId() == R.id.mTakeSelfRadioButton) {
            EasyToast.getDEFAULT().show(R.string.addTakePointsPlease);
            return;
        }

        if (TextUtils.isEmpty(mFirstCustomerDeliveryPriceEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputFirstCustomerDeliveryPricePlease);
            return;
        }
        if (TextUtils.isEmpty(mNotFirstCustomerDeliveryPriceEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputNotFirstCustomerDeliveryPricePlease);
            return;
        }

        //单人起购价必须小于等于包邮价
        double oncePrice = MathUtil.getDoubleNumber(mOnceLimitedPriceEditText.getText().toString());
        double freePrice = MathUtil.getDoubleNumber(mNoDeliveryMoneyPriceEditText.getText().toString());
        if (oncePrice > 0 && oncePrice > freePrice) {
            EasyToast.getDEFAULT().show(R.string.freeDeliveryPriceCantSaveInfo);
            return;
        }

        AddOrEditDeliveryAddressRequestBean requestBean = new AddOrEditDeliveryAddressRequestBean();
        if (null != deliveryAddressManageItemBean) {
            requestBean.setDeliveryAddressId(deliveryAddressManageItemBean.getDeliveryAddressId());
        }
        requestBean.setRemark(mNameAutoCompleteTextView.getText().toString());
        requestBean.setAddress(mDetailAddressEditText.getText().toString());
        //非首个用户
        requestBean.setDeliveryFee(mNotFirstCustomerDeliveryPriceEditText.getText().toString());
        //首个用户
        requestBean.setFirstDeliveryFee(mFirstCustomerDeliveryPriceEditText.getText().toString());
        //包邮价
        requestBean.setFreeMoney(mNoDeliveryMoneyPriceEditText.getText().toString());//
        //地图经纬度
        requestBean.setLat(String.valueOf(mMarkerLatLng.latitude));
        requestBean.setLon(String.valueOf(mMarkerLatLng.longitude));
        //取餐点
        List<AddTakePointForResultBean> list = selectedTakePointsListAdapter.getData();
        List<AddOrEditDeliveryAddressRequestBean.MealAddressesBean> mealAddressesBeanList = new ArrayList<>();
        for (AddTakePointForResultBean forResultBean : list) {
            AddOrEditDeliveryAddressRequestBean.MealAddressesBean item = new AddOrEditDeliveryAddressRequestBean.MealAddressesBean();
            item.setMealAddress(forResultBean.name);
            item.setLat(String.valueOf(forResultBean.selectedPosition.latitude));
            item.setLon(String.valueOf(forResultBean.selectedPosition.longitude));
            mealAddressesBeanList.add(item);
        }
        requestBean.setMealAddresses(mealAddressesBeanList);

        //下班时间
        requestBean.setOffDutyTime(mHoursSpinner.getSelectedItem() + ":" + mMinutesSpinner.getSelectedItem());

        //单人起购价
        requestBean.setPersonPayMoney(mOnceLimitedPriceEditText.getText().toString());
        //设置配送模式
        switch (mSendModeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mSend2homeRadioButton:
                requestBean.setUseType(ServerConstants.DeliveryAddressUseType.SEND2HOME);
                break;
            case R.id.mTakeSelfRadioButton:
                requestBean.setUseType(ServerConstants.DeliveryAddressUseType.TAKE_SELF);
                break;
        }

        //周边店铺
        requestBean.setShops(selectedShopListAdapter.getData());

        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (null != spEntity.loginResponseBean && null != spEntity.loginResponseBean.getOper()) {
            requestBean.setCity(spEntity.loginResponseBean.getOper().getCity());
            requestBean.setCityId(spEntity.loginResponseBean.getOper().getCityId());
            requestBean.setProvince(spEntity.loginResponseBean.getOper().getProvince());
            requestBean.setProvinceId(spEntity.loginResponseBean.getOper().getProvinceId());
            requestBean.setDistrict(spEntity.loginResponseBean.getOper().getDistrict());
            requestBean.setDistrictId(spEntity.loginResponseBean.getOper().getDistrictId());
        }
        mPresenter.addOrEdit(requestBean);
    }


    private void initMap() {
        //初始化地图控制器对象
        aMap = mMapView.getMap();
        if (null == deliveryAddressManageItemBean) {
            aMap.setMyLocationEnabled(true);
        }
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMarker(latLng);
                setPositionInfo(new LatLonPoint(latLng.latitude, latLng.longitude));
            }
        });
        //搜索POI
        mNameAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString().trim();
                if (!TextUtils.isEmpty(newText)) {
                    //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                    InputtipsQuery inputquery = new InputtipsQuery(newText, "");
                    Inputtips inputTips = new Inputtips(AddOrEditDeliveryAddressActivity.this, inputquery);
                    inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                        @Override
                        public void onGetInputtips(List<Tip> tipList, int rCode) {
                            if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
                                //展示搜索到的地址列表
                                List<Map<String, String>> list = new ArrayList<>();
                                for (Tip tip : tipList) {
                                    Map<String, String> map = new HashMap<>();
                                    map.put("name", tip.getName());
                                    map.put("address", tip.getDistrict() + tip.getAddress());
                                    list.add(map);
                                }
                                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), list, R.layout.item_poi,
                                        new String[]{"name", "address"}, new int[]{R.id.mNameTextView, R.id.mAddressTextView});
                                mNameAutoCompleteTextView.setAdapter(simpleAdapter);
                                simpleAdapter.notifyDataSetChanged();

                                //当选中搜索地址一项后向地图添加一个marker
                                mNameAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Tip tip = tipList.get(position);
                                        if (tip == null) {
                                            return;
                                        }
                                        LatLonPoint latLonPoint = tip.getPoint();
                                        LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());

                                        //设置详细地址
                                        mNameAutoCompleteTextView.setText(tip.getName());
                                        mDetailAddressEditText.setText(tip.getDistrict() + tip.getAddress() + tip.getName());
                                        changeCamera(
                                                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                                                        latLng, 18, 30, 30)));
                                        setMarker(latLng);
                                        setPositionInfo(latLonPoint);
                                    }
                                });
                            }
                        }
                    });
                    inputTips.requestInputtipsAsyn();
                }
            }
        });
    }

    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update) {
        aMap.moveCamera(update);
    }

    /**
     * 设置标记点
     *
     * @param latLng
     */
    private void setMarker(LatLng latLng) {
        this.mMarkerLatLng = latLng;
        aMap.clear();
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker())
                .position(latLng);
        aMap.addMarker(markerOptions);
    }

    /**
     * 获取地址的信息
     *
     * @param latLonPoint
     */
    private void setPositionInfo(LatLonPoint latLonPoint) {
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                mDetailAddressEditText.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
    }

    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }
}
