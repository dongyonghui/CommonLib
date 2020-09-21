package com.dyh.android.winehall.http;

/**
 * API路径常量类
 */
public class ApiPathConstants {

    public final static String APP_VERSION_PATH = HttpHostUtil.getDefaultRootUrl() + "/ui/v1/version";//版本更新
    public final static String LOGIN_PATH = HttpHostUtil.getDefaultRootUrl() + "/user/login";//登录
    public final static String LOGIN_WX_PATH = HttpHostUtil.getDefaultRootUrl() + "/user/wxsns-login";//微信登录
    public final static String SEND_SMS_CODE = HttpHostUtil.getDefaultRootUrl() + "/user/send-sms";//发送验证码
    public final static String USER_INFO = HttpHostUtil.getDefaultRootUrl() + "/user/view";//获取用户详情
    public final static String GET_CATEGORY = HttpHostUtil.getDefaultRootUrl() + "/category";//获取分类列表
    public final static String GET_BANNER = HttpHostUtil.getDefaultRootUrl() + "/banner";//通用-获取banner列表
    public final static String GET_COURSE = HttpHostUtil.getDefaultRootUrl() + "/course";//课程列表
    public final static String GET_LESSON = HttpHostUtil.getDefaultRootUrl() + "/lesson";//获取课节详情
    public final static String GET_LESSON_BARRAGE = HttpHostUtil.getDefaultRootUrl()
            + "/lesson/pull-barrage";//获取课程视频的弹幕
    public final static String GET_LESSON_SEND_BARRAGE = HttpHostUtil.getDefaultRootUrl()
            + "/lesson/send-barrage";//课程学习发送弹幕
    public final static String GET_ATTACHMENT = HttpHostUtil.getDefaultRootUrl()
            + "/attachment";//获取课程、课节资料
    public final static String GET_LECTURER = HttpHostUtil.getDefaultRootUrl() + "/lecturer";//获取讲师个人详情
    public final static String GET_LECTURER_COURSE = HttpHostUtil.getDefaultRootUrl() + "/lecturer/course";//【分页】获取讲师的课程列表
    public final static String GET_LECTURER_FOLLOW = HttpHostUtil.getDefaultRootUrl() + "/user/follow";//关注讲师
    public final static String GET_LECTURER_UNFOLLOW = HttpHostUtil.getDefaultRootUrl() + "/user/unfollow";//取消关注讲师
    public final static String GET_COURSE_COMMENTS = HttpHostUtil.getDefaultRootUrl() + "/course/comments";//【分页】获取课程的评价列表
    public final static String COURSE_COLLECT = HttpHostUtil.getDefaultRootUrl()
            + "/user/collect";//收藏课程
    public final static String COURSE_UNCOLLECT = HttpHostUtil.getDefaultRootUrl()
            + "/user/uncollect";//取消收藏课程
    public final static String ATTACHMENT_SAVE = HttpHostUtil.getDefaultRootUrl()
            + "/attachment/save";//保存资料
    public final static String GET_ATTACHMENT_LIST = HttpHostUtil.getDefaultRootUrl()
            + "/attachment/user";//【分页】获取我下载的资料列表
}
