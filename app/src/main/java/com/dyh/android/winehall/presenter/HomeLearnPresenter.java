package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.constants.ServerType;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.local.CacheHomeDataBean;
import com.dyh.android.winehall.entity.local.SPCacheEntity;
import com.dyh.android.winehall.entity.response.BannerItemResponseBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.IHomeLearnView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 17:10
 * description: 学酒
 */
public class HomeLearnPresenter extends MVPPresenter<IHomeLearnView> {

    private SPCacheEntity spCacheEntity;
    private Runnable runnableRefreshBanner = this::getBanner;
    private Runnable runnableRefreshCategory = this::getCategory;

    public HomeLearnPresenter(@Nullable IHomeLearnView view) {
        super(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRefreshAllPageDatas();
    }

    /**
     * 停止刷新数据
     */
    private void stopRefreshAllPageDatas() {
        MyApplication.getInstance().getUiHandler().removeCallbacks(runnableRefreshCategory);
        MyApplication.getInstance().getUiHandler().removeCallbacks(runnableRefreshBanner);
    }

    /**
     * 刷新并展示数据
     */
    public void refreshAllPageDatas2View() {
        spCacheEntity = EasySharedPreferences.load(SPCacheEntity.class);
        CacheHomeDataBean cacheHomeDataBean = spCacheEntity.getCacheHomeDataBean();
        //如果缓存为空，立即刷新，否则先展示缓存后再延时1秒刷新
        if (null == cacheHomeDataBean.bannerItemResponseBeanList) {
            getBanner();
        } else {
            getView().showBannerList(cacheHomeDataBean.bannerItemResponseBeanList);
            MyApplication.getInstance().getUiHandler().postDelayed(runnableRefreshBanner, UIConfig.DELAY_TIME2REFRESH_BANNER);
        }

        //如果缓存为空，立即刷新，否则先展示缓存后再延时1秒刷新
        if (null == cacheHomeDataBean.categoryItemResponseBeanList) {
            getCategory();
        } else {
            getView().showCategoryList(cacheHomeDataBean.categoryItemResponseBeanList);
            MyApplication.getInstance().getUiHandler().postDelayed(runnableRefreshCategory, UIConfig.DELAY_TIME2REFRESH_CATEGORY);
        }
    }


    /**
     * 获取分类信息
     */
    public void getCategory() {
        EasyHttp.get(ApiPathConstants.GET_CATEGORY)
                .params(AppHttpKey.TYPE, ServerType.TYPE_LEARN)
                .execute(new MyHttpNoViewCallBack<List<CategoryItemResponseBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        //缓存信息
                        if (null == spCacheEntity)
                            spCacheEntity = EasySharedPreferences.load(SPCacheEntity.class);

                        //发生请求异常，并且缓存数据为空时，需要展示异常界面，保证可再次请求
                        if (null == spCacheEntity.getCacheHomeDataBean().categoryItemResponseBeanList){
                            getView().hideLoadingView();
                            getView().showNetworkErrorView(getActivity().getString(R.string.info_retry2GetData));
                        }
                    }

                    @Override
                    public void onSuccess(ResponseOptional<List<CategoryItemResponseBean>> response) {
                        //缓存信息
                        if (null == spCacheEntity)
                            spCacheEntity = EasySharedPreferences.load(SPCacheEntity.class);

                        //首次缓存展示到UI
                        if (null == spCacheEntity.getCacheHomeDataBean().categoryItemResponseBeanList)
                            getView().showCategoryList(response.getIncludeNull());

                        spCacheEntity.getCacheHomeDataBean().categoryItemResponseBeanList = response.getIncludeNull();
                        spCacheEntity.apply();
                    }
                }, getLifecycleProvider());
    }

    /**
     * 获取banner信息
     */
    private void getBanner() {
        EasyHttp.get(ApiPathConstants.GET_BANNER)
                .params(AppHttpKey.TYPE, ServerType.TYPE_LEARN)
                .execute(new MyHttpNoViewCallBack<List<BannerItemResponseBean>>() {
                    @Override
                    public void onSuccess(ResponseOptional<List<BannerItemResponseBean>> response) {
                        //缓存信息
                        if (null == spCacheEntity)
                            spCacheEntity = EasySharedPreferences.load(SPCacheEntity.class);

                        //首次缓存展示到UI
                        if (null == spCacheEntity.getCacheHomeDataBean().bannerItemResponseBeanList) {
                            getView().showBannerList(response.getIncludeNull());
                        }
                        spCacheEntity.getCacheHomeDataBean().bannerItemResponseBeanList = response.getIncludeNull();
                        spCacheEntity.apply();

                    }
                }, getLifecycleProvider());
    }
}
