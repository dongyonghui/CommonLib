package com.dyh.commonlib.presenter;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CityDeliverPriceBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.ICityDeliveryPriceManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;


/**
 * 城市配送费管理
 */
public class CityDeliveryPriceManagePresenter extends MVPPresenter<ICityDeliveryPriceManageView> {


    public CityDeliveryPriceManagePresenter( ICityDeliveryPriceManageView view) {
        super(view);
    }

    /**
     * 新建或修改
     */
    public void getDeliveryPrice() {
        EasyHttp.post(ApiPathConstants.GET_CITY_DELIVERY_PRICE)
                .execute(new ProgressDialogCallBack<CityDeliverPriceBean>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<CityDeliverPriceBean> result) {
                        getView().onLoadDeliveryPriceSuccess(result.getIncludeNull());
                    }
                });
    }

    /**
     * 新建或修改
     *
     * @param cityDeliverPriceBean
     */
    public void submit(CityDeliverPriceBean cityDeliverPriceBean) {
        MyAppDialog myAppDialog = new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.cityDeliveryPriceChangeTo))
                .setMessage(getActivity().getString(R.string.formatString_setCityDeliveryPrice
                        , cityDeliverPriceBean.getFirstDeliveryFee(), cityDeliverPriceBean.getDeliveryFee()
                        , cityDeliverPriceBean.getFreeFee()))
                .setNegative(getActivity().getString(R.string.cancel))
                .setPositive(getActivity().getString(R.string.common_confirm), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        EasyHttp.post(ApiPathConstants.SUBMIT_CITY_DELIVERY_PRICE)
                                .upObject(cityDeliverPriceBean)
                                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<Object> listOptional) {
                                        getView().onSubmitSuccess();
                                    }
                                });
                    }
                });
        myAppDialog.show();
    }
}
