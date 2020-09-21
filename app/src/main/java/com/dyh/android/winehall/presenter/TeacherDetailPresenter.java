package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.entity.response.TeacherDetailResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.ITeacherDetailView;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/15 0015
 * description:
 */
public class TeacherDetailPresenter extends MVPPresenter<ITeacherDetailView> {

    private int mPageNumber;        //当前分页为第几页数据
    private String mTeacherId;      //讲师ID

    public TeacherDetailPresenter(@Nullable ITeacherDetailView view) {
        super(view);
    }

    /**
     * 加载课程详情数据
     *
     * @param lecturerId
     */
    public void loadDetailData(String lecturerId) {
        this.mTeacherId = lecturerId;
        EasyHttp.get(ApiPathConstants.GET_LECTURER + "/" + lecturerId)
                .execute(new MyHttpNoViewCallBack<TeacherDetailResponseBean>() {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getView().hideLoadingView();
                    }

                    @Override
                    public void onSuccess(ResponseOptional<TeacherDetailResponseBean> response) {
                        getView().hideLoadingView();
                        getView().showDetail(response.getIncludeNull());
                    }
                }, getLifecycleProvider());
    }


    /**
     * 加载课程详情数据
     *
     * @param lecturerId
     */
    public void followTeacher(String lecturerId, final boolean isFollowAlready) {
        this.mTeacherId = lecturerId;
        EasyHttp.post((isFollowAlready ? ApiPathConstants.GET_LECTURER_UNFOLLOW
                : ApiPathConstants.GET_LECTURER_FOLLOW)

                + "/" + lecturerId)
                .execute(new ProgressDialogCallBack<String>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(ResponseOptional<String> response) {
                        EasyToast.getDEFAULT().show(response.getIncludeNull());
                        getView().refreshFollowStatus(!isFollowAlready);
                    }
                }, getLifecycleProvider());
    }


    /**
     * 获取分类信息
     */
    public void refreshList() {
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
        EasyHttp.get(ApiPathConstants.GET_LECTURER_COURSE + "/" + mTeacherId)
                .params(AppHttpKey.PAGE, String.valueOf(mPageNumber))
                .params(AppHttpKey.PAGE_SIZE, String.valueOf(UIConfig.PAGE_SIZE_DEFAULT))
                .execute(new MyHttpNoViewCallBack<TeacherDetailResponseBean.CourseInfo>() {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getView().hideLoadingView();
                    }

                    @Override
                    public void onSuccess(ResponseOptional<TeacherDetailResponseBean.CourseInfo> response) {
                        getView().hideLoadingView();

                        TeacherDetailResponseBean.CourseInfo courseInfo = response.getIncludeNull();
                        List<HomeLearnCourseItemResponseBean> list = courseInfo.list;

                        boolean isLastPage = null == list || list.size() < UIConfig.PAGE_SIZE_DEFAULT;
                        if (UIConfig.PAGE_NO_START_DEFAULT >= mPageNumber)
                            getView().onRefreshPageSuccess(list, isLastPage);
                        else
                            getView().onLoadMorePageSuccess(list, isLastPage);
                    }
                }, getLifecycleProvider());
    }

}
