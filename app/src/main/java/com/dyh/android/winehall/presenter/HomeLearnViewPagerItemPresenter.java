package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.IBasePageView;
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
 * created on: 2020/9/6 0006 10:56
 * description: 首页View Pager项单个页面
 */
public class HomeLearnViewPagerItemPresenter extends MVPPresenter<IBasePageView<HomeLearnCourseItemResponseBean>> {

    private int mPageNumber;                        //当前分页为第几页数据
    private String mCategoryId;                     //分类数据

    public HomeLearnViewPagerItemPresenter(@Nullable IBasePageView<HomeLearnCourseItemResponseBean> view) {
        super(view);
        mPageNumber = UIConfig.PAGE_NO_START_DEFAULT;
        mCategoryId = UIConfig.REQUEST_PARAM_ALL_DEFAULT;
    }

    /**
     * 获取分类信息
     */
    public void refreshList(String mCategoryId) {
        this.mCategoryId = mCategoryId;
        mPageNumber = UIConfig.PAGE_NO_START_DEFAULT;
        loadData();
    }

    /**
     * 加载下一页
     */
    public void loadMoreList() {
        mPageNumber++;
        loadData();
    }

    private void loadData() {
        EasyHttp.get(ApiPathConstants.GET_COURSE)
                .params(AppHttpKey.PAGE, String.valueOf(mPageNumber))
                .params(AppHttpKey.PAGE_SIZE, String.valueOf(UIConfig.PAGE_SIZE_DEFAULT))
                .params(AppHttpKey.CATEGORY_ID, mCategoryId)
                .execute(new MyHttpNoViewCallBack<List<HomeLearnCourseItemResponseBean>>() {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getView().hideLoadingView();
                    }

                    @Override
                    public void onSuccess(ResponseOptional<List<HomeLearnCourseItemResponseBean>> response) {
                        List<HomeLearnCourseItemResponseBean> list = response.getIncludeNull();
                        boolean isLastPage = null == list || list.size() < UIConfig.PAGE_SIZE_DEFAULT;
                        if (UIConfig.PAGE_NO_START_DEFAULT >= mPageNumber)
                            getView().onRefreshPageSuccess(list, isLastPage);
                        else
                            getView().onLoadMorePageSuccess(list, isLastPage);
                    }
                }, getLifecycleProvider());
    }
}
