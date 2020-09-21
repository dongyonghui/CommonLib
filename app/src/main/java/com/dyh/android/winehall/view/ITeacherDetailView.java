package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.entity.response.TeacherDetailResponseBean;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/15 0015
 * description:讲师详情
 */
public interface ITeacherDetailView extends IBasePageView<HomeLearnCourseItemResponseBean> {

    /**
     * 展示详情
     *
     * @param teacherDetailResponseBean
     */
    void showDetail(TeacherDetailResponseBean teacherDetailResponseBean);

    /**
     * 刷新关注状态
     *
     * @param isFollowAlready 是否已关注
     */
    void refreshFollowStatus(boolean isFollowAlready);

}
