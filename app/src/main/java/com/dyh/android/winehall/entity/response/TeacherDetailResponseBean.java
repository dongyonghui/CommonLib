package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/15 0015
 * description: 讲师详情
 */
public class TeacherDetailResponseBean extends BaseHttpResponseBean {

    private String lecturer_id;
    private String name;
    private String avatar_show;
    private String verify_status;
    private String brief;
    private boolean is_followed;
    private List<String> verify_show;
    private CourseInfo course;

    public boolean isIs_followed() {
        return is_followed;
    }

    public void setIs_followed(boolean is_followed) {
        this.is_followed = is_followed;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_show() {
        return avatar_show;
    }

    public void setAvatar_show(String avatar_show) {
        this.avatar_show = avatar_show;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<String> getVerify_show() {
        return verify_show;
    }

    public void setVerify_show(List<String> verify_show) {
        this.verify_show = verify_show;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public static class CourseInfo {
        public List<HomeLearnCourseItemResponseBean> list;
        public String total;
    }
}
