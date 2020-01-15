package com.dyh.commonlib.presenter;

import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.GetStatisticsRequestBean;
import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IStatisticsView;


/**
 * 统计
 */
public class StatisticsPresenter extends MVPPresenter<IStatisticsView> {


    public StatisticsPresenter( IStatisticsView view) {
        super(view);
    }

    /**
     * 获取统计数据
     */
    public void loadData() {
        GetStatisticsRequestBean requestBean = new GetStatisticsRequestBean();
        requestBean.day = "2";
        LoginResponseBean loginResponseBean = EasySharedPreferences.load(SPEntity.class).loginResponseBean;
        if (null != loginResponseBean && null != loginResponseBean.getOper()) {
            requestBean.cityId = loginResponseBean.getOper().getCityId();
        }
        EasyHttp.post(ApiPathConstants.GET_STATISTICS)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<StatisticsResponseBean>(getView()) {
                    @Override
                    public void onSuccess(Optional<StatisticsResponseBean> listOptional) {
                        getView().showData(listOptional.getIncludeNull());
                    }
                });
    }
}
