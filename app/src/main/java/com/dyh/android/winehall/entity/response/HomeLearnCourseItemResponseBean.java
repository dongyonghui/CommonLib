package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.BaseHttpResponseBean;
import com.dyh.android.winehall.entity.common.GenreBean;
import com.dyh.android.winehall.entity.common.LecturerBean;
import com.dyh.common.lib.recyclerview_helper.entity.MultiItemEntity;

import java.text.MessageFormat;
import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 10:57
 * description:课程列表 item
 */
public class HomeLearnCourseItemResponseBean extends BaseHttpResponseBean implements MultiItemEntity {

    public static final int TYPE_IMAGE_COUNT = 0; //展示有限个数的图片
    public static final int TYPE_IMAGE_ARRAY = 1; //展示数组图片

    private String course_id;
    private LecturerBean lecturer;
    private String title;
    private String brief;
    private String sign_count;
    private String collect_count;
    private String comment_count;
    private String start_time;
    private String duration;
    private List<String> cover_show;
    private List<GenreBean> genres;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<GenreBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBean> genres) {
        this.genres = genres;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public LecturerBean getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerBean lecturer) {
        this.lecturer = lecturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSign_count() {
        return sign_count;
    }

    public void setSign_count(String sign_count) {
        this.sign_count = sign_count;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public List<String> getCover_show() {
        return cover_show;
    }

    public void setCover_show(List<String> cover_show) {
        this.cover_show = cover_show;
    }

    @Override
    public int getItemType() {
        if (null == cover_show || cover_show.size() <= UIConfig.HOME_COURSE_MAX_IMAGE_COUNT)
            return TYPE_IMAGE_COUNT;

        return TYPE_IMAGE_ARRAY;
    }

    /**
     * @return 返回标签图片地址
     */
    public String getFirstFlagImageUrl() {
        if (null != genres && genres.size() > 0)
            return genres.get(0).getImage();

        return null;
    }

    /**
     * @return 返回备注信息
     */
    public String getRemarkInfo() {
        String message = MyApplication.getInstance().getString(R.string.format_homeLearnCourseRemark);
        return MessageFormat.format(message, sign_count, collect_count, comment_count);
    }
}
