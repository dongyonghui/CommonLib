package com.dyh.commonlib.ui.city;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.AddOrEditCityTaskRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.CityTaskItemBean;
import com.dyh.commonlib.entity.response.OperatorItemBean;
import com.dyh.commonlib.presenter.AddOrEditCityTaskPresenter;
import com.dyh.commonlib.ui.view.IAddOrEditCityTaskView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.TimeUtil;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.picker.picker.DatePicker;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加或编辑城市任务
 */
public class AddOrEditCityTaskActivity extends BaseMVPActivity implements IAddOrEditCityTaskView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mDateTextView)
    TextView mDateTextView;
    @BindView(R.id.mChildItemTitleTextView)
    TextView mChildItemTitleTextView;
    @BindView(R.id.mChildSpinner)
    Spinner mChildSpinner;
    @BindView(R.id.mTargetOrderNumberEditText)
    EditText mTargetOrderNumberEditText;
    @BindView(R.id.mTargetOrderMoneyEditText)
    EditText mTargetOrderMoneyEditText;

    private CityTaskItemBean cityTaskItemBean;//编辑参数传递
    private AddOrEditCityTaskPresenter mPresenter = new AddOrEditCityTaskPresenter(this);
    private boolean isAdmin, isCityAdmin;
    String cityId = null, cityName = null;
    String operatorId = null, operatorName = null;
    private final List<Object> mCurrentShowList = new ArrayList<>();//当前展示的选项列表


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_city_task;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        cityTaskItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_CITY_TASK_ITEM_JSON), CityTaskItemBean.class);
        mDateTextView.setText(TimeUtil.date2String(new Date(), "yyyy-MM"));

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

        //初始化登录者相关信息
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (null != spEntity.loginResponseBean) {
            isAdmin = spEntity.loginResponseBean.isAdmin();
            isCityAdmin = spEntity.loginResponseBean.isCityAdmin();
            if (null != spEntity.loginResponseBean.getOper()) {
                cityId = spEntity.loginResponseBean.getOper().getCityId();
                cityName = spEntity.loginResponseBean.getOper().getCity();
            }
        }

        showEditInfos();
        refreshChildData();
    }

    /**
     * 展示编辑项的数据
     */
    private void showEditInfos() {
        if (null == cityTaskItemBean) {
            return;
        }
        mCommonTitleBar.getCenterTextView().setText(R.string.editCityTask);
        cityId = cityTaskItemBean.getCityId();
        cityName = cityTaskItemBean.getCity();
        operatorId = cityTaskItemBean.getOperatorId();
        operatorName = cityTaskItemBean.getOperatorName();

        mDateTextView.setText(cityTaskItemBean.getMonthDate());
        mTargetOrderMoneyEditText.setText(cityTaskItemBean.getTargetTradePrice());
        mTargetOrderNumberEditText.setText(cityTaskItemBean.getTargetOrderCount());
    }

    /**
     * 刷新选项数据
     */
    private void refreshChildData() {
        if (isAdmin) {
            mPresenter.loadCityList();
            mChildItemTitleTextView.setText(R.string.city);
        } else if (isCityAdmin) {
            mPresenter.loadOperatorList();
            mChildItemTitleTextView.setText(R.string.member);
        }
    }


    @OnClick(R.id.mDateTextView)
    public void selectDate() {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setWidth((int) (picker.getScreenWidthPixels()));
        Calendar currentCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        picker.setRangeStart(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1, currentCalendar.get(Calendar.DAY_OF_MONTH));
        picker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        picker.setSelectedItem(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                String date = year + "-" + month;
                mDateTextView.setText(date);
            }
        });
        picker.show();
    }

    @Override
    public void onLoadCitySuccess(List<CityItemBean> list) {
        if (list == null) {
            return;
        }
        if (null == cityTaskItemBean) {
            CityItemBean allCity = new CityItemBean();
            allCity.id = "0";
            allCity.name = getString(R.string.all);
            list.add(0, allCity);
        }
        int selectedIndex = 0;
        if (!TextUtils.isEmpty(cityId)) {
            for (int i = 0; i < list.size(); i++) {
                if (cityId.equals(list.get(i).id)) {
                    selectedIndex = i;
                    break;
                }
            }
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (CityItemBean cityItemBean : list) {
            arrayAdapter.add(cityItemBean.name);
        }
        mCurrentShowList.clear();
        mCurrentShowList.addAll(list);

        mChildSpinner.setAdapter(arrayAdapter);
        mChildSpinner.setSelection(selectedIndex);
    }

    @Override
    public void onLoadOperatorSuccess(List<OperatorItemBean> list) {
        if (list == null) {
            return;
        }
        if (null == cityTaskItemBean) {
            OperatorItemBean allItem = new OperatorItemBean();
            allItem.setOperatorId("0");
            allItem.setName(getString(R.string.all));
            list.add(0, allItem);
        }

        int selectedIndex = 0;
        if (!TextUtils.isEmpty(operatorId)) {
            for (int i = 0; i < list.size(); i++) {
                if (operatorId.equals(list.get(i).getOperatorId())) {
                    selectedIndex = i;
                    break;
                }
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (OperatorItemBean itemBean : list) {
            arrayAdapter.add(itemBean.getName());
        }
        mCurrentShowList.clear();
        mCurrentShowList.addAll(list);

        mChildSpinner.setAdapter(arrayAdapter);
        mChildSpinner.setSelection(selectedIndex);
    }

    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }


    private void save() {
        Object object = mCurrentShowList.get(mChildSpinner.getSelectedItemPosition());
        if (null == object) {
            EasyToast.getDEFAULT().show(isAdmin ? R.string.selectCityPlease : R.string.selectMemberPlease);
            refreshChildData();
            return;
        }

        AddOrEditCityTaskRequestBean requestBean = new AddOrEditCityTaskRequestBean();
        if (null != cityTaskItemBean) {
            requestBean.orderTaskId = cityTaskItemBean.getOrderTaskId();
        }
        //管理员选择的是城市
        if (isAdmin && object instanceof CityItemBean) {
            cityId = ((CityItemBean) object).id;
            cityName = ((CityItemBean) object).name;
        }

        //城市经理选择的是成员
        if (isCityAdmin && object instanceof OperatorItemBean) {
            requestBean.operatorId = ((OperatorItemBean) object).getOperatorId();
            requestBean.operatorName = ((OperatorItemBean) object).getName();
        }
        requestBean.targetOrderCount = mTargetOrderNumberEditText.getText().toString();
        requestBean.targetTradePrice = mTargetOrderMoneyEditText.getText().toString();
        requestBean.monthDate = mDateTextView.getText().toString();
        requestBean.cityId = cityId;
        requestBean.city = cityName;
        mPresenter.addOrEdit(requestBean);
    }
}
