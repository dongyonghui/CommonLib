package com.dyh.commonlib.ui.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.request.GetCityTaskRequestBean;
import com.dyh.commonlib.entity.response.CityTaskItemBean;
import com.dyh.commonlib.presenter.CityTaskManagePresenter;
import com.dyh.commonlib.ui.adapter.CityTaskListAdapter;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.TimeUtil;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.picker.picker.DatePicker;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 城市任务管理页面
 */
public class CityTaskManageActivity extends BaseMVPActivity implements IDefaultManageView<CityTaskItemBean> {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.mDateTextView)
    TextView mDateTextView;

    private CityTaskManagePresenter mPresenter = new CityTaskManagePresenter(this);
    private CityTaskListAdapter mListAdapter;
    private final GetCityTaskRequestBean mRequestBean = new GetCityTaskRequestBean();
    private final int REQUEST_CODE_EDIT = 100;//编辑

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_EDIT == requestCode && RESULT_OK == resultCode) {
            refreshList();
        }
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_task_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        mDateTextView.setText(TimeUtil.date2String(new Date(), "yyyy-MM"));
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action) {
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                        onBackPressed();
                        break;
                    case CommonTitleBar.ACTION_RIGHT_TEXT:
                        readyGoForResult(AddOrEditCityTaskActivity.class, REQUEST_CODE_EDIT);
                        break;
                }
            }
        });

        //初始化列表
        mListAdapter = new CityTaskListAdapter(R.layout.item_city_task_manage);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        //加载更多
        mListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadList(mRequestBean);
            }
        }, mRecyclerView);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //设置列表项按钮点击监听
        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.mEditTextView://编辑
                        Bundle bundle = new Bundle();
                        bundle.putString(IntentConstants.KEY_CITY_TASK_ITEM_JSON, JsonUtils.toJson(mListAdapter.getItem(position)));
                        readyGoForResult(AddOrEditCityTaskActivity.class, REQUEST_CODE_EDIT, bundle);
                        break;
                    case R.id.mDeleteTextView://删除
                        mPresenter.deleteCityTask(mListAdapter.getItem(position));
                        break;
                }
            }
        });

        refreshList();
    }

    /**
     * 刷新列表
     */
    private void refreshList() {
        mRequestBean.monthDate = mDateTextView.getText().toString();
        mPresenter.refreshList(mRequestBean);//刷新列表

    }

    @OnClick(R.id.mDateTextView)
    public void selectDate() {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setWidth((int) (picker.getScreenWidthPixels()));
        picker.setRangeStart(2018, 1, 1);
        Calendar currentCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        picker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        picker.setSelectedItem(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                String date = year + "-" + month;
                mDateTextView.setText(date);
                refreshList();
            }
        });
        picker.show();
    }

    @Override
    public void onOptionSuccess() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        refreshList();
    }

    @Override
    public void showList(List<CityTaskItemBean> list) {
        mListAdapter.setNewData(list);
    }

    @Override
    public void appendList(List<CityTaskItemBean> list) {
        mListAdapter.addListBottom(list);
    }
}
