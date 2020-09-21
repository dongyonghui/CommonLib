package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;
import com.dyh.android.winehall.entity.common.CourseDetailChildBean;
import com.dyh.android.winehall.entity.common.GenreBean;
import com.dyh.android.winehall.entity.common.LecturerBean;
import com.dyh.android.winehall.entity.common.LessonsBean;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 17:09
 * description: 课程详情
 */
public class CourseDetailResponseBean extends BaseHttpResponseBean {
    private String course_id;
    private LecturerBean lecturer;
    private String title;
    private String brief;
    private String sign_count;
    private String collect_count;
    private String comment_count;
    private String score;
    private String lang;
    private String duration;
    private String lesson_count;
    private String start_time;
    private String is_free;
    private boolean is_collected;
    private String total_fee;
    private String verify_fee;
    private String line_fee;
    private String vip_fee;
    private Detail detail;
    private List<String> cover_show;
    private List<GenreBean> genres;
    private List<LessonsBean> lessons;

    public boolean isIs_collected() {
        return is_collected;
    }

    public void setIs_collected(boolean is_collected) {
        this.is_collected = is_collected;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLesson_count() {
        return lesson_count;
    }

    public void setLesson_count(String lesson_count) {
        this.lesson_count = lesson_count;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getVerify_fee() {
        return verify_fee;
    }

    public void setVerify_fee(String verify_fee) {
        this.verify_fee = verify_fee;
    }

    public String getLine_fee() {
        return line_fee;
    }

    public void setLine_fee(String line_fee) {
        this.line_fee = line_fee;
    }

    public String getVip_fee() {
        return vip_fee;
    }

    public void setVip_fee(String vip_fee) {
        this.vip_fee = vip_fee;
    }

    public List<String> getCover_show() {
        return cover_show;
    }

    public void setCover_show(List<String> cover_show) {
        this.cover_show = cover_show;
    }

    public List<GenreBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBean> genres) {
        this.genres = genres;
    }

    public List<LessonsBean> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonsBean> lessons) {
        this.lessons = lessons;
    }

    public static class Detail {
        private String node;
        private List<CourseDetailChildBean> child;

        public List<CourseDetailChildBean> getChild() {
            return child;
        }

        public void setChild(List<CourseDetailChildBean> child) {
            this.child = child;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }


    }

}
