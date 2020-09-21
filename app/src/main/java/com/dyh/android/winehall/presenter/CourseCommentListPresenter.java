package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.response.CourseCommentItemResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.ICourseCommentListView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/18 0018
 * description:课程详情--全部评价列表
 */
public class CourseCommentListPresenter extends MVPPresenter<ICourseCommentListView> {

    private int mPageNumber;        //当前分页为第几页数据
    private String mDetailId;       //详情ID

    public CourseCommentListPresenter(@Nullable ICourseCommentListView view) {
        super(view);
    }

    /**
     * 获取分类信息
     */
    public void refreshList(String mDetailId) {
        this.mDetailId = mDetailId;
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
        EasyHttp.get(ApiPathConstants.GET_COURSE_COMMENTS + "/" + mDetailId)
                .params(AppHttpKey.PAGE, String.valueOf(mPageNumber))
                .params(AppHttpKey.PAGE_SIZE, String.valueOf(UIConfig.PAGE_SIZE_DEFAULT))
                .execute(new MyHttpCallBack<List<CourseCommentItemResponseBean>>(getView()) {
                    @Override
                    public void onSuccess(ResponseOptional<List<CourseCommentItemResponseBean>> response) {
                        super.onSuccess(response);
                        List<CourseCommentItemResponseBean> list = response.getIncludeNull();
                        boolean isLastPage = null == list || list.size() < UIConfig.PAGE_SIZE_DEFAULT;
                        if (UIConfig.PAGE_NO_START_DEFAULT >= mPageNumber)
                            getView().onRefreshPageSuccess(list, isLastPage);
                        else
                            getView().onLoadMorePageSuccess(list, isLastPage);
                    }
                }, getLifecycleProvider());
    }
}
