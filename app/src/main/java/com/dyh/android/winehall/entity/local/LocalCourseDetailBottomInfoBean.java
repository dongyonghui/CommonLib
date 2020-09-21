package com.dyh.android.winehall.entity.local;

import com.dyh.common.lib.recyclerview_helper.entity.MultiItemEntity;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/8 0008 16:33
 * description:课程详情底部信息列表item
 */
public class LocalCourseDetailBottomInfoBean implements MultiItemEntity {
    public static final int TYPE_TILE_DEFAULT = 0;//默认标题
    public static final int TYPE_TILE_COMMENT = 1;//标题 评论
    public static final int TYPE_TILE_COMMENT_FOOTER = 2;//标题 评论footer
    public static final int TYPE_ITEM_LESSON = 10;//课节
    public static final int TYPE_SHOW_ALL_LESSONS = 11;//展开所有课节
    public static final int TYPE_ITEM_COURSE_DETAIL_H1 = 20;//课程详情
    public static final int TYPE_ITEM_COURSE_DETAIL_H2 = 21;//课程详情
    public static final int TYPE_ITEM_COURSE_DETAIL_TEXT = 22;//课程详情
    public static final int TYPE_ITEM_COURSE_DETAIL_IMAGE = 23;//课程详情
    public static final int TYPE_ITEM_COURSE_DETAIL_HR = 24;//课程详情
    public static final int TYPE_ITEM_COMMENT = 30;//评论
    public static final int TYPE_ITEM_RECOMMEND = 40;//推荐
    public int type;
    public int titleInQuickTabIndex;//标题在Tab中索引值，快速定位使用
    public Object data;

    public LocalCourseDetailBottomInfoBean(int type, int titleInQuickTabIndex) {
        this.type = type;
        this.titleInQuickTabIndex = titleInQuickTabIndex;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public static class TitleDefaultData {
        public String title;
        public String countInfo;
    }
}
