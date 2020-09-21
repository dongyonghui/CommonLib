package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.CourseDetailResponseBean;
import com.dyh.common.lib.mvp.MVPView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 10:46
 * description: 课程详情
 */
public interface ILessonCourseDetailView extends MVPView {
    /**
     * 展示详情到UI上
     *
     * @param courseDetailResponseBean
     */
    void showDetail2Ui(CourseDetailResponseBean courseDetailResponseBean);
}
