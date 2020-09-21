package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.local.LocalCourseDetailBottomInfoBean;
import com.dyh.android.winehall.entity.response.CourseDetailResponseBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 10:46
 * description: 课程详情
 */
public interface ICourseDetailView extends MVPView {
    /**
     * 展示详情到UI上
     *
     * @param courseDetailResponseBean
     */
    void showDetail2Ui(CourseDetailResponseBean courseDetailResponseBean);

    /**
     * 展示底部列表信息
     *
     * @param bottomInfoBeanList
     */
    void showDetailBottomListInfo2Ui(List<LocalCourseDetailBottomInfoBean> bottomInfoBeanList);

    /**
     * 刷新收藏状态
     *
     * @param isCollected
     */
    void refreshCollectStatus(boolean isCollected);
}
