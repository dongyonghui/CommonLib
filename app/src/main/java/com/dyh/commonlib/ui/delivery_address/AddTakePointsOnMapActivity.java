package com.dyh.commonlib.ui.delivery_address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.local.AddTakePointForResultBean;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 在地图上添加取餐点
 */
public class AddTakePointsOnMapActivity extends BaseMVPActivity {
    private static final String MAP_FRAGMENT_TAG = "map";

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;
    @BindView(R.id.mContentRootView)
    FrameLayout mContentRootView;
    private AMap aMap;
    private LatLng mCenterLatLng;//地图中心点
    private LatLng mSelectedLatLng;//地图中心点


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_take_points_on_map;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        mCenterLatLng = getIntent().getParcelableExtra(IntentConstants.KEY_MAP_LATLAN);

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

        AMapOptions aOptions = new AMapOptions();
        aOptions.zoomGesturesEnabled(false);// 禁止通过手势缩放地图
        aOptions.scrollGesturesEnabled(false);// 禁止通过手势移动地图
        if (null != mCenterLatLng) {
            aOptions.camera(new CameraPosition(mCenterLatLng, 17, 30, 20));
        }
        SupportMapFragment aMapFragment = SupportMapFragment.newInstance(aOptions);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.add(R.id.mContentRootView, aMapFragment,
                MAP_FRAGMENT_TAG);
        fragmentTransaction.commit();

        aMap = aMapFragment.getMap();
        setMarker(mCenterLatLng);
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMarker(latLng);
            }
        });
    }

    private void save() {
        if (TextUtils.isEmpty(mNameEditText.getText())) {
            EasyToast.getDEFAULT().show(R.string.inputNamePlease);
            return;
        }
        if (null == mSelectedLatLng) {
            EasyToast.getDEFAULT().show(R.string.notSelectPointsOnMap);
            return;
        }
        Intent intent = new Intent();
        AddTakePointForResultBean forResultBean = new AddTakePointForResultBean();
        forResultBean.name = mNameEditText.getText().toString();
        forResultBean.selectedPosition = mSelectedLatLng;
        intent.putExtra(IntentConstants.KEY_ADD_TAKE_POINT_RESULT_JSON, JsonUtils.toJson(forResultBean));
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 设置标记点
     *
     * @param latLng
     */
    private void setMarker(LatLng latLng) {
        this.mSelectedLatLng = latLng;
        aMap.clear();
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker())
                .position(latLng);
        aMap.addMarker(markerOptions);
    }
}
