package com.dyh.commonlib.ui.city;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.entity.response.TimeItemBean;
import com.dyh.commonlib.presenter.AddOrEditBetterGoodsTimePresenter;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 新建或编辑优选菜展示时间
 */
public class AddOrEditBetterGoodsTimeActivity extends BaseMVPActivity implements IDefaultOptionView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mNameEditText)
    EditText mNameEditText;

    @BindView(R.id.mStartTimeHourSpinner)
    Spinner mStartTimeHourSpinner;
    @BindView(R.id.mStartTimeMinuetSpinner)
    Spinner mStartTimeMinuetSpinner;
    @BindView(R.id.mEndTimeHourSpinner)
    Spinner mEndTimeHourSpinner;
    @BindView(R.id.mEndTimeMinuetSpinner)
    Spinner mEndTimeMinuetSpinner;

    private TimeItemBean timeItemBean;//编辑参数传递
    private AddOrEditBetterGoodsTimePresenter mPresenter = new AddOrEditBetterGoodsTimePresenter(this);


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_or_edit_better_goods_time;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        timeItemBean = JsonUtils.object(getIntent().getStringExtra(IntentConstants.KEY_BETTER_GOODS_TIME_ITEM_JSON), TimeItemBean.class);

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

        showEditInfos();
    }

    /**
     * 展示编辑项的数据
     */
    private void showEditInfos() {
        if (null == timeItemBean) {
            return;
        }
        mCommonTitleBar.getCenterTextView().setText(R.string.editBetterGoodsTime);
        mNameEditText.setText(timeItemBean.getName());
        //设置选中的时间
        String[] hoursArray = getResources().getStringArray(R.array.hours24);
        String[] minuetsArray = getResources().getStringArray(R.array.minutes);


        String entTimeStr = timeItemBean.getEndTimeStr();
        if (!TextUtils.isEmpty(entTimeStr) && entTimeStr.contains(":")) {
            String[] timeArray = entTimeStr.split(":");
            int hourIndex = 0, minutIndex = 0;
            for (int i = 0; i < hoursArray.length; i++) {
                if (hoursArray[i].equals(timeArray[0])) {
                    hourIndex = i;
                    break;
                }
            }
            for (int i = 0; i < minuetsArray.length; i++) {
                if (minuetsArray[i].equals(timeArray[1])) {
                    minutIndex = i;
                    break;
                }
            }
            mStartTimeMinuetSpinner.setSelection(minutIndex);
            mStartTimeHourSpinner.setSelection(hourIndex);
        }
        String sendTimeStr = timeItemBean.getSendTimeStr();
        if (!TextUtils.isEmpty(sendTimeStr) && sendTimeStr.contains(":")) {
            String[] timeArray = sendTimeStr.split(":");
            int hourIndex = 0, minutIndex = 0;
            for (int i = 0; i < hoursArray.length; i++) {
                if (hoursArray[i].equals(timeArray[0])) {
                    hourIndex = i;
                    break;
                }
            }
            for (int i = 0; i < minuetsArray.length; i++) {
                if (minuetsArray[i].equals(timeArray[1])) {
                    minutIndex = i;
                    break;
                }
            }
            mEndTimeMinuetSpinner.setSelection(minutIndex);
            mEndTimeHourSpinner.setSelection(hourIndex);
        }
    }


    @Override
    public void onOptionOk() {
        EasyToast.getDEFAULT().show(R.string.optionOk);
        setResult(RESULT_OK);
        finish();
    }


    private void save() {
        if (null == timeItemBean) {
            timeItemBean = new TimeItemBean();
        }
        timeItemBean.setName(mNameEditText.getText().toString());
        timeItemBean.setEndTimeStr(mStartTimeHourSpinner.getSelectedItem() + ":" + mStartTimeMinuetSpinner.getSelectedItem());
        timeItemBean.setSendTimeStr(mEndTimeHourSpinner.getSelectedItem() + ":" + mEndTimeMinuetSpinner.getSelectedItem());
        mPresenter.addOrEdit(timeItemBean);
    }
}
