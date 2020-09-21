package com.dyh.android.winehall.constants;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/5 0005 20:34
 * description: UI 配置项
 */
public class UIConfig {
    public static final int DELAY_TIME2REFRESH_BANNER = 15000;//刷新Banner缓存延时时间
    public static final int DELAY_TIME2REFRESH_CATEGORY = 5000;//刷新分类缓存延时时间


    public static final int PAGE_SIZE_DEFAULT = 20;             //每页加载数量
    public static final int PAGE_NO_START_DEFAULT = 1;          //页面开始加载的索引
    public static final String REQUEST_PARAM_ALL_DEFAULT = "-1";//默认全部分类
    public static final String EMPTY_DATA_DEFAULT = "--";       //默认空数据


    public static final int HOME_COURSE_MAX_IMAGE_COUNT = 3;    //首页课程最大展示图片数量，超过时列表展示
    public static final int HOME_COURSE_IMAGE_RADIUS = 2;       //首页课程图片圆角大小

    public static final int COURSE_DETAIL_COMMENT_PAGE_SIZE = 2;//课程详情评论列表加载数量
    public static final int COURSE_DETAIL_NEED_EXPAND_COUNT = 3;//课程需要展示展开全部按钮的最大数量

}
