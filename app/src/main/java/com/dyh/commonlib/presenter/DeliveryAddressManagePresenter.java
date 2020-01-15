package com.dyh.commonlib.presenter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.DeleteDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.request.GetDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.request.UpdateDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.dw.util.TimeUtil;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.calendar_picker.CalendarPickerView;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 配送地址管理
 */
public class DeliveryAddressManagePresenter extends MVPPresenter<IDefaultManageView<DeliveryAddressManageItemBean>> {


    public DeliveryAddressManagePresenter( IDefaultManageView<DeliveryAddressManageItemBean> view) {
        super(view);
    }

    public void refreshList(GetDeliveryAddressRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetDeliveryAddressRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }

    /**
     * 删除配送地址
     *
     * @param itemBean
     */
    public void deleteDeliverAddress(DeliveryAddressManageItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.formatString_deleteDelivery, itemBean.getRemark()))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        DeleteDeliveryAddressRequestBean requestBean = new DeleteDeliveryAddressRequestBean();
                        requestBean.deliveryAddressIds = new ArrayList<>();
                        requestBean.deliveryAddressIds.add(itemBean.getDeliveryAddressId());
                        EasyHttp.post(ApiPathConstants.DELETE_DELIVERY_ADDRESS)
                                .upObject(requestBean)
                                .execute(new ProgressDialogCallBack<List<DeliveryAddressManageItemBean>>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<List<DeliveryAddressManageItemBean>> listOptional) {
                                        getView().onOptionSuccess();
                                        dialog.dismiss();
                                    }
                                });
                    }
                })
                .isCancelable(true)
                .show();
    }

    /**
     * 开启或关闭配送地址
     *
     * @param itemBean
     */
    public void openOrCloseDeliverAddress(DeliveryAddressManageItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_content_open_close_delivery_address, null);
        CalendarPickerView mCalendarPickerView = contentView.findViewById(R.id.mCalendarPickerView);
        String titleInfo;
        String useStatus;
        //如果已经启用，则提示关闭信息，否则提示开启信息
        if (ServerConstants.DELIVERY_ADDRESS_USE_STATUS_USED.equals(itemBean.getUseStatus())) {
            titleInfo = getActivity().getString(R.string.formatString_closeDeliveryAddressConfirm, itemBean.getRemark());
            Calendar nextYear = Calendar.getInstance();
            nextYear.add(Calendar.YEAR, 1);
            Date selectedDay = new Date();
            mCalendarPickerView.init(selectedDay, nextYear.getTime())
                    .inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(selectedDay);

            useStatus = "0";
        } else {
            titleInfo = getActivity().getString(R.string.formatString_openDeliveryAddressConfirm, itemBean.getRemark());
            Date selectedDay = TimeUtil.string2Date(itemBean.getOpenTime(), "yyyy-MM-dd");
            Date nextDay = new Date(selectedDay.getTime() + 24 * 60 * 60 * 1000);
            mCalendarPickerView.init(selectedDay, nextDay)
                    .inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(selectedDay)
                    .displayOnly();
            useStatus = "1";
        }

        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(titleInfo)
                .setCustomerView(contentView)
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.common_confirm), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        UpdateDeliveryAddressRequestBean requestBean = new UpdateDeliveryAddressRequestBean();
                        requestBean.deliveryAddressId = itemBean.getDeliveryAddressId();
                        requestBean.useStatus = useStatus;
                        requestBean.openTime = TimeUtil.date2String(mCalendarPickerView.getSelectedDate(), "yyyy-MM-dd");
                        EasyHttp.post(ApiPathConstants.UPDATE_DELIVERY_ADDRESS)
                                .upObject(requestBean)
                                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<Object> listOptional) {
                                        getView().onOptionSuccess();
                                        dialog.dismiss();
                                    }
                                });
                    }
                })
                .isCancelable(true)
                .show();
    }


    private void loadListData(GetDeliveryAddressRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_DELIVERY_ADDRESS_MANAGE_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<DeliveryAddressManageItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<DeliveryAddressManageItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
