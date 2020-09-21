package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.CourseLessonDetailResponseBean;
import com.dyh.android.winehall.entity.response.DanmuItemResponseBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/14 0014
 * description: 课程--课节详情
 */
public interface ICourseLessonDetailView extends MVPView {
    /**
     * 展示详情信息
     *
     * @param lessonDetailResponseBean
     */
    void showDetail(CourseLessonDetailResponseBean lessonDetailResponseBean);

    /**
     * 展示弹幕信息
     *
     * @param danmuItemResponseBeanList
     */
    void showDanmu(List<DanmuItemResponseBean> danmuItemResponseBeanList);


    /**
     * 刷新收藏状态
     *
     * @param isCollected
     */
    void refreshCollectStatus(boolean isCollected);
}
