package com.dyh.android.winehall.entity.common;

import java.util.List;

/**
 * 课节列表项
 */
public class LessonsBean {
    /**
     * title : 课节1
     * audition : 100
     * uuid : 1
     * course_id : 2020080123582910001
     */

    private String title;
    private String audition;
    private String uuid;
    private String course_id;
    private List<GenreBean> genres;
    public boolean isLastOne;//是否是最后一条（本地使用）

    public List<GenreBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBean> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudition() {
        return audition;
    }

    public void setAudition(String audition) {
        this.audition = audition;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
