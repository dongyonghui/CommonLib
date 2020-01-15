package com.dyh.commonlib.ui.supply_pool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polygon;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.AddOrEditSupplyPoolRequestBean;
import com.dyh.commonlib.entity.request.GetFilterListRequestBean;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolManageItemBean;
import com.dyh.commonlib.presenter.AddOrEditSupplyPoolPresenter;
import com.dyh.commonlib.ui.adapter.SelectedShopListAdapter;
import com.dyh.commonlib.ui.common.SelectShopsActivity;
import com.dyh.commonlib.ui.view.IAddOrEditSupplyPoolView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建或编辑供给池
 */
public class AddOrEditSupplyPoolActivity extends BaseMVPActivity implements IAddOrEditSupplyPoolView {

    private final int REQUEST_SELECT_SHOPS = 110;//选择店铺

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;
    @BindView(R.id.mNameAutoCompleteTextView)
    AutoCompleteTextView mNameAutoCompleteTextView;
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.mAroundShopRecyclerView)
    RecyclerView mAroundShopRecyclerView;
    @BindView(R.id.mMapsRootView)
    View mMapsRootView;
    @BindView(R.id.mShopsRootView)
    View mShopsRootView;

    private int flag = IntentConstants.Flag.EDIT_MAP_ONLY;//编辑选项
    private final SelectedShopListAdapter selectedShopListAdapter = new SelectedShopListAdapter(R.layout.item_selected_item);
    private AMap aMap;
    private final List<Marker> mMarkList = new ArrayList<>();//标记点的位置列表
    private AddOrEditSupplyPoolPresenter mPresenter = new AddOrEditSupplyPoolPresenter(this);
    private SupplyPoolManageItemBean supplyPoolManageItemBean;
    private Polygon polygon;//当前绘制的多边形
    private int currentDragIndex = -1;
    private List<SupplyPoolItemBean> supplyPoolItemBeanList;//全部供给池

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && null != data) {
            switch (requestCode) {
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
        return R.layout.activity_add_or_edit_supply_pool;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //获取编辑数据
        flag = getIntent().getIntExtra(IntentConstants.KEY_FLAG_INT, IntentConstants.Flag.EDIT_MAP_ONLY);
        supplyPoolManageItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_SUPPLY_POOL_MANAGE_ITEM_JSON), SupplyPoolManageItemBean.class);
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
        initEditData();
    }

    /**
     * 清空地图
     */
    @OnClick(R.id.mClearFenceTextView)
    public void clearMap() {
        mMarkList.clear();
        aMap.clear();
        drawPolygon(supplyPoolItemBeanList);
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


    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onLoadSupplyPoolSuccess(List<SupplyPoolItemBean> supplyPoolItemBeanList) {
        this.supplyPoolItemBeanList = supplyPoolItemBeanList;
        drawPolygon(supplyPoolItemBeanList);
    }

    private void initEditData() {
        //店铺和地图围栏只能一次保存一个，后台API功能限制，不能同时传
        if (IntentConstants.Flag.EDIT_MAP_ONLY == flag) {
            mShopsRootView.setVisibility(View.GONE);
            mMapsRootView.setVisibility(View.VISIBLE);
            initMap();

            //获取所有供给池进行绘制图形
            GetFilterListRequestBean requestBean = new GetFilterListRequestBean();
            requestBean.cityId = "0";
            mPresenter.loadSupplyPoolList(requestBean);
        } else if (IntentConstants.Flag.EDIT_SHOPS_ONLY == flag) {//初始化店铺
            mMapsRootView.setVisibility(View.GONE);
            mShopsRootView.setVisibility(View.VISIBLE);
            //点击选中的店铺项后删除
            selectedShopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    selectedShopListAdapter.remove(position);
                }
            });
            //设置周边店铺适配器
            mAroundShopRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mAroundShopRecyclerView.setAdapter(selectedShopListAdapter);

            if (supplyPoolManageItemBean == null) {
                return;
            }
            mCommonTitleBar.getCenterTextView().setText(R.string.editDeliveryAddress);
            mNameEditText.setText(supplyPoolManageItemBean.getName());
            selectedShopListAdapter.setNewData(supplyPoolManageItemBean.getShopList());
        }

    }


    /**
     * 保存数据
     */
    private void save() {
        if (TextUtils.isEmpty(mNameEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputSupplyPoolNamePlease);
            return;
        }
        if (IntentConstants.Flag.EDIT_MAP_ONLY == flag && mMarkList.size() < 3) {
            EasyToast.getDEFAULT().show(R.string.drawFenceNotEnoughInfo);
            return;
        }
        if (IntentConstants.Flag.EDIT_SHOPS_ONLY == flag && selectedShopListAdapter.getItemCount() <= 0) {
            EasyToast.getDEFAULT().show(R.string.selectShopsPlease);
            return;
        }
        AddOrEditSupplyPoolRequestBean requestBean = new AddOrEditSupplyPoolRequestBean();
        if (null != supplyPoolManageItemBean) {
            requestBean.supplyPoolId = supplyPoolManageItemBean.getSupplyPoolId();
        }
        requestBean.name = mNameEditText.getText().toString();
        requestBean.address = requestBean.name;
        if (IntentConstants.Flag.EDIT_MAP_ONLY == flag) {
            StringBuilder points = new StringBuilder();
            double lonTotal = 0, latTotal = 0;
            for (Marker marker : mMarkList) {
                lonTotal += marker.getPosition().longitude;
                latTotal += marker.getPosition().latitude;
                points.append(marker.getPosition().longitude)
                        .append(",")
                        .append(marker.getPosition().latitude)
                        .append(";");
            }
            //供给池中心点
            DecimalFormat decimalFormat = new DecimalFormat("0.000000");
            requestBean.lat = decimalFormat.format(latTotal / mMarkList.size());
            requestBean.lon = decimalFormat.format(lonTotal / mMarkList.size());
            requestBean.points = points.toString();
        } else if (IntentConstants.Flag.EDIT_SHOPS_ONLY == flag) {
            //店铺列表
            requestBean.shopList = selectedShopListAdapter.getData();
        }

        mPresenter.addOrEdit(requestBean);
    }


    private void initMap() {
        //初始化地图控制器对象
        aMap = mMapView.getMap();
        //如果是新建，则定位当前位置
        if (null == supplyPoolManageItemBean) {
            aMap.setMyLocationEnabled(true);
        } else {//如果是编辑，则地图定位到供给池中心点，并绘制所有供给池范围
            mCommonTitleBar.getCenterTextView().setText(R.string.editDeliveryAddress);
            mNameEditText.setText(supplyPoolManageItemBean.getName());
            double lat = MathUtil.getDoubleNumber(supplyPoolManageItemBean.getLat());
            double lon = MathUtil.getDoubleNumber(supplyPoolManageItemBean.getLon());
            if (0 != lat) {
                changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        new LatLng(lat, lon), 16, 30, 30)));
            }
            String points = supplyPoolManageItemBean.getPoints();
            if (!TextUtils.isEmpty(points)) {
                String[] pointArray = points.split(";");
                for (String s : pointArray) {
                    String[] latLonArray = s.split(",");
                    setMarker(new LatLng(MathUtil.getDoubleNumber(latLonArray[1]), MathUtil.getDoubleNumber(latLonArray[0])));
                }
            }
        }

        //点击地图添加marker
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMarker(latLng);
            }
        });

        //设置marker拖拽事件
        aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                for (int i = 0; i < mMarkList.size(); i++) {
                    if (mMarkList.get(i).getId().equals(marker.getId())) {
                        currentDragIndex = i;
                    }
                }
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mMarkList.remove(currentDragIndex);
                marker.remove();
                setMarker(marker.getPosition());
                currentDragIndex = -1;
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
                    Inputtips inputTips = new Inputtips(AddOrEditSupplyPoolActivity.this, inputquery);
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
                                        if (tip == null || null == tip.getPoint()) {
                                            EasyToast.getDEFAULT().show(R.string.cantGetPositionInfo);
                                            mNameAutoCompleteTextView.setText(null);
                                            return;
                                        }
                                        LatLonPoint latLonPoint = tip.getPoint();
                                        LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());

                                        //设置详细地址
                                        mNameAutoCompleteTextView.setText(tip.getName());
                                        changeCamera(
                                                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                                                        latLng, 16, 30, 30)));
//                                        setMarker(latLng);
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
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_blue))
                .position(latLng).draggable(true);
        Marker marker = aMap.addMarker(markerOptions);
        if (-1 == currentDragIndex) {
            mMarkList.add(marker);
        } else {
            mMarkList.add(currentDragIndex, marker);
        }
        drawCurrentPolygon();
    }


    /**
     * 绘制当前的多边形
     */
    private void drawCurrentPolygon() {
        final PolygonOptions polygonOptions = new PolygonOptions();
        // 声明 多边形参数对象
        polygonOptions.strokeWidth(2) // 多边形的边框
                .strokeColor(getResources().getColor(R.color.mapLinesColor)) // 边框颜色
                .fillColor(getResources().getColor(R.color.mapOverlyColor));   // 多边形的填充色
        // 添加 多边形的每个顶点（顺序添加）
        for (Marker marker : mMarkList) {
            polygonOptions.add(marker.getPosition());
        }
        //添加多边形
        if (null != polygon && polygon.isVisible()) {
            polygon.remove();
        }
        polygon = aMap.addPolygon(polygonOptions);
    }


    //绘制所有的供给池多边形
    private void drawPolygon(List<SupplyPoolItemBean> supplyPoolItemBeanList) {
        if (null == supplyPoolItemBeanList || supplyPoolItemBeanList.isEmpty()) {
            return;
        }

        List<Polygon> polygonList = new ArrayList<Polygon>();
        for (SupplyPoolItemBean itemBean : supplyPoolItemBeanList) {
            String points = itemBean.points;
            if (!TextUtils.isEmpty(points)) {
                String[] pointArray = points.split(";");
                PolygonOptions polygonOption = new PolygonOptions();
                polygonOption.strokeWidth(1) // 多边形的边框
                        .strokeColor(getResources().getColor(R.color.mapLinesColor2)) // 边框颜色
                        .fillColor(getResources().getColor(R.color.mapOverlyColor2));   // 多边形的填充色

                for (String s : pointArray) {
                    String[] latLonArray = s.split(",");
                    polygonOption.add(new LatLng(MathUtil.getDoubleNumber(latLonArray[1]), MathUtil.getDoubleNumber(latLonArray[0])));
                }

                Polygon polygon = aMap.addPolygon(polygonOption);
                polygonList.add(polygon);
            }
        }
    }
}
